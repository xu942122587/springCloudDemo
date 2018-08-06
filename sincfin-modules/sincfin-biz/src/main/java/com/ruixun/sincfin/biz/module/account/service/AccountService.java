package com.ruixun.sincfin.biz.module.account.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ruixun.sincfin.biz.facade.UserFacade;
import com.ruixun.sincfin.biz.feign.client.AccountClient;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMoneyRecordMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountRechargeMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountWithdrawMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankMapper;
import com.ruixun.sincfin.biz.pay.fuiou.FuiouPayHelper;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayRequest;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayResponse;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.lock.RedisLock;
import com.ruixun.sincfin.common.lock.RedisLockParam;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.common.util.SincfinUtils;
import com.ruixun.sincfin.common.util.Snowflake;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.AccountMoneyRecordEntity;
import com.ruixun.sincfin.domain.entity.AccountRechargeEntity;
import com.ruixun.sincfin.domain.entity.AccountWithdrawEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.enums.AccountMoneyRecordTypeEnum;
import com.ruixun.sincfin.domain.enums.AccountRechargeStatusEnum;
import com.ruixun.sincfin.domain.enums.AccountWithdrawStatusEnum;
import com.ruixun.sincfin.domain.enums.CouponTriggerConditionEnum;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

@RestController
@RequestMapping("account")
public class AccountService implements AccountClient {

	private Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

	@Resource
	private AccountMapper accountMapper;

	@Resource
	private UserBankMapper userBankMapper;

	@Resource
	private AccountMoneyRecordMapper accountMoneyRecordMapper;

	@Resource
	private AccountWithdrawMapper accountWithdrawMapper;

	@Resource
	private AccountRechargeMapper accountRechargeMapper;

	@Resource
	private FuiouPayHelper fuiouPayHelper;

	@Resource
	private UserFacade userFacade;

	/**
	 * 添加
	 *
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public AccountDto add(@RequestBody AccountDto accountDto) {
		AccountEntity accountEntity = new AccountEntity();
		BeanHelper.copyProperties(accountEntity, accountDto);
		accountMapper.insertSelective(accountEntity);
		accountDto.setId(accountEntity.getId());
		return accountDto;
	}

	/**
	 * 删除
	 *
	 * @param accountDto
	 */
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public void delete(@RequestBody AccountDto accountDto) {
		// TODO
	}

	/**
	 * 更新
	 *
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public AccountDto update(@RequestBody AccountDto accountDto) {
		AccountEntity accountEntity = new AccountEntity();
		BeanHelper.copyProperties(accountEntity, accountDto);
		accountMapper.updateByPrimaryKeySelective(accountEntity);
		return accountDto;
	}

	/**
	 * 根据id查找
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public AccountDto getById(@RequestParam Long id) {

		AccountEntity accountEntity = accountMapper.selectByPrimaryKey(id);
		if (accountEntity == null) {
			return null;
		}
		AccountDto accountDto = new AccountDto();
		BeanHelper.copyProperties(accountDto, accountEntity);

		return accountDto;
	}

	/**
	 * 根据userId查找
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getByUserId", method = RequestMethod.GET)
	public AccountDto getByUserId(@RequestParam Long userId) {

		AccountEntity accountEntity = accountMapper.getByUserId(userId);
		if (accountEntity == null) {
			return null;
		}
		AccountDto accountDto = new AccountDto();
		BeanHelper.copyProperties(accountDto, accountEntity);

		return accountDto;
	}

	/**
	 * 提现
	 *
	 * @param userId
	 *            用户id
	 * @param bankCodeId
	 *            bankCodeId
	 * @param amount
	 *            金额
	 */
	@Override
	@Transactional
	@RedisLock(prefix = "account_withdraw", error = "当前提现人数较多，请稍后重试")
	@RequestMapping(value = "withdraw", method = RequestMethod.POST)
	public ApiResponse withdraw(@RedisLockParam @RequestParam("userId") Long userId,
			@RequestParam("bankCodeId") Long bankCodeId, @RequestParam("amount") Long amount) {

		UserBankEntity userBankEntity = userBankMapper.getAvailable(bankCodeId);
		if (userBankEntity == null) {
			return new ApiResponse(ApiStatusEnum.USER_BANK_CARD_NOT_EXIST);
		}
		// 限定提现次数
		long countWithdraw = accountWithdrawMapper.countWithdrawByDate(userId, new Date());
		countWithdraw = 3 - countWithdraw;
		if (countWithdraw <= 0) {
			return new ApiResponse(ApiStatusEnum.USER_WITHDRAW_TIME_OVERRUN);
		}
		String withdrawNum = Snowflake.getFlowSnowflakeInstance().nextId(ConfigConstants.SN_WITH_DRAW, userId); // todo
																												// 生成规则

		// 账号提现
		AccountDto accountDto = this.getByUserId(userId);
		// 确定可提现金额
		Long canWithdrawAmount = SincfinUtils.canWithdrawAmount(userBankEntity, accountDto);
		if (canWithdrawAmount < amount) {
			return new ApiResponse(ApiStatusEnum.USER_WITHDRAW_AMOUNT_OVERRUN);
		}

		accountWithdraw(accountDto, amount);
		// 添加提现记录
		addWithdrawRecord(accountDto, userBankEntity, amount, withdrawNum);
		// 账户操作记录（TODO 这一步是不是该在提现审核之后完成）
		// addAccountMoneyRecord(accountDto ,amount,withdrawNum);

		// 更新银行卡的累计提现金额
		updateUserBankTotalWithdraw(userBankEntity, amount);
		return ApiResponse.successApiResponse();
	}

	/**
	 * 账号提现
	 *
	 * @param accountDto
	 * @param amount
	 * @return
	 */
	private AccountDto accountWithdraw(AccountDto accountDto, long amount) {

		accountDto.setWalletBalance(accountDto.getWalletBalance() - amount);
		accountDto.setWithdrawAmount(accountDto.getWithdrawAmount() + amount);
		accountDto = this.update(accountDto);
		return accountDto;
	}

	private void addAccountMoneyRecord(AccountDto accountDto, long amount, String withdrawNum) {
		// 记录账户记录
		AccountMoneyRecordEntity accountMoneyRecordEntity = new AccountMoneyRecordEntity();
		accountMoneyRecordEntity.setUserId(accountDto.getUserId());
		accountMoneyRecordEntity.setAccountId(accountDto.getId());
		accountMoneyRecordEntity.setType(AccountMoneyRecordTypeEnum.WITHDRAW.getCode());
		accountMoneyRecordEntity.setObjectId(withdrawNum);
		accountMoneyRecordEntity.setTitle(AccountMoneyRecordTypeEnum.WITHDRAW.getText());
		accountMoneyRecordEntity.setAmount(amount);
		accountMoneyRecordEntity.setBalance(accountDto.getWalletBalance());
		accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);
	}

	private void addWithdrawRecord(AccountDto accountDto, UserBankEntity userBankEntity, long amount,
			String withdrawNum) {
		// 记录提现记录
		AccountWithdrawEntity accountWithdrawEntity = new AccountWithdrawEntity();
		accountWithdrawEntity.setUserId(accountDto.getUserId());
		accountWithdrawEntity.setAccountId(accountDto.getId());
		accountWithdrawEntity.setUserBankId(userBankEntity.getId());
		accountWithdrawEntity.setBankName(userBankEntity.getBankName());
		accountWithdrawEntity.setBankCardNo(userBankEntity.getBankCardNo());
		accountWithdrawEntity.setAmount(amount);
		accountWithdrawEntity.setStatus(AccountWithdrawStatusEnum.PENDING.getCode());
		accountWithdrawMapper.insertSelective(accountWithdrawEntity);
	}

	/**
	 * 更新银行卡的累计提现信息
	 *
	 * @param userBankEntity
	 * @param amount
	 */
	private void updateUserBankTotalWithdraw(UserBankEntity userBankEntity, Long amount) {
		userBankEntity.setTotalWithdraw(userBankEntity.getTotalWithdraw() + amount);
		userBankMapper.updateByPrimaryKeySelective(userBankEntity);
	}

	/**
	 * 充值获取验证码
	 *
	 * @param id
	 * @param bankCodeId
	 * @param amount
	 */
	@RedisLock(prefix = "account_recharge_smsCode", error = "当前人数较多，请稍后重试")
	@RequestMapping(value = "recharge/smsCode", method = RequestMethod.POST)
	public ApiResponse rechargeSmsCode(@RequestParam("ip") String ip,
			@RedisLockParam @RequestParam("userId") Long userId, @RequestParam("bankCodeId") Long bankCodeId,
			@RequestParam("amount") Long amount, @RequestParam("type") String type) {

		String rechargeNum = Snowflake.getFlowSnowflakeInstance().nextId(ConfigConstants.SN_RECHARGE, userId); // todo
																												// 生成规则

		FuiouPayRequest request = null;
		FuiouPayResponse response = null;
		UserBankEntity userBankEntity = null;
		try {

			userBankEntity = userBankMapper.selectByPrimaryKey(bankCodeId);
			assertUserBankAvailable(userBankEntity);
			// attr2 表示富有的协议号
			if (StringUtils.isEmpty(userBankEntity.getFuiouProtocolNo())) {
				request = fuiouPayHelper.orderActionRequest(ip, rechargeNum, userBankEntity.getFuiouUserId(),
						String.valueOf(amount), userBankEntity.getBankCardNo(), userBankEntity.getRealName(),
						userBankEntity.getIdCardNo(), userBankEntity.getMobile());
				response = fuiouPayHelper.orderAction(userId, request);
			} else {
				request = fuiouPayHelper.proPayOrderActionRequest(ip, rechargeNum, userBankEntity.getFuiouUserId(),
						String.valueOf(amount), userBankEntity.getFuiouProtocolNo());
				response = fuiouPayHelper.proPayOrderAction(userId, request);
			}
		} catch (BizException be) {
			return new ApiResponse(be.getExceptionCode(), be.getMessage());
		} catch (Exception e) {
			LOGGER.error("recharge sms code gain fail msg : " + e.getMessage());
			return new ApiResponse(ApiStatusEnum.COMMON_BIZ_SERVICE_ERROR);
		}
		// 返回“0000”表示成功
		if (!"0000".equals(response.getResponseCode())) {
			return new ApiResponse(ApiStatusEnum.USER_RECHARGE_FAIL.getStatus(),
					response.getResponseMsg() + "[" + response.getResponseCode() + "]");
		}
		addRechargeRecord(userId, rechargeNum, amount, userBankEntity, response.getOrderId(), response.getSignPay(),
				type);

		return ApiResponse.successApiResponse(rechargeNum);
	}

	/**
	 * 充值
	 *
	 * @param id
	 * @param bankCodeId
	 * @param amount
	 */
	@Override
	@Transactional
	@RedisLock(prefix = "account_recharge", error = "当前充值人数较多，请稍后重试")
	@RequestMapping(value = "recharge", method = RequestMethod.POST)
	public ApiResponse recharge(@RequestParam("ip") String ip,
			@RedisLockParam @RequestParam("rechargeNum") String rechargeNum, @RequestParam("smsCode") String smsCode) {

		AccountRechargeEntity accountRechargeEntity = null;
		UserBankEntity userBankEntity = null;
		FuiouPayResponse response = null;
		boolean firstCharge = false;
		try {
			accountRechargeEntity = accountRechargeMapper.getByRechargeNum(rechargeNum);
			firstCharge = assertFirstCharge(accountRechargeEntity);
			assertCanRecharge(accountRechargeEntity);
			userBankEntity = userBankMapper.selectByPrimaryKey(accountRechargeEntity.getUserBankId());
			assertUserBankAvailable(userBankEntity);

			FuiouPayRequest request = null;
			if (StringUtils.isEmpty(userBankEntity.getFuiouProtocolNo())) { // 首次支付
				request = fuiouPayHelper.payActionRequest(ip, rechargeNum, userBankEntity.getFuiouUserId(),
						accountRechargeEntity.getFuiouOrderId(), userBankEntity.getBankCardNo(),
						userBankEntity.getMobile(), smsCode, accountRechargeEntity.getFuiouSignPay());
				response = fuiouPayHelper.PayAction(accountRechargeEntity.getUserId(), request);
			} else { // 非首次支付
				request = fuiouPayHelper.proPayPayActionRequest(ip, rechargeNum, userBankEntity.getFuiouUserId(),
						accountRechargeEntity.getFuiouOrderId(), userBankEntity.getFuiouProtocolNo(), smsCode,
						accountRechargeEntity.getFuiouSignPay());
				response = fuiouPayHelper.proPayPayAction(accountRechargeEntity.getUserId(), request);
			}
			LOGGER.info("\n富友结果：{}", JSON.toJSONString(response));
		} catch (BizException be) {
			LOGGER.info("富友报错：{}", be.getMessage());
			return new ApiResponse(be.getExceptionCode(), be.getMessage());
		} catch (Exception e) {
			LOGGER.error("recharge fail msg : " + e.getMessage());
			return new ApiResponse(ApiStatusEnum.COMMON_BIZ_SERVICE_ERROR);
		}
		// 返回“0000”表示成功
		if (response.getResponseCode().equals("0000")) {
			updateRechargeRecord(accountRechargeEntity, AccountRechargeStatusEnum.SUCCESS, response);
			accountRecharge(accountRechargeEntity);
			updateUserBank(userBankEntity, accountRechargeEntity, response);
			if (firstCharge) {
				userFacade.sendCoupon(accountRechargeEntity.getUserId(),
						CouponTriggerConditionEnum.FIRST_RECHARGE.getCode());
			}
		} else if (FuiouPayHelper.unknownCode.contains(response.getResponseCode())) {
			updateRechargeRecord(accountRechargeEntity, AccountRechargeStatusEnum.UNKNOWN, response);
			return new ApiResponse(ApiStatusEnum.USER_RECHARGE_RESULT_UNKNOWN.getStatus(),
					response.getResponseMsg() + "[" + response.getResponseCode() + "]");
		} else if (FuiouPayHelper.smsCodeErrorCode.contains(response.getResponseCode())) {
			return new ApiResponse(ApiStatusEnum.USER_SMSCODE_ERROR);
		} else {
			updateRechargeRecord(accountRechargeEntity, AccountRechargeStatusEnum.FAILURE, response);
			return new ApiResponse(ApiStatusEnum.USER_RECHARGE_FAIL.getStatus(),
					response.getResponseMsg() + "[" + response.getResponseCode() + "]");
		}

		return ApiResponse.successApiResponse(response.getAmt());
	}

	/**
	 * 判断是否首次充值
	 * 
	 * @param accountRechargeEntity
	 * @return
	 */
	private boolean assertFirstCharge(AccountRechargeEntity accountRechargeEntity) {
		boolean firstCharge = false;
		int num = accountRechargeMapper.selectChargeCount(accountRechargeEntity.getUserId());
		if (num == 0) {
			firstCharge = true;
		}
		return firstCharge;
	}

	/**
	 * 更新银行卡信息
	 *
	 * @param userBankEntity
	 * @param accountRechargeEntity
	 * @param response
	 */
	private void updateUserBank(UserBankEntity userBankEntity, AccountRechargeEntity accountRechargeEntity,
			FuiouPayResponse response) {
		userBankEntity.setTotalRecharge(userBankEntity.getTotalRecharge() + accountRechargeEntity.getAmount());
		if (userBankEntity.getBindStatus().equals(UserBankBindStatusEnum.UNBIND.getCode())) {
			userBankEntity.setBindStatus(UserBankBindStatusEnum.BIND.getCode());
		}
		if (response.isFirstPay()) {
			userBankEntity.setFuiouProtocolNo(response.getProtocolno());
		}
		userBankMapper.updateByPrimaryKeySelective(userBankEntity);
	}

	/**
	 * 更新充值记录状态
	 *
	 * @param accountRechargeEntity
	 * @param status
	 */
	public void updateRechargeRecord(AccountRechargeEntity accountRechargeEntity, AccountRechargeStatusEnum status,
			FuiouPayResponse response) {
		accountRechargeEntity.setStatus(status.getCode());
		accountRechargeEntity.setFuiouCode(response.getResponseCode());
		accountRechargeEntity.setFuiouMsg(response.getResponseMsg());
		accountRechargeMapper.updateByPrimaryKeySelective(accountRechargeEntity);
	}

	/**
	 * 添加支付记录
	 *
	 * @param userId
	 * @param rechargeNum
	 * @param amount
	 * @param userBankEntity
	 * @param orderId
	 *            富有订单号
	 * @param signPay
	 *            富又支付签名
	 * @return
	 */
	private AccountRechargeEntity addRechargeRecord(Long userId, String rechargeNum, Long amount,
			UserBankEntity userBankEntity, String orderId, String signPay, String type) {
		// 记录充值记录
		AccountRechargeEntity accountRechargeEntity = new AccountRechargeEntity();
		accountRechargeEntity.setRechargeNum(rechargeNum);
		accountRechargeEntity.setUserId(userId);
		accountRechargeEntity.setAmount(amount);
		accountRechargeEntity.setUserBankId(userBankEntity.getId());
		accountRechargeEntity.setBankName(userBankEntity.getBankName());
		accountRechargeEntity.setBankCardNo(userBankEntity.getBankCardNo());
		accountRechargeEntity.setType(type);
		accountRechargeEntity.setStatus(AccountRechargeStatusEnum.UNPAID.getCode());
		accountRechargeEntity.setFuiouOrderId(orderId);
		accountRechargeEntity.setFuiouSignPay(signPay);
		accountRechargeMapper.insertSelective(accountRechargeEntity);
		return accountRechargeEntity;
	}

	/**
	 * 断言银行卡是否可用
	 *
	 * @param productEntity
	 */
	private void assertUserBankAvailable(UserBankEntity userBankEntity) {
		if (userBankEntity == null) {
			throw new BizException(ApiStatusEnum.USER_BANK_CARD_NOT_EXIST);
		}
		if (!userBankEntity.getBindMerch().equalsIgnoreCase("fuiou")) {
			throw new BizException(ApiStatusEnum.USER_PAY_CHANNEL_ERROR);
		}
	}

	/**
	 * 断言充值记录的可用性
	 *
	 * @param productEntity
	 */
	private void assertCanRecharge(AccountRechargeEntity accountRechargeEntity) {
		if (accountRechargeEntity == null) {
			throw new BizException(ApiStatusEnum.USER_RECHARGE_RECORD_NOT_EXIST);
		}
		if (!accountRechargeEntity.getStatus().equals(AccountRechargeStatusEnum.UNPAID.getCode())) {
			throw new BizException(ApiStatusEnum.USER_RECHARGE_REPEAT_SUBMIT);
		}
	}

	/**
	 * 变更账号余额（账号充值）
	 *
	 * @param userId
	 * @param orderDto
	 */
	private void accountRecharge(AccountRechargeEntity accountRechargeEntity) {
		// 对账户做加操作
		AccountEntity accountEntity = accountMapper.getByUserId(accountRechargeEntity.getUserId());
		// 变更账户余额
		accountEntity.setWalletBalance(accountEntity.getWalletBalance() + accountRechargeEntity.getAmount());
		accountMapper.updateByPrimaryKeySelective(accountEntity);

		// 记录账户记录
		AccountMoneyRecordEntity accountMoneyRecordEntity = new AccountMoneyRecordEntity();
		accountMoneyRecordEntity.setUserId(accountRechargeEntity.getUserId());
		accountMoneyRecordEntity.setAccountId(accountEntity.getId());
		accountMoneyRecordEntity.setType(AccountMoneyRecordTypeEnum.RECHARGE.getCode());
		accountMoneyRecordEntity.setObjectId(accountRechargeEntity.getRechargeNum());
		accountMoneyRecordEntity.setTitle(AccountMoneyRecordTypeEnum.RECHARGE.getText());
		accountMoneyRecordEntity.setAmount(accountRechargeEntity.getAmount());
		accountMoneyRecordEntity.setBalance(accountEntity.getWalletBalance());
		accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);
	}

	/**
	 * 统计规定时间内的提现次数
	 *
	 * @param id
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "countWithdrawByDate", method = RequestMethod.GET)
	public int countWithdrawByDate(@RequestParam("userId") Long userId, @RequestParam("time") Date time) {
		return (int) accountWithdrawMapper.countWithdrawByDate(userId, time);
	}

	@RequestMapping(value = "countTodayWithdraw", method = RequestMethod.GET)
	public int countTodayWithdraw(@RequestParam("userId") Long userId) {
		return (int) accountWithdrawMapper.countTodayWithdraw(userId);
	}

	/**
	 * 处理充值中的订单
	 */
	@RequestMapping(value = "rechargeHandle", method = RequestMethod.POST)
	public void rechargeHandle() {
		List<AccountRechargeEntity> recharges = accountRechargeMapper
				.listByStatus(AccountRechargeStatusEnum.UNKNOWN.getCode());
		UserBankEntity userBankEntity = null;
		for (AccountRechargeEntity rechargeRecord : recharges) {

			// //如果超过了10分钟，则将支付记录状态改变为 未确认
			// Date maxTimeLimit = DateUtils.addDateMinu(rechargeRecord.getGmtCreate(), 10);
			// if (new Date().compareTo(maxTimeLimit) > 0) {
			// rechargeRecord.setStatus(AccountRechargeStatusEnum.UNCONFIRMED.getCode());
			// } else {
			FuiouPayResponse response = null;
			try {
				FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
				fuiouPayRequest.setMchntOrderId(rechargeRecord.getRechargeNum());
				response = fuiouPayHelper.queryAction(rechargeRecord.getUserId(), fuiouPayRequest);
			} catch (Exception e) {
				LOGGER.error("in payment order handle erroneous : " + e.getMessage());
			}
			rechargeRecord.setFuiouCode(response.getResponseCode());
			rechargeRecord.setFuiouMsg(response.getResponseMsg());
			if (response.getResponseCode().equals("0000")) {
				userBankEntity = userBankMapper.getAvailable(rechargeRecord.getUserBankId());
				updateRechargeRecord(rechargeRecord, AccountRechargeStatusEnum.SUCCESS, response);
				accountRecharge(rechargeRecord);
				updateUserBank(userBankEntity, rechargeRecord, response);
			} else if (FuiouPayHelper.unknownCode.contains(response.getResponseCode())) {
				updateRechargeRecord(rechargeRecord, AccountRechargeStatusEnum.UNKNOWN, response);
			} else {
				updateRechargeRecord(rechargeRecord, AccountRechargeStatusEnum.FAILURE, response);
			}
			// }
			accountRechargeMapper.updateByPrimaryKeySelective(rechargeRecord);
		}
	}

}
