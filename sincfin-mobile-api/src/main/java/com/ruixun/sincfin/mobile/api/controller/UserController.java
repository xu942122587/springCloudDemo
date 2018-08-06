package com.ruixun.sincfin.mobile.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.AccountClient;
import com.ruixun.sincfin.biz.feign.client.AccountMoneyRecordClient;
import com.ruixun.sincfin.biz.feign.client.AccountRechargeClient;
import com.ruixun.sincfin.biz.feign.client.AccountWithdrawClient;
import com.ruixun.sincfin.biz.feign.client.ChannelClient;
import com.ruixun.sincfin.biz.feign.client.CouponUserClient;
import com.ruixun.sincfin.biz.feign.client.FileObjectClient;
import com.ruixun.sincfin.biz.feign.client.OrderClient;
import com.ruixun.sincfin.biz.feign.client.ProductClient;
import com.ruixun.sincfin.biz.feign.client.UserBankChangeClient;
import com.ruixun.sincfin.biz.feign.client.UserBankClient;
import com.ruixun.sincfin.biz.feign.client.UserClient;
import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.client.message.PushMessageClient;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.MessageContent;
import com.ruixun.sincfin.common.RedisConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.common.util.IpUtils;
import com.ruixun.sincfin.common.util.NumberUtils;
import com.ruixun.sincfin.common.util.RandomUtil;
import com.ruixun.sincfin.common.util.SaltGenerate;
import com.ruixun.sincfin.common.util.SincfinUtils;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.dto.AccountMoneyRecordDto;
import com.ruixun.sincfin.domain.dto.AccountRechargeDto;
import com.ruixun.sincfin.domain.dto.AccountWithdrawDto;
import com.ruixun.sincfin.domain.dto.CouponUserDto;
import com.ruixun.sincfin.domain.dto.InvitationRecordDto;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.dto.UserBankDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.enums.AccountMoneyRecordTypeEnum;
import com.ruixun.sincfin.domain.enums.AccountRechargeStatusEnum;
import com.ruixun.sincfin.domain.enums.AccountRechargeTypeEnum;
import com.ruixun.sincfin.domain.enums.AccountWithdrawStatusEnum;
import com.ruixun.sincfin.domain.enums.CouponTriggerConditionEnum;
import com.ruixun.sincfin.domain.enums.CouponUserStatusEnum;
import com.ruixun.sincfin.domain.enums.DeviceTypeEnum;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.enums.UserRealNameAuthEnum;
import com.ruixun.sincfin.domain.enums.UserRistTestEnum;
import com.ruixun.sincfin.domain.enums.UserStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.AccountMoneyRecordQuery;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;
import com.ruixun.sincfin.domain.query.AccountWithdrawQuery;
import com.ruixun.sincfin.domain.query.CouponUserQuery;
import com.ruixun.sincfin.domain.query.OrderQuery;
import com.ruixun.sincfin.domain.query.UserBankQuery;
import com.ruixun.sincfin.domain.query.UserQuery;
import com.ruixun.sincfin.mobile.api.annotation.Auth;
import com.ruixun.sincfin.mobile.api.config.SincfinConfiguration;
import com.ruixun.sincfin.mobile.api.config.SwitchConfiguration;
import com.ruixun.sincfin.mobile.api.vo.CouponUserVo;
import com.ruixun.sincfin.mobile.api.vo.UserAccountVO;

/**
 * @author t
 * @date 2018/4/28 16:09
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	private Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserClient userClient;
	@Resource
	private AccountClient accountClient;
	@Resource
	private UserBankClient userBankClient;
	@Resource
	private UserBankChangeClient userBankChangeClient;
	@Resource
	private AccountMoneyRecordClient accountMoneyRecordClient;
	@Resource
	private AccountWithdrawClient accountWithdrawClient;
	@Resource
	private AccountRechargeClient accountRechargeClient;
	@Resource
	private CouponUserClient couponUserClient;
	@Resource
	private ProductClient productClient;
	@Resource
	private OrderClient orderClient;
	@Resource
	private SincfinConfiguration config;
	@Resource
	private FileObjectClient fileObjectClient;
	@Autowired
	private PushMessageClient messageClient;
	@Autowired
	private ChannelClient channelClient;

	@Resource
	private SwitchConfiguration switchConfig;

	/**
	 * 获取短信验证码
	 *
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/smsCode", method = RequestMethod.POST)
	public ApiResponse smsCode(@RequestParam(value = "mobile", defaultValue = "0") String mobile,
			@RequestParam(value = "type", required = true) String type) {
		// 如果类型不是注册，和忘记密码，一律需要做登陆校验
		UserDto userDto = null;
		if (!type.equals("register") && !type.equals("password")) {
			assertLogin();
			Long userId = getCurrentUserId();
			userDto = userClient.getById(userId);
		}
		if (type.equals("register")) {
			userDto = userClient.getByMobile(mobile);
			if (userDto != null) {
				throw new BizException(ApiStatusEnum.USER_MOBILE_ALREADY_EXIST);
			}
		}
		if (mobile.equals("0") || userDto != null) {
			mobile = userDto.getMobile();
		}
		if (!StringUtils.isMobile(mobile)) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_FORMAT_ERROR);
		}
		sendSmsCode(mobile, type);
		return ApiResponse.successApiResponse("验证码已发送至您" + StringUtils.mobileEncryption(mobile) + "手机号，请注意查收");
	}

	/**
	 * 登陆
	 *
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResponse login(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "password", required = true) String password) {
		if (!StringUtils.isMobile(mobile)) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_FORMAT_ERROR);
		}
		UserDto userDto = userClient.getByMobile(mobile);
		assertCanLogin(userDto);
		if (!DigestUtils.md5Hex(password).equals(userDto.getPassword()))
			throw new BizException(ApiStatusEnum.USER_PASSWORD_ERROR);
		Map<String, Object> param = new HashMap<String, Object>();
		String token = createToken(userDto);
		param.put("token", token);
		param.put("mobile", userDto.getMobile());
		return ApiResponse.successApiResponse(param);
	}

	private String createToken(UserDto userDto) {
		String token = UUID.randomUUID().toString().replace("-", "");
		String redisToken = RedisConstants.getUserTokenKey(token);
		coexistLogin(userDto, redisToken);
		redisTemplate.opsForValue().set(redisToken, String.valueOf(userDto.getId()),
				RedisConstants.USER_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
		return token;
	}

	/* 限制多个客户端配置登陆，清除其他手机登陆的状态 */
	private void coexistLogin(UserDto userDto, String redisToken) {
		if (!switchConfig.isCoexist()) {
			String oldToken = redisTemplate.opsForValue().get(RedisConstants.getUserKey(userDto.getId()));
			if (StringUtils.isNotBlank(oldToken) && redisTemplate.hasKey(oldToken)) {
				redisTemplate.delete(oldToken);
			}
			redisTemplate.opsForValue().set(RedisConstants.getUserKey(userDto.getId()), redisToken,
					RedisConstants.USER_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
		}
	}

	private void assertCanLogin(UserDto userDto) {
		Assert.assertNotNull(userDto, ApiStatusEnum.USER_MOBILE_NOT_EXIST);
		if (userDto.getStatus() != null && userDto.getStatus().equals(UserStatusEnum.FROZEN.getCode())) {
			throw new BizException(ApiStatusEnum.USER_FROZEN);
		}
	}

	/**
	 * 登出
	 *
	 * @param mobile
	 * @param password
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ApiResponse logout() {
		clearCurrentUser();
		return ApiResponse.successApiResponse();
	}

	/**
	 * 注册
	 *
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ApiResponse register(String inviter, String channel,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "smsCode", required = true) String smsCode, String cid) {
		if (!StringUtils.isMobile(mobile)) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_FORMAT_ERROR);
		}
		String oSmsCode = getSmsCode(mobile, "register");
		if (oSmsCode == null || !oSmsCode.equals(smsCode)) {
			throw new BizException(ApiStatusEnum.USER_SMSCODE_ERROR);
		}
		cleanSmsCode(mobile, "register");
		UserDto userDto = userClient.getByMobile(mobile);
		if (userDto != null) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_ALREADY_EXIST);
		}
		userDto = new UserDto();
		if (StringUtils.isNotEmpty(inviter)) {
			if (!StringUtils.isMobile(inviter)) {
				throw new BizException(ApiStatusEnum.USER_MOBILE_FORMAT_ERROR);
			}
			UserDto inviterDto = userClient.getByMobile(inviter);
			if (inviterDto == null) {
				throw new BizException(ApiStatusEnum.INVITER_USER_ID_NOT_EXIST);
			}
			userDto.setInviterId(inviterDto.getId());

		}
		userDto.setChannelName(channel);
		/* 版本号 */
		String appversion = getRequest().getHeader(ConfigConstants.HTTP_HEADER_APP_VERSION);
		logger.info("版本号：{}", appversion);
		/* 设备类型 */
		String deviceType = getRequest().getHeader(ConfigConstants.HTTP_HEADER_DEVICE_TYPE);
		logger.info("设备类型：{}", deviceType);
		if (StringUtils.isNotBlank(deviceType) && deviceType.toLowerCase().equalsIgnoreCase("ios")) {
			userDto.setDeviceType(DeviceTypeEnum.IOS.getCode());
		} else if (StringUtils.isNotBlank(deviceType) && deviceType.toLowerCase().equalsIgnoreCase("android")) {
			userDto.setDeviceType(DeviceTypeEnum.ANDROID.getCode());
		} else {
			userDto.setDeviceType(DeviceTypeEnum.H5.getCode());

		}
		// 渠道
		String channelId = getRequest().getHeader(ConfigConstants.HTTP_HEADER_CHANNEL_ID);
		logger.info("渠道编号：{}", channelId);
		if (StringUtils.isNotEmpty(channelId) && NumberUtils.isNumber(channelId)) {
			userDto.setChannelId(Long.valueOf(channelId));
		}
		// 渠道ID
		if (StringUtils.isNotEmpty(cid) && NumberUtils.isNumber(cid)) {
			userDto.setChannelId(Long.valueOf(channelId));
		}
		userDto.setMobile(mobile);
		userDto.setSalt(SaltGenerate.generate());

		userDto.setPassword(DigestUtils.md5Hex(password));

		userClient.register(userDto);

		return ApiResponse.successApiResponse();
	}

	/**
	 * 修改登陆密码
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public ApiResponse updatePassword(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "smsCode", required = true) String smsCode) {
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		if (!userDto.getMobile().equals(mobile)) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_ERROR);
		}
		String oSmsCode = getSmsCode(userDto.getMobile(), "password");
		if (oSmsCode == null || !oSmsCode.equals(smsCode)) {
			throw new BizException(ApiStatusEnum.USER_SMSCODE_ERROR);
		}
		cleanSmsCode(mobile, "password");
		userClient.updatePassword(userDto.getId(), password);
		return ApiResponse.successApiResponse();
	}

	/**
	 * 忘记密码
	 *
	 * @return
	 */
	@RequestMapping(value = "/forget/password", method = RequestMethod.POST)
	public ApiResponse forgetPassword(@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "smsCode", required = true) String smsCode) {

		if (!StringUtils.isMobile(mobile)) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_FORMAT_ERROR);
		}

		UserDto userDto = userClient.getByMobile(mobile);
		if (userDto == null) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_NOT_EXIST);
		}
		String oSmsCode = getSmsCode(userDto.getMobile(), "password");
		if (oSmsCode == null || !oSmsCode.equals(smsCode)) {
			throw new BizException(ApiStatusEnum.USER_SMSCODE_ERROR);
		}
		cleanSmsCode(mobile, "password");
		userClient.updatePassword(userDto.getId(), password);

		return ApiResponse.successApiResponse();
	}

	/**
	 * 个人中心
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public ApiResponse center() {
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		UserAccountVO account = new UserAccountVO();
		account.setMobile(userDto.getMobile());
		AccountDto accountDto = accountClient.getByUserId(userDto.getId());
		// 用户的账户余额+待收本金+待收利息+提现中的金额
		long totalAssets = accountDto.getWalletBalance() + accountDto.getCurrentInvestmentAmount()
				+ accountDto.getWaitInterest() + accountDto.getWithdrawAmount();
		account.setTotalAssets(totalAssets); // 总资产
		account.setWalletBalance(accountDto.getWalletBalance()); // 账户余额
		account.setTotalProfit(
				accountDto.getWaitInterest() + accountDto.getReceivedInterest() + accountDto.getCouponBonusIncome()); // 总收益

		return ApiResponse.successApiResponse(account);
	}

	/**
	 * 总资产
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/total/assets", method = RequestMethod.GET)
	public ApiResponse totalAssets() {
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		UserAccountVO account = new UserAccountVO();
		account.setMobile(userDto.getMobile());
		AccountDto accountDto = accountClient.getByUserId(userDto.getId());
		// 用户的账户余额+待收本金+待收利息+提现中的金额
		long totalAssets = accountDto.getWalletBalance() + accountDto.getCurrentInvestmentAmount()
				+ accountDto.getWaitInterest() + accountDto.getWithdrawAmount();
		account.setTotalAssets(totalAssets); // 总资产
		account.setWalletBalance(accountDto.getWalletBalance()); // 账户余额
		account.setWithdrawAmount(accountDto.getWithdrawAmount()); // 提现中金额
		account.setCurrentInvestmentAmount(accountDto.getCurrentInvestmentAmount()); // 当前投资中金额
		account.setWaitInterest(accountDto.getWaitInterest()); // 待收利息
		account.setTotalInvestmentAmount(accountDto.getTotalInvestmentAmount()); // 累计投资金额
		return ApiResponse.successApiResponse(account);
	}

	/**
	 * 账号记录
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/account/record", method = RequestMethod.GET)
	public ApiResponse accountRecord(AccountMoneyRecordQuery query) {
		Long userId = getCurrentUserId();
		if (query.getType() != null) {
			if (query.getType().equals("profit")) {
			} else if (AccountMoneyRecordTypeEnum.fromCode(query.getType()) == null) {
				throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
			}

		}
		query.setUserId(userId);
		Pagination<AccountMoneyRecordDto> records = accountMoneyRecordClient.listPageByCondition(query);
		return ApiResponse.successApiResponse(records);
	}

	/**
	 * 充值确认
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/recharge/confirm", method = RequestMethod.GET)
	public ApiResponse rechargeConfirm() {
		Long userId = getCurrentUserId();
		Map<String, Object> param = new HashMap<String, Object>();
		AccountDto accountDto = accountClient.getByUserId(userId);

		UserBankDto userBank = userBankClient.getRecentUpdate(userId);
		// if (userBank == null) {
		// throw new BizException(ApiStatusEnum.NOT_BOUND_BRANK_CARD.getStatus(),
		// ApiStatusEnum.NOT_BOUND_BRANK_CARD.getMsg());
		// }
		param.put("walletBalance", accountDto.getWalletBalance()); // 可用余额
		param.put("userBank", userBank); // 银行卡
		return ApiResponse.successApiResponse(param);
	}

	/**
	 * 充值获取验证码
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/recharge/smsCode", method = RequestMethod.POST)
	public ApiResponse rechargeSmsCode(@RequestParam(value = "amount", required = true) Long amount, Long bankCodeId,
			String realName, String idCardNo, String bankCardNo, String mobile) {
		assertRechargeSmsCodeParam(bankCodeId, realName, idCardNo, bankCardNo, mobile);
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		boolean realNameAuth = userDto.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
		if (realNameAuth) {
			realName = userDto.getRealName();
			idCardNo = userDto.getIdCardNo();
		}
		if (bankCodeId == null || bankCodeId <= 0) {
			ApiResponse bindBankResponse = userClient.bindBankCode(userId, realName, idCardNo, bankCardNo, mobile,
					false);
			if (!bindBankResponse.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS)) {
				return bindBankResponse;
			} else {
				bankCodeId = Long.valueOf(bindBankResponse.getData().toString());
			}
		}
		String ip = IpUtils.getRemoteHost(getRequest());
		return accountClient.rechargeSmsCode(ip, userId, bankCodeId, amount, AccountRechargeTypeEnum.PERSON.getCode());
	}

	/**
	 * 断言参数正确
	 * 
	 * @param bankCodeId
	 * @param realName
	 * @param idCard
	 * @param bankCardNo
	 * @param mobile
	 */
	private void assertRechargeSmsCodeParam(Long bankCodeId, String realName, String idCardNo, String bankCardNo,
			String mobile) {

		if (bankCodeId != null && bankCodeId > 0) {
			if (!StringUtils.isAllBlank(realName, idCardNo, bankCardNo, mobile)) {
				throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
			}
		} else {
			if (StringUtils.isAllBlank(bankCardNo, mobile)) {
				throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
			}
		}
	}

	/**
	 * 充值
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public ApiResponse recharge(@RequestParam(value = "rechargeNum", required = true) String rechargeNum,
			@RequestParam(value = "smsCode", required = true) String smsCode) {
		Long userId = getCurrentUserId();
		String ip = IpUtils.getRemoteHost(getRequest());
		ApiResponse response = accountClient.recharge(ip, rechargeNum, smsCode);
		if (!response.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS)) {
			return response;
		} else {
			if (config.isOpenSmsSend()) {

				try {
					Long amount = Long.valueOf((String) response.getData());
					UserDto userDto = userClient.getById(userId);
					String content = MessageContent.getRechargeSuccessContent(amount);
					PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
							Arrays.asList(userDto.getMobile()), content);
					messageClient.send(message);
				} catch (Exception e) {
					LOGGER.error("recharge success sms send fail");
				}

			}
		}
		return response;
	}

	/**
	 * 充值记录
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/recharge/record", method = RequestMethod.GET)
	public ApiResponse rechargeRecord(AccountRechargeQuery query) {
		Long userId = getCurrentUserId();
		query.setUserId(userId);
		query.setExcludeStatus(AccountRechargeStatusEnum.UNPAID.getCode());
		Pagination<AccountRechargeDto> records = accountRechargeClient.listPageByCondition(query);
		return ApiResponse.successApiResponse(records);
	}

	/**
	 * 提现确认
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/withdraw/confirm", method = RequestMethod.GET)
	public ApiResponse withdrawConfirm(@RequestParam(value = "bankCodeId", defaultValue = "0") Long bankCodeId) {
		Long userId = getCurrentUserId();
		Map<String, Object> param = new HashMap<String, Object>();
		AccountDto accountDto = accountClient.getByUserId(userId);
		UserBankDto userBank = null;
		if (bankCodeId != 0) {
			userBank = userBankClient.getAvailable(bankCodeId);
		} else {
			userBank = userBankClient.getRecentUpdate(userId);
		}
		if (userBank == null) {
			return new ApiResponse(ApiStatusEnum.USER_BANK_CARD_NOT_EXIST);
		}

		// 确定可提现金额
		Long canWithdrawAmount = SincfinUtils.canWithdrawAmount(userBank, accountDto);

		// 确定是否有可用优惠券
		BigDecimal totalCouponMoney = couponUserClient.getTotalAmountByUserId(userId);
		Long canCouponAmount = 0L;
		if (totalCouponMoney.compareTo(BigDecimal.ZERO) > 0) {
			canCouponAmount = totalCouponMoney.longValue();
		}
		// 确定已提现的次数
		int countWithdraw = accountClient.countTodayWithdraw(userId);
		countWithdraw = 3 - countWithdraw;

		param.put("walletBalance", accountDto.getWalletBalance()); // 可用余额
		param.put("userBank", userBank); // 银行卡
		param.put("accountingDate", SincfinUtils.accountingDate(new Date())); // 预计到账时间
		param.put("canWithdrawAmount", canWithdrawAmount); // 可提现金额
		param.put("canCouponAmount", canCouponAmount); // 可用优惠券金额
		param.put("countWithdraw", countWithdraw); // 可提现次数

		return ApiResponse.successApiResponse(param);
	}

	/**
	 * 提现
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public ApiResponse withdraw(@RequestParam(value = "bankCodeId", required = true) Long bankCodeId,
			@RequestParam(value = "amount", required = true) Long amount) {
		Long userId = getCurrentUserId();
		if (amount <= 0) {
			return new ApiResponse(ApiStatusEnum.CLIENT_PARAM_ERROR);
		}
		ApiResponse response = accountClient.withdraw(userId, bankCodeId, amount);
		if (!response.getStatus().equals(ApiResponse.DEFAULT_STATUS_SUCCESS)) {
			return response;
		} else {
			if (config.isOpenSmsSend()) {
				try {
					UserDto userDto = userClient.getById(userId);
					String content = MessageContent.getWithdrawApplyContent(new Date(), amount);
					PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
							Arrays.asList(userDto.getMobile()), content);
					messageClient.send(message);
				} catch (Exception e) {
					LOGGER.error("recharge success sms send fail");
				}

			}
			response.setData(SincfinUtils.accountingDate(new Date()));
		}
		return response;
	}

	/**
	 * 提现记录
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/withdraw/record", method = RequestMethod.GET)
	public ApiResponse withdrawRecord(AccountWithdrawQuery query) {
		Long userId = getCurrentUserId();
		query.setUserId(userId);
		Pagination<AccountWithdrawDto> records = accountWithdrawClient.listPageByCondition(query);
		return ApiResponse.successApiResponse(records);
	}

	/**
	 * 银行卡列表
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/bankcode", method = RequestMethod.GET)
	public ApiResponse bankcode(UserBankQuery query) {
		Long userId = getCurrentUserId();
		query.setUserId(userId);
		Pagination<UserBankDto> list = userBankClient.listByUser(query);
		return ApiResponse.successApiResponse(list);
	}

	/**
	 * 绑卡确认
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/bind/bankcode/confirm", method = RequestMethod.GET)
	public ApiResponse bindBankCodeConfirm() {
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		/**
		 * 是否实名认证
		 */
		String realName = null;
		String idCard = null;
		boolean realNameAuth = userDto.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
		if (realNameAuth) {
			realName = StringUtils.realNameEncryption(userDto.getRealName());
			idCard = StringUtils.cardNoEncryption(userDto.getIdCardNo());
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("realNameAuth", realNameAuth);
		param.put("realName", realName);
		param.put("idCardNo", idCard);

		return ApiResponse.successApiResponse(param);
	}

	/**
	 * 绑卡
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/bind/bankcode", method = RequestMethod.POST)
	public ApiResponse bindBankCode(String realName, String idCardNo,
			@RequestParam(value = "bankCardNo", required = true) String bankCardNo,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "smsCode", required = true) String smsCode) {
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		String oSmsCode = getSmsCode(userDto.getMobile(), "bankcode");
		if (oSmsCode == null || !oSmsCode.equals(smsCode)) {
			throw new BizException(ApiStatusEnum.USER_SMSCODE_ERROR);
		}
		cleanSmsCode(mobile, "bankcode");
		boolean realNameAuth = userDto.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
		if (realNameAuth) {
			realName = userDto.getRealName();
			idCardNo = userDto.getIdCardNo();
		}
		ApiResponse response = userClient.bindBankCode(userDto.getId(), realName, idCardNo, bankCardNo, mobile, true);

		return response;
	}

	/**
	 * 解绑
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/unbind/bankcode", method = RequestMethod.POST)
	public ApiResponse unbindBankCode(@RequestParam(value = "bankCodeId", required = true) Long bankCodeId,
			Long disBankCodeId, String idCardFace, String idCardBack) {
		Long userId = getCurrentUserId();
		// AccountDto accountDto = accountClient.getByUserId(userId);
		UserBankDto userBank = userBankClient.getById(bankCodeId);
		if (userBank == null || !userBank.getUserId().equals(userId)) {
			return new ApiResponse(ApiStatusEnum.USER_BANK_CARD_NOT_EXIST);
		}
		// 提现中的卡不允许解绑
		AccountWithdrawQuery query = new AccountWithdrawQuery();
		query.setUserId(userId);
		query.setUserBankId(bankCodeId);
		query.setStatusList(
				Arrays.asList(AccountWithdrawStatusEnum.PENDING.getCode(), AccountWithdrawStatusEnum.PASS.getCode()));

		Pagination<AccountWithdrawDto> pagination = accountWithdrawClient.listPageByCondition(query);
		Assert.isTrue(pagination.getIsEmpty(), ApiStatusEnum.WITHDRAW_BANKCARD_FORBID_UNBIND);
		// 银行卡进账差
		Long diffAmount = userBank.getTotalRecharge() - userBank.getTotalWithdraw();
		if (diffAmount <= 0) {
			userBankClient.unbind(userBank.getId());
			if (config.isOpenSmsSend()) {
				// 发解绑验证码
				try {
					UserDto userDto = userClient.getById(userId);
					String content = MessageContent.getUntieBankCardSuccessContent();
					PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
							Arrays.asList(userDto.getMobile()), content);
					messageClient.send(message);
				} catch (Exception e) {
					LOGGER.error("recharge success sms send fail");
				}
			}
		} else {
			assertunbindBankCodeParam(disBankCodeId, idCardFace, idCardBack);
			userBankChangeClient.changeCardApply(bankCodeId, disBankCodeId, idCardFace, idCardBack);
			if (config.isOpenSmsSend()) {
				// 发换卡申请验证码
				try {
					UserDto userDto = userClient.getById(userId);
					String content = MessageContent.getChangeCardApplyContent();
					PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
							Arrays.asList(userDto.getMobile()), content);
					messageClient.send(message);
				} catch (Exception e) {
					LOGGER.error("recharge success sms send fail");
				}

			}
		}
		return ApiResponse.successApiResponse();
	}

	/**
	 * 可替换的银行卡列表
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/canReplaceBankCode", method = RequestMethod.GET)
	public ApiResponse canReplaceBankCode(UserBankQuery query) {
		Long userId = getCurrentUserId();

		UserBankDto userBank = userBankClient.getById(query.getReplaceBankCodeId());
		if (userBank == null || !userBank.getUserId().equals(userId)) {
			return new ApiResponse(ApiStatusEnum.USER_BANK_CARD_NOT_EXIST);
		}
		query.setUserId(userId);
		query.setBindStatus(UserBankBindStatusEnum.BIND.getCode());
		Pagination<UserBankDto> userBanks = userBankClient.listByUser(query);

		return ApiResponse.successApiResponse(userBanks);
	}

	/**
	 * 断言参数正确
	 * 
	 * @param bankCodeId
	 * @param realName
	 * @param idCard
	 * @param bankCardNo
	 * @param mobile
	 */
	private void assertunbindBankCodeParam(Long disBankCodeId, String idCardFace, String idCardBack) {

		if (disBankCodeId == null || StringUtils.isEmpty(idCardBack) || StringUtils.isEmpty(idCardFace)) {
			throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
		}
		UserBankDto userBank = userBankClient.getById(disBankCodeId);
		if (userBank == null) {
			throw new BizException(ApiStatusEnum.USER_BANK_CARD_NOT_EXIST);
		}
	}

	/**
	 * 我的优惠券
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/coupon", method = RequestMethod.GET)
	public ApiResponse coupon(CouponUserQuery query) {
		Long userId = getCurrentUserId();
		if (query.getStatus() != null && CouponUserStatusEnum.fromCode(query.getStatus()) == null) {
			throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
		}
		query.setUserId(userId);

		Pagination<CouponUserDto> pageCoupons = couponUserClient.listByUserId(query);
		List<CouponUserDto> listCopons = pageCoupons.getData();
		List<CouponUserVo> listCoponVos = new ArrayList<CouponUserVo>();
		for (CouponUserDto dto : listCopons) {
			CouponUserVo vo = new CouponUserVo();
			BeanHelper.copyProperties(vo, dto);
			vo.setProductTypeList(CouponUserVo.parseType(dto.getProductTypeList()));
			CouponTriggerConditionEnum conditionEnum = CouponTriggerConditionEnum.fromCode(vo.getTriggerCondition());
			if (conditionEnum != null) {
				vo.setTriggerCondition(conditionEnum.getText());
			}
			listCoponVos.add(vo);
		}

		return ApiResponse.successApiResponse(new Pagination(query, listCoponVos, pageCoupons.getTotalCount()));
	}

	/**
	 * 投资记录
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "/investment/record", method = RequestMethod.GET)
	public ApiResponse investmentRecord(OrderQuery query) {
		Long userId = getCurrentUserId();
		query.setUserId(userId);
		Pagination<OrderDto> orderDtos = userClient.investmentRecord(query);
		return ApiResponse.successApiResponse(orderDtos);
	}

	/**
	 * 用户风险评测,数据上报
	 *
	 * @return
	 */
	@Auth
	@CrossOrigin
	@RequestMapping(value = "/update/riskTest", method = RequestMethod.POST)
	public ApiResponse updateRiskTest(Integer riskType) {
		Long userId = getCurrentUserId();
		if (UserRistTestEnum.fromCode(riskType) == null) {
			throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
		}
		userClient.updateRiskTest(userId, riskType);
		return ApiResponse.successApiResponse();
	}

	/**
	 * 用户风险评测
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "riskTest", method = RequestMethod.GET)
	public ApiResponse riskTest() {
		return ApiResponse.successApiResponse(config.getRiskTestUrl("riskTest"));
	}

	/**
	 * 个人中心设置
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "setting", method = RequestMethod.GET)
	public ApiResponse setting() {
		Long userId = getCurrentUserId();
		UserDto userDto = userClient.getById(userId);
		Map<String, Object> param = new HashMap<String, Object>();

		/**
		 * 是否实名认证
		 */
		String realName = null;
		String idCard = null;
		boolean realNameAuth = userDto.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
		if (realNameAuth) {
			realName = StringUtils.realNameEncryption(userDto.getRealName());
			idCard = StringUtils.cardNoEncryption(userDto.getIdCardNo());
		}

		param.put("realNameAuth", realNameAuth);
		param.put("realName", realName);
		param.put("idCard", idCard);

		/**
		 * 是否绑定银行卡
		 */
		boolean bindBankCode = userClient.isBindBankCode(userDto.getId());
		param.put("bindBankCode", bindBankCode);

		param.put("mobile", StringUtils.mobileEncryption(userDto.getMobile()));

		UserRistTestEnum reskEnum = UserRistTestEnum.fromCode(userDto.getRiskType());
		boolean riskTest = reskEnum != null;
		String riskType = null;
		if (riskTest) {
			riskType = reskEnum.getText();
		}
		param.put("riskTest", riskTest);
		param.put("riskType", riskType);
		param.put("riskTestURL", config.getRiskTestUrl("riskTest"));

		return ApiResponse.successApiResponse(param);
	}

	/**
	 * 邀友记录
	 *
	 * @return
	 */
	@Auth
	@RequestMapping(value = "invitation/record", method = RequestMethod.GET)
	public ApiResponse invitationRecord(UserQuery query) {
		Long userId = getCurrentUserId();
		query.setId(userId);
		Pagination<InvitationRecordDto> invitationRecords = userClient.invitationRecord(query);
		return ApiResponse.successApiResponse(invitationRecords);
	}

	private void sendSmsCode(String mobile, String type) {
		String smsCode = null;
		// 如果不开启验证默认验证码为“000000”
		if (config.isSmsCodeValidate()) {
			smsCode = RandomUtil.getRandom(6);
			String content = MessageContent.getSmsCodeContent(smsCode);
			PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(), Arrays.asList(mobile), content);
			messageClient.send(message);
		} else {
			smsCode = config.getSmsCodeDefaultValue();
		}

		redisTemplate.opsForValue().set(RedisConstants.getsmsCodeKey(mobile, type), smsCode,
				RedisConstants.SMSCODE_EXPIRE_TIME, TimeUnit.SECONDS);
	}

	private String getSmsCode(String mobile, String type) {
		return redisTemplate.opsForValue().get(RedisConstants.getsmsCodeKey(mobile, type));
	}

	private Boolean cleanSmsCode(String mobile, String type) {
		return redisTemplate.delete(RedisConstants.getsmsCodeKey(mobile, type));
	}

	// /**
	// * <p> 用户获取临时Aliyun OSS Token </p>
	// *
	// * @return
	// */
	// @PostMapping(value = "/securityToken")
	// public ApiResponse securityToken(@RequestParam(value = "category") int
	// category) {
	// assertLogin();
	// return
	// ApiResponse.successApiResponse(fileObjectClient.securityToken(category));
	// }
	//
	// /**
	// * <p> aliyun oss callback </p>
	// *
	// * @param request
	// * @param response
	// */
	// @PostMapping(value = "/aliyunCallback")
	// public void aliyunCallback(HttpServletRequest request, HttpServletResponse
	// response) {
	// try {
	//
	// String ossCallbackBody =
	// AliyunOSSUtils.getCallbackData(request.getInputStream(),
	// Integer.parseInt(request.getHeader("content-length")));
	//
	// boolean ret = AliyunOSSUtils.verifyOSSCallbackRequest(request,
	// ossCallbackBody);
	//
	// if (!ret)
	// AliyunOSSUtils.response(request, response, "{\"status\":\"verdify not ok\"}",
	// HttpServletResponse.SC_BAD_REQUEST);
	//
	// AliyunCallbackDto aliyunCallbackDto = JacksonUtil.readValue(ossCallbackBody,
	// AliyunCallbackDto.class);
	//
	// try {
	// if (aliyunCallbackDto != null)
	// fileObjectClient.addUserFileObject(aliyunCallbackDto);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// logger.error("aliyun oss callback app server url error []", e);
	// AliyunOSSUtils.response(request, response, "{\"status\": 500}",
	// HttpServletResponse.SC_BAD_REQUEST);
	// }
	//
	// AliyunOSSUtils.response(request, response, "{\"status\": 200}",
	// HttpServletResponse.SC_OK);
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// @PostMapping(value = "/loadFile")
	// public ApiResponse loadFile(@RequestParam(value = "id", required = true) Long
	// id) {
	//
	// return ApiResponse.successApiResponse(fileObjectClient.getFileObject(id));
	// }

}