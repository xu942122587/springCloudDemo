package com.ruixun.sincfin.biz.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.facade.UserFacade;
import com.ruixun.sincfin.biz.feign.client.UserClient;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMapper;
import com.ruixun.sincfin.biz.module.basic.mapper.BankMapper;
import com.ruixun.sincfin.biz.module.basic.mapper.ChannelMapper;
import com.ruixun.sincfin.biz.module.coupon.mapper.CouponMapper;
import com.ruixun.sincfin.biz.module.coupon.mapper.CouponUserMapper;
import com.ruixun.sincfin.biz.module.order.mapper.OrderMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserMapper;
import com.ruixun.sincfin.biz.pay.fuiou.FuiouPayHelper;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayRequest;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayResponse;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.lock.RedisLock;
import com.ruixun.sincfin.common.lock.RedisLockParam;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.common.util.Snowflake;
import com.ruixun.sincfin.common.util.http.HttpClientUtil;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.InvitationRecordDto;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.BankEntity;
import com.ruixun.sincfin.domain.entity.ChannelEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.entity.UserEntity;
import com.ruixun.sincfin.domain.enums.CouponTriggerConditionEnum;
import com.ruixun.sincfin.domain.enums.UserAccountTypeEnum;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.enums.UserRealNameAuthEnum;
import com.ruixun.sincfin.domain.enums.UserStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.OrderQuery;
import com.ruixun.sincfin.domain.query.UserQuery;

/**
 * @author t
 * @date 2018/5/18 17:45
 */
@RestController
@RequestMapping("user")
public class UserService implements UserClient {

	private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private UserBankMapper userBankMapper;
	@Resource
	private BankMapper bankMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private FuiouPayHelper fuiouPayHelper;
	@Resource
	private ChannelMapper channelMapper;

	@Resource
	private CouponMapper couponMapper;
	@Resource
	private CouponUserMapper couponUserMapper;

	@Resource
	private UserFacade userFacade;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public UserDto add(@RequestBody UserDto userDto) {

		UserEntity userEntity = new UserEntity();
		BeanHelper.copyProperties(userEntity, userDto);
		userEntity.setRealNameAuth(UserRealNameAuthEnum.UNAUTH.getCode());
		// userEntity.setRiskType(0);
		userEntity.setAccountType(UserAccountTypeEnum.NORMAL.getCode());
		userEntity.setStatus(UserStatusEnum.NORMAL.getCode());
		userMapper.insertSelective(userEntity);
		userDto.setId(userEntity.getId());
		return userDto;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public void register(@RequestBody UserDto userDto) {
		userDto = this.add(userDto);
		addAccount(userDto);
		userFacade.sendCoupon(userDto.getId(), CouponTriggerConditionEnum.NEW_REGISTER.getCode());
		if (userDto.getInviterId() != null && userDto.getInviterId() != 0) {
			userFacade.sendCoupon(userDto.getInviterId(), CouponTriggerConditionEnum.INVITES_500.getCode());
		}
	}

	private AccountEntity addAccount(UserDto userDto) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setUserId(userDto.getId());
		accountMapper.insertSelective(accountEntity);
		return accountEntity;
	}

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public UserDto getById(@RequestParam Long id) {

		UserEntity userEntity = userMapper.selectByPrimaryKey(id);
		if (userEntity == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		BeanHelper.copyProperties(userDto, userEntity);
		AccountEntity accountEntity = accountMapper.getByUserId(userDto.getId());
		if (accountEntity == null) {
			return userDto;
		}

		userDto.setWalletBalance(accountEntity.getWalletBalance());
		userDto.setCurrentInvestmentAmount(accountEntity.getCurrentInvestmentAmount());
		if (userEntity.getChannelId() != null) {
			ChannelEntity channelEntity = channelMapper.selectByPrimaryKey(userEntity.getChannelId());
			userDto.setChannelName(channelEntity.getName());
		}
		OrderDto firstOrder = orderMapper.getFirstOrderByUserId(id);
		if (firstOrder != null) {
			userDto.setFirstInvestmentFlag(true);
			userDto.setGmtFirstInvestment(firstOrder.getGmtCreate());
		} else {
			userDto.setFirstInvestmentFlag(false);
		}

		return userDto;
	}

	@RequestMapping(value = "getByMobile", method = RequestMethod.GET)
	public UserDto getByMobile(@RequestParam String mobile) {

		UserEntity userEntity = userMapper.getByMobile(mobile);
		if (userEntity == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		BeanHelper.copyProperties(userDto, userEntity);
		return userDto;

	}

	@RequestMapping(value = "listManagerPage", method = RequestMethod.POST)
	public Pagination<UserDto> listManagerPage(@RequestBody UserQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());

		List<UserDto> userDtoList = userMapper.listManagerByCondition(query);
		PageInfo pageInfo = new PageInfo(userDtoList);

		return new Pagination(query, userDtoList, (int) pageInfo.getTotal());

	}

	@RequestMapping(value = "insertGhost", method = RequestMethod.POST)
	@Transactional
	public UserDto insertGhost(@RequestBody UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setRealName(userDto.getRealName());
		userEntity.setMobile(userDto.getMobile());
		userEntity.setAccountType(UserAccountTypeEnum.GHOST.getCode());
		userMapper.insertSelective(userEntity);
		userDto.setId(userEntity.getId());
		addAccount(userDto);
		return userDto;

	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	@Transactional
	public int updatePassword(@RequestParam("userId") Long userId, @RequestParam("password") String password) {
		UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
		if (userEntity == null) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_NOT_EXIST);
		}
		String pw = DigestUtils.md5Hex(password);
		userEntity.setPassword(pw);
		return userMapper.updateByPrimaryKeySelective(userEntity);

	}

	@RequestMapping(value = "updateMobile", method = RequestMethod.POST)
	@Transactional
	@RedisLock(prefix = "user:mobile:")
	public int updateMobile(Long userId, @RedisLockParam String mobile) {

		UserEntity userEntity = userMapper.getByMobile(mobile);
		if (userEntity != null && !userId.equals(userEntity.getId())) {
			throw new BizException(ApiStatusEnum.USER_MOBILE_ALREADY_EXIST);
		}

		userEntity = userMapper.selectByPrimaryKey(userId);
		if (userEntity == null) {
			throw new BizException(ApiStatusEnum.USER_ID_NOT_EXIST);
		}
		UserEntity updateUserEntity = new UserEntity();
		updateUserEntity.setId(userId);
		updateUserEntity.setMobile(mobile);
		return userMapper.updateByPrimaryKeySelective(updateUserEntity);
	}

	@RequestMapping(value = "updateStatus")
	public int updateStatus(Long userId, String status) {

		UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
		if (userEntity == null) {
			throw new BizException(ApiStatusEnum.USER_ID_NOT_EXIST);
		}
		userEntity = new UserEntity();
		userEntity.setId(userId);
		userEntity.setStatus(status);
		return userMapper.updateByPrimaryKeySelective(userEntity);
	}

	@RequestMapping(value = "bindBankCode", method = RequestMethod.POST)
	public ApiResponse bindBankCode(@RequestParam("userId") Long userId, @RequestParam("realName") String realName,
			@RequestParam("idCardNo") String idCardNo, @RequestParam("bankCardNo") String bankCardNo,
			@RequestParam("mobile") String mobile, @RequestParam("isBind") Boolean isBind) {

		UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
		boolean realNameAuth = userEntity.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
		if (!realNameAuth) {
			// 判断身份证，已经实名认证其他人
			UserEntity realuserEntity = userMapper.getByIdCord(idCardNo);
			if (realuserEntity != null) {
				return new ApiResponse(ApiStatusEnum.USER_ID_CARD_EXIST);
			}
		}
		// 判断银行卡没有别其他人绑定
		UserBankEntity userBankEntity = userBankMapper.getByCard(bankCardNo);
		if (userBankEntity != null) {
			return new ApiResponse(ApiStatusEnum.USER_BANK_CARD_EXIST);
		}

		FuiouPayRequest request = fuiouPayHelper.bindBankRequest(realName, idCardNo, bankCardNo, mobile);
		try {
			FuiouPayResponse response = fuiouPayHelper.checkCard(userId, request);
			// 返回“0000”表示成功
			if (response.getResponseCode().equals("0000")) {
				// 添加银行卡
				userBankEntity = addBankCode(userId, realName, idCardNo, bankCardNo, response.getBankName(), mobile,
						isBind);
				// 实名认证
				realNameAuth(userEntity, realName, idCardNo);
			} else {
				return new ApiResponse(ApiStatusEnum.USER_BIND_BANK_FAIL.getStatus(),
						response.getResponseMsg() + "[" + response.getResponseCode() + "]");
			}

		} catch (Exception e) {
			LOGGER.error("user bind bank code fail msg : " + e.getMessage());
			return new ApiResponse(ApiStatusEnum.COMMON_BIZ_SERVICE_ERROR);
		}
		return ApiResponse.successApiResponse(userBankEntity.getId());
	}

	/**
	 * 银行卡名称转PayCode服务
	 * 
	 * @param bank_name
	 * @return
	 */
	public String bankNameToPayCode(String bankBame) {
		List<BankEntity> banks = bankMapper.getBankByBankName(bankBame);
		if (banks.size() > 0) {
			return banks.get(0).getCode();
		}
		return null;
	}

	/**
	 * 实名认证
	 */
	private void realNameAuth(UserEntity userEntity, String realName, String idCard) {
		if (!userEntity.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode())) {
			userEntity.setRealNameAuth(UserRealNameAuthEnum.AUTH.getCode());
			userEntity.setRealName(realName);
			userEntity.setIdCardNo(idCard);
			userMapper.updateByPrimaryKeySelective(userEntity);
		}
	}

	/**
	 * 添加银行卡
	 * 
	 * @param userId
	 * @param realName
	 * @param idCard
	 * @param bankCardNo
	 * @param bankName
	 * @param mobile
	 */
	private UserBankEntity addBankCode(Long userId, String realName, String idCard, String bankCardNo, String bankName,
			String mobile, Boolean isBind) {
		UserBankEntity userBankEntity = new UserBankEntity();
		userBankEntity.setUserId(userId);
		userBankEntity.setBankCardNo(bankCardNo);
		userBankEntity.setIdCardNo(idCard);
		userBankEntity.setMobile(mobile);
		userBankEntity.setRealName(realName);
		userBankEntity.setBankName(bankName);
		String code = this.bankNameToPayCode(bankName);
		if (StringUtils.isBlank(code)) {
			try {
				String rs = HttpClientUtil.doGet("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?cardNo="
						+ bankCardNo + "&cardBinCheck=true");
				JSONObject obj = JSONObject.parseObject(rs);
				code = (obj == null ? "" : obj.getString("bank"));
			} catch (Exception e) {
				LOGGER.error("调用卡宾校验出错异常:{}", e);
			}
		}
		userBankEntity.setBankCode(code);
		userBankEntity.setBindMerch("fuiou");
		userBankEntity.setWithdrawLimit(0L);
		if (isBind) {
			userBankEntity.setBindStatus(UserBankBindStatusEnum.BIND.getCode());
		} else {
			userBankEntity.setBindStatus(UserBankBindStatusEnum.UNBIND.getCode());
		}
		String orderNum = Snowflake.getFlowSnowflakeInstance().nextId(ConfigConstants.SN_FOIOU,userId);
		userBankEntity.setFuiouUserId(orderNum);
		userBankMapper.insertSelective(userBankEntity);
		return userBankEntity;
	}

	@RequestMapping(value = "investment/record", method = RequestMethod.POST)
	public Pagination<OrderDto> investmentRecord(@RequestBody OrderQuery query) {
		PageHelper.startPage(query.getPageIndex(), query.getPageSize());

		List<OrderDto> orders = orderMapper.getOrderByUser(query);
		PageInfo pageInfo = new PageInfo(orders);
		return new Pagination(query, orders, (int) pageInfo.getTotal());
	}

	/**
	 * 更新用户风险评测
	 * 
	 * @param userId
	 * @param riskType
	 */
	@RequestMapping(value = "update/riskTest", method = RequestMethod.POST)
	public int updateRiskTest(@RequestParam("userId") Long userId, @RequestParam("riskType") Integer riskType) {
		return userMapper.updateRiskTest(userId, riskType);
	}

	/**
	 * 判断用户是否绑定银行卡
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "isBindBankCode", method = RequestMethod.GET)
	public boolean isBindBankCode(@RequestParam("userId") Long userId) {
		return userBankMapper.isBindBankCode(userId);
	}

	/**
	 * 查询邀友记录
	 * 
	 * @param query
	 * @return
	 */

	@RequestMapping(value = "invitationRecord", method = RequestMethod.POST)
	public Pagination<InvitationRecordDto> invitationRecord(@RequestBody UserQuery query) {
		PageHelper.startPage(query.getPageIndex(), query.getPageSize());

		List<InvitationRecordDto> records = userMapper.listInvitationRecord(query);
		PageInfo pageInfo = new PageInfo(records);
		return new Pagination(query, records, (int) pageInfo.getTotal());
	}

	/**
	 * 判断用户是否是新手
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "isNewUser", method = RequestMethod.GET)
	public boolean isNewUser(@RequestParam("userId") Long userId) {
		return userMapper.isNewUser(userId);
	}

	@Override
	@RequestMapping("firstInvestmentFlagTask")
	public int firstInvestmentFlagTask() {
		return userMapper.updateFirstInvestmentByTask();
	}

}
