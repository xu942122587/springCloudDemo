package com.ruixun.sincfin.biz.module.product.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.facade.UserFacade;
import com.ruixun.sincfin.biz.feign.client.ProductClient;
import com.ruixun.sincfin.biz.mapper.ProductExtendMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMoneyRecordMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountRechargeMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountRepaymentMapper;
import com.ruixun.sincfin.biz.module.basic.mapper.BankMapper;
import com.ruixun.sincfin.biz.module.coupon.mapper.CouponUserMapper;
import com.ruixun.sincfin.biz.module.file.service.FileObjectService;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserMapper;
import com.ruixun.sincfin.biz.module.finuser.service.FinancingUserService;
import com.ruixun.sincfin.biz.module.order.mapper.OrderCouponMapper;
import com.ruixun.sincfin.biz.module.order.mapper.OrderMapper;
import com.ruixun.sincfin.biz.module.order.mapper.OrderProductMapper;
import com.ruixun.sincfin.biz.module.product.mapper.ProductMapper;
import com.ruixun.sincfin.biz.module.product.mapper.ProductRepaymentMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserMapper;
import com.ruixun.sincfin.biz.pay.fuiou.FuiouPayHelper;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.lock.RedisLock;
import com.ruixun.sincfin.common.lock.RedisLockParam;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.common.util.SincfinUtils;
import com.ruixun.sincfin.common.util.Snowflake;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.CouponUserDto;
import com.ruixun.sincfin.domain.dto.FinancingUserCountDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.dto.PayConfirmDto;
import com.ruixun.sincfin.domain.dto.ProductCategoryDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.ProductIntroduceDto;
import com.ruixun.sincfin.domain.dto.ProductViewDto;
import com.ruixun.sincfin.domain.dto.UserBankDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.AccountMoneyRecordEntity;
import com.ruixun.sincfin.domain.entity.AccountRepaymentEntity;
import com.ruixun.sincfin.domain.entity.BankEntity;
import com.ruixun.sincfin.domain.entity.CouponUserEntity;
import com.ruixun.sincfin.domain.entity.OrderCouponEntity;
import com.ruixun.sincfin.domain.entity.OrderEntity;
import com.ruixun.sincfin.domain.entity.OrderProductEntity;
import com.ruixun.sincfin.domain.entity.ProductEntity;
import com.ruixun.sincfin.domain.entity.ProductExtend;
import com.ruixun.sincfin.domain.entity.ProductRepaymentEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.entity.UserEntity;
import com.ruixun.sincfin.domain.enums.AccountMoneyRecordTypeEnum;
import com.ruixun.sincfin.domain.enums.AccountRepaymentStatusEnum;
import com.ruixun.sincfin.domain.enums.ContractRepaymentTypeEnum;
import com.ruixun.sincfin.domain.enums.CouponTriggerConditionEnum;
import com.ruixun.sincfin.domain.enums.CouponUserStatusEnum;
import com.ruixun.sincfin.domain.enums.FinancingUserRepaymentStatusEnum;
import com.ruixun.sincfin.domain.enums.OrderStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductCategoryEnum;
import com.ruixun.sincfin.domain.enums.ProductRemittanceStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductRepaymentStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductTypeEnum;
import com.ruixun.sincfin.domain.enums.ProductValueDateMethodEnum;
import com.ruixun.sincfin.domain.enums.UserAccountTypeEnum;
import com.ruixun.sincfin.domain.enums.UserRealNameAuthEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.OrderQuery;
import com.ruixun.sincfin.domain.query.ProductQuery;

@RestController
@RequestMapping("/product")
public class ProductService implements ProductClient {

	private Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Resource
	private ProductMapper productMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderProductMapper orderProductMapper;
	@Resource
	private OrderCouponMapper orderCouponMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private UserBankMapper userBankMapper;
	@Resource
	private CouponUserMapper couponUserMapper;
	@Resource
	private AccountMoneyRecordMapper accountMoneyRecordMapper;
	@Resource
	private FinancingUserMapper financingUserMapper;
	@Resource
	private FuiouPayHelper fuiouPayHelper;
	@Resource
	private AccountRechargeMapper accountRechargeMapper;
	@Resource
	private BankMapper bankMapper;
	@Resource
	private ProductRepaymentMapper productRepaymentMapper;
	@Resource
	private AccountRepaymentMapper accountRepaymentMapper;
	@Resource
	private FileObjectService fileObjectService;

	@Resource
	private FinancingUserService financingUserService;

	@Resource
	private ProductExtendMapper productExtendMapper;

	@Resource
	private UserFacade userfacade;

	/**
	 * 添加
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ProductDto add(@RequestBody ProductDto dto) {
		ProductEntity entity = new ProductEntity();
		BeanHelper.copyProperties(entity, dto);
		productMapper.insertSelective(entity);
		dto.setId(entity.getId());
		return dto;
	}

	/**
	 * 删除
	 *
	 * @param productDto
	 */
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public void delete(@RequestBody ProductDto productDto) {
		// TODO
	}

	/**
	 * 更新
	 *
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public int update(@RequestBody ProductDto dto) {

		ProductEntity productEntity = productMapper.selectByPrimaryKey(dto.getId());
		if (productEntity == null) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST);
		}
		if (!ProductStatusEnum.WAIT_EDIT.getCode().equals(productEntity.getStatus())
				&& !ProductStatusEnum.WAIT_SELL.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_EDITABLE);
		}
		if (StringUtils.isNotEmpty(dto.getTitle())) {
			ProductQuery productQuery = new ProductQuery();
			productQuery.setTitle(dto.getTitle());
			List<ProductEntity> productEntityList = productMapper.listByCondition(productQuery);
			if (CollectionUtils.isNotEmpty(productEntityList)
					&& !productEntityList.get(0).getId().equals(dto.getId())) {
				throw new BizException(ApiStatusEnum.PRODUCE_TITLE_EXIST);
			}
		}

		dto.setAmount(null);
		dto.setStatus(ProductStatusEnum.WAIT_SELL.getCode());
		if (dto.getTotalInterestRate() != null && dto.getSubsidyInterestRate() != null) {
			dto.setOriginalInterestRate(dto.getTotalInterestRate().subtract(dto.getSubsidyInterestRate()));
		}

		ProductEntity updateProductEntity = new ProductEntity();
		BeanHelper.copyProperties(updateProductEntity, dto);
		return productMapper.updateByPrimaryKeySelective(updateProductEntity);
	}

	/**
	 * 根据id查找
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public ProductDto getById(@RequestParam("id") Long id) {

		ProductDto dto = productMapper.getFullById(id);

		return dto;
	}

	/**
	 * 管理平台详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getDetailById", method = RequestMethod.GET)
	public ProductDto getDetailById(@RequestParam("id") Long id) {
		ProductDto dto = productMapper.getDetailById(id);
		return dto;
	}

	/**
	 * 管理后台产品列表
	 *
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "listPageByCondition", method = RequestMethod.POST)
	public Pagination<ProductDto> listPageByCondition(@RequestBody ProductQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<ProductDto> productDtoList = productMapper.listManagerByCondition(query);
		PageInfo pageInfo = new PageInfo(productDtoList);

		return new Pagination(query, productDtoList, (int) pageInfo.getTotal());
	}

	/**
	 * 管理后台产品搜索列表
	 *
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "listSearch", method = RequestMethod.POST)
	public List<ProductDto> listSearch(@RequestBody ProductQuery query) {
		return productMapper.listSearch(query);
	}

	/**
	 * 上标
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("shelve")
	public int shelve(@RequestParam("id") Long id) {
		ProductEntity productEntity = productMapper.selectByPrimaryKey(id);
		if (productEntity == null) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST);
		}
		if (!ProductStatusEnum.WAIT_SELL.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_SHELVE);
		}

		ProductEntity updateProductEntity = new ProductEntity();
		updateProductEntity.setId(id);
		updateProductEntity.setStatus(ProductStatusEnum.ON_SALE.getCode());
		return productMapper.updateByPrimaryKeySelective(updateProductEntity);
	}

	/**
	 * 下标
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("unshelve")
	public int unshelve(@RequestParam("id") Long id) {
		ProductEntity productEntity = productMapper.selectByPrimaryKey(id);
		if (productEntity == null) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST);
		}
		if (!ProductStatusEnum.ON_SALE.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_UNSHELVE);
		}
		if (productEntity.getSoldAmount() > 0) {
			throw new BizException(ApiStatusEnum.PRODUCE_SELL_NOT_UNSHELVE);
		}

		ProductEntity updateProductEntity = new ProductEntity();
		updateProductEntity.setId(id);
		updateProductEntity.setStatus(ProductStatusEnum.WAIT_SELL.getCode());
		return productMapper.updateByPrimaryKeySelective(updateProductEntity);
	}

	/**
	 * 推荐的标第
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("getRecommend")
	public ProductDto getRecommend(Long userId) {

		ProductDto productDto = null;
		boolean isNewUser = true;
		// 登陆用户和非登陆用户推荐不同
		if (userId != null) {
			if (!userMapper.isNewUser(userId)) {
				isNewUser = false;
			}
		}
		if (isNewUser) {
			productDto = productMapper.getNewUserRecommend();
		} else {
			productDto = productMapper.getOldUserRecommend();
		}
		return productDto;
	}

	/**
	 * 标列表，按分类划分不同区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<ProductCategoryDto> list(Long userId) {

		boolean isNewUser = true;
		// 判断是否是新用户
		if (userId != null) {
			if (!userMapper.isNewUser(userId)) {
				isNewUser = false;
			}
		}

		List<ProductCategoryDto> projectCategorys = new ArrayList<ProductCategoryDto>();
		List<ProductDto> products = null;
		ProductCategoryDto projectCategory = null;

		for (ProductCategoryEnum productCategoryEnum : ProductCategoryEnum.values()) {
			projectCategory = new ProductCategoryDto(productCategoryEnum);

			products = listByTypeArea(productCategoryEnum);
			List<ProductViewDto> productViews = new ArrayList<ProductViewDto>();
			products.forEach(entity -> {
				ProductViewDto dto = new ProductViewDto();
				BeanHelper.copyProperties(dto, entity);
				if (dto.getGmtRelease().compareTo(new Date()) > 0) {
					if (!dto.getPresellFlag()) {
						return;
					}
				}
				productViews.add(dto);
			});

			projectCategory.setProducts(productViews);

			switch (productCategoryEnum) {
			case NEW_USER_AREA:
				projectCategory.setCategorySort(isNewUser ? 1 : 3);
				break;
			case ACTIVITY_AREA:
				projectCategory.setCategorySort(isNewUser ? 2 : 1);
				break;
			case SELECT_AREA:
				projectCategory.setCategorySort(isNewUser ? 3 : 2);
				break;
			case SELLOUT_AREA:
				projectCategory.setCategorySort(isNewUser ? 4 : 4);
				break;
			}

			if (productViews.size() > 0) {
				projectCategorys.add(projectCategory);
			}
		}
		Collections.sort(projectCategorys);
		return projectCategorys;

	}

	private List<ProductDto> listByTypeArea(ProductCategoryEnum productCategoryEnum) {
		List<ProductDto> products = null;
		if (productCategoryEnum != ProductCategoryEnum.SELLOUT_AREA) {
			products = productMapper.listByTypeArea(productCategoryEnum.getCode());
		} else {
			PageHelper.startPage(1, 2);
			products = productMapper.listSellOut();
		}
		return products;

	}

	/**
	 * 已售罄列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "sellOut", method = RequestMethod.POST)
	public Pagination<ProductViewDto> sellOut(@RequestBody ProductQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());

		List<ProductDto> products = productMapper.listSellOut();

		PageInfo pageInfo = new PageInfo(products);

		List<ProductViewDto> productViews = new ArrayList<ProductViewDto>();
		products.forEach(entity -> {
			ProductViewDto dto = new ProductViewDto();
			BeanHelper.copyProperties(dto, entity);
			productViews.add(dto);
		});

		return new Pagination(query, productViews, (int) pageInfo.getTotal());
	}

	/**
	 * 产品的投资记录
	 * 
	 * @param productId
	 */
	@RequestMapping(value = "/investment/record", method = RequestMethod.POST)
	public Pagination<OrderDto> investmentRecord(@RequestBody OrderQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());

		List<OrderDto> orders = orderMapper.getOrderByProduct(query);

		for (OrderDto orderDto : orders) {
			orderDto.setUserMobile(StringUtils.mobileEncryption(orderDto.getUserMobile()));
		}

		PageInfo pageInfo = new PageInfo(orders);

		return new Pagination(query, orders, (int) pageInfo.getTotal());

	}

	/**
	 * 统计投资人数
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "countInvestmentUser", method = RequestMethod.GET)
	public long countInvestmentUser(@RequestParam("id") Long id) {
		return orderMapper.countInvestmentUser(id);
	}

	/**
	 * 支付确认
	 * 
	 * @param userId
	 * @param productId
	 * @param couponUserId
	 * @param amount
	 * @return
	 */
	@RequestMapping(value = "/investment/pay/confirm", method = RequestMethod.GET)
	public ApiResponse investmentPayConfirm(@RequestParam("userId") Long userId,
			@RequestParam("productId") Long productId, @RequestParam("couponUserId") Long couponUserId,
			@RequestParam("amount") Long amount, @RequestParam("needBank") Boolean needBank) {

		PayConfirmDto payConfirmDto = null;

		ProductDto productDto = null;
		AccountEntity accountEntity = null;
		CouponUserDto couponUserDto = null;
		try {

			UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
			if (userEntity == null) {
				throw new BizException(ApiStatusEnum.USER_ID_NOT_EXIST);
			}

			productDto = productMapper.getAvailable(productId);
			assertProductAvailable(productDto, amount, userId);

			if (couponUserId != null && couponUserId != 0) {
				couponUserDto = couponUserMapper.getCouponById(couponUserId);
				assertCouponUsable(userId, couponUserId, couponUserDto, productDto, amount);
			}
			accountEntity = accountMapper.getByUserId(userId);

			payConfirmDto = getPayConfirmInfo(productId, couponUserId, userEntity, accountEntity, couponUserDto,
					amount);
			if (needBank) {
				UserBankEntity entity = userBankMapper.selectRecentUpdate(userId);
				if (entity != null) {
					UserBankDto userBank = new UserBankDto();
					BeanHelper.copyProperties(userBank, entity);
					payConfirmDto.setUserBank(userBank);
				}

			}
		} catch (BizException be) {
			return new ApiResponse(be.getExceptionCode(), be.getMessage());
		} catch (Exception e) {
			LOGGER.error("investment validate execption msg : " + e.getMessage());
			return new ApiResponse(ApiStatusEnum.COMMON_SERVICE_ERROR);
		}
		return ApiResponse.successApiResponse(payConfirmDto);
	}

	private PayConfirmDto getPayConfirmInfo(Long productId, Long couponUserId, UserEntity userEntity,
			AccountEntity accountEntity, CouponUserDto couponUserDto, Long amount) {
		PayConfirmDto payConfirmDto = new PayConfirmDto();

		// 设置投资金额
		payConfirmDto.setInvestmentAmount(amount);
		payConfirmDto.setProductId(productId == null ? 0 : productId);
		payConfirmDto.setCouponUserId(couponUserId == null ? 0 : couponUserId);
		if (couponUserDto != null) {
			payConfirmDto.setCouponPayAmount(couponUserDto.getAmount());
		}
		payConfirmDto.setPayAmount(payConfirmDto.getInvestmentAmount() - payConfirmDto.getCouponPayAmount());
		if (accountEntity.getWalletBalance() < payConfirmDto.getPayAmount()) {
			payConfirmDto.setWalletPayAmount(accountEntity.getWalletBalance());
			payConfirmDto.setBankCodePayAmount(payConfirmDto.getPayAmount() - accountEntity.getWalletBalance());
			payConfirmDto.setNeedBankCode(true);
		} else {
			payConfirmDto.setWalletPayAmount(payConfirmDto.getPayAmount());
		}
		boolean realNameAuth = userEntity.getRealNameAuth().equals(UserRealNameAuthEnum.AUTH.getCode());
		payConfirmDto.setRealNameAuth(realNameAuth);
		return payConfirmDto;
	}

	@RedisLock(prefix = "product_investment", error = "当前投资人数较多，请稍后重试", blocked = true, retry = 5, timeout = 150)
	@Transactional
	@RequestMapping(value = "investment", method = RequestMethod.POST)
	public ApiResponse investment(@RedisLockParam @RequestParam("userId") Long userId,
			@RedisLockParam @RequestParam("productId") Long productId, @RequestParam("couponUserId") Long couponUserId,
			@RequestParam("amount") Long amount) {

		ProductDto productDto = null;
		AccountEntity accountEntity = null;
		CouponUserDto couponUserDto = null;
		BigDecimal total = BigDecimal.ZERO;
		try {
			total = assertHistoryHasTotalInvest(userId);
			productDto = productMapper.getAvailable(productId);
			assertProductAvailable(productDto, amount, userId);
			assertProductHasTotalInvest(userId, productId, amount, productDto.getAmountMax());
			if (couponUserId != null && couponUserId != 0) {
				couponUserDto = couponUserMapper.getCouponById(couponUserId);
				assertCouponUsable(userId, couponUserId, couponUserDto, productDto, amount);
			}

			accountEntity = accountMapper.getByUserId(userId);
			assertAccountWalletBalanceAdequate(accountEntity, couponUserDto, amount);

		} catch (BizException be) {
			return new ApiResponse(be.getExceptionCode(), be.getMessage());
		} catch (Exception e) {
			LOGGER.error("investment validate execption msg : " + e.getMessage());
			return new ApiResponse(ApiStatusEnum.COMMON_SERVICE_ERROR);
		}
		// 如下操作 涉及到数据的一致性，需要加锁，加事物
		// 添加订单
		OrderDto orderDto = addOrder(userId, productDto, couponUserDto, amount, couponUserId);
		// 使用优惠券
		clipCoupons(couponUserDto);
		// 减库存
		productDto = changeInventory(productDto, orderDto);
		// 支付
		payment(userId, orderDto);

		sendCoupon(userId, total);
		return ApiResponse.successApiResponse(System.currentTimeMillis());

	}

	private void sendCoupon(Long userId, BigDecimal total) {
		LOGGER.info("当前用户userid:{}  累计投资：{}", userId, total);
		try {
			if (total.compareTo(BigDecimal.ZERO) <= 0) {
				userfacade.sendCoupon(userId, CouponTriggerConditionEnum.FIRST_INVESTMENT.getCode());
			}
		} catch (Exception e) {
			LOGGER.error("发送首投红包出错：{}", e.getMessage());
		}

	}

	/**
	 * 断言产品是可投资的
	 * 
	 * @param productEntity
	 */
	private void assertProductHasTotalInvest(Long userId, Long productId, long currentAmount, long maxAmount) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", productId);
		map.put("userId", userId);
		BigDecimal money = orderMapper.getTotalInvestByUserIdAndPid(map);
		LOGGER.info("hasmoney {}  currentmoney  {}  maxamount {}", money.longValue(), currentAmount, maxAmount);
		if (maxAmount != 0 && (money.longValue() + currentAmount) > maxAmount) {
			throw new BizException(ApiStatusEnum.PRODUCE_TOTAL_MAX_AMOUNT_LIMIT);
		}
	}

	/**
	 * 判断用户历史累计投资额
	 * 
	 * @param productEntity
	 */
	private BigDecimal assertHistoryHasTotalInvest(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return orderMapper.getTotalInvestByUserId(map);

	}

	/**
	 * 断言产品是可投资的
	 * 
	 * @param productEntity
	 */
	private void assertProductAvailable(ProductDto productDto, long amount, Long userId) {

		Assert.assertNotNull(productDto, ApiStatusEnum.PRODUCE_NOT_EXIST);

		Assert.isTrue(productDto.getAmountMin() <= amount, ApiStatusEnum.PRODUCE_MIN_AMOUNT_LIMIT);

		Assert.isTrue((amount % productDto.getAmountMin()) == 0, ApiStatusEnum.PRODUCE_MIN_AMOUNT_MULTIPLE_LIMIT);

		Assert.notTrue(productDto.getAmountMax() > 0 && productDto.getAmountMax() < amount,
				ApiStatusEnum.PRODUCE_MAX_AMOUNT_LIMIT);

		Assert.isTrue(productDto.getUnsoldAmount() >= amount, ApiStatusEnum.PRODUCE_NOT_SUFFICIENT_FUNDS);

		Assert.isTrue(productDto.getGmtRelease().compareTo(new Date()) <= 0, ApiStatusEnum.PRODUCE_STATUS_NOT_ON_SALE);

		// 非新手用户购买新手标
		Assert.notTrue(
				!userMapper.isNewUser(userId)
						&& ProductTypeEnum.NEW_USER.getCode().equals(productDto.getProductTypeCode()),
				ApiStatusEnum.PRODUCE_NEW_USER_LIMIT);

		// if (productDto == null) {
		// throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST);
		// }
		// if (productDto.getAmountMin() > amount) {
		// throw new BizException(ApiStatusEnum.PRODUCE_MIN_AMOUNT_LIMIT);
		// }
		// if ((amount % productDto.getAmountMin()) != 0) {
		// throw new BizException(ApiStatusEnum.PRODUCE_MIN_AMOUNT_MULTIPLE_LIMIT);
		// }
		// if (productDto.getAmountMax() > 0 && productDto.getAmountMax() < amount) {
		// throw new BizException(ApiStatusEnum.PRODUCE_MAX_AMOUNT_LIMIT);
		// }
		// if (productDto.getUnsoldAmount() < amount) {
		// throw new BizException(ApiStatusEnum.PRODUCE_NOT_SUFFICIENT_FUNDS);
		// }
		// if (productDto.getGmtRelease().compareTo(new Date()) > 0) {
		// throw new BizException(ApiStatusEnum.PRODUCE_STATUS_NOT_ON_SALE);
		// }
	}

	/**
	 * 断言账户余额是充足的
	 * 
	 * @param accountEntity
	 * @param amount
	 */
	private void assertAccountWalletBalanceAdequate(AccountEntity accountEntity, CouponUserDto couponUserDto,
			long amount) {
		Long payAmount = amount;
		if (couponUserDto != null) {
			payAmount = payAmount - couponUserDto.getAmount();
		}
		if (accountEntity.getWalletBalance() < payAmount) {
			throw new BizException(ApiStatusEnum.INSUFFICIENT_ACCOUNT_BALANCE);
		}
	}

	/**
	 * 断言优惠券是可用的
	 */
	private void assertCouponUsable(Long userId, Long couponUserId, CouponUserDto couponUserDto, ProductDto productDto,
			Long amount) {
		if (couponUserId != null && couponUserId != 0) {
			if (couponUserDto == null || !couponUserDto.getUserId().equals(userId)) {
				throw new BizException(ApiStatusEnum.COUPON_NOT_EXIST);
			} else if (!couponUserDto.getProductTypeList().contains(productDto.getProductTypeCode())) {
				// 判断标的是否可用优惠券
				throw new BizException(ApiStatusEnum.COUPON_DISABLE);
			} else if (couponUserDto.getLimitDays() > productDto.getTimeLimit()) {
				// 判断是否满足最少期限限制
				throw new BizException(ApiStatusEnum.COUPON_DISABLE);
			} else if (couponUserDto.getLimitPrice() > amount) {
				// 判断标的是否满足最少投资额限制
				throw new BizException(ApiStatusEnum.COUPON_DISABLE);
			} else if (couponUserDto.getAmount() > amount) {
				// 优惠券的金额大于投资金额，不可用
				throw new BizException(ApiStatusEnum.COUPON_DISABLE);
			}

		}
	}

	/**
	 * 生成订单
	 * 
	 * @param product
	 * @return
	 */
	private OrderDto addOrder(Long userId, ProductDto product, CouponUserDto couponUserDto, Long amount,
			Long couponUserId) {

		String orderNum = Snowflake.getFlowSnowflakeInstance().nextId(ConfigConstants.SN_INVEST, userId); // todo 生成规则

		// 订单
		OrderEntity order = new OrderEntity();
		order.setOrderNum(orderNum);
		order.setUserId(userId);
		order.setAmount(amount);
		if (couponUserDto != null) {
			order.setPaymentAmount(amount - couponUserDto.getAmount());
		} else {
			order.setPaymentAmount(amount);
		}

		Long interest = SincfinUtils.calcInterest(product.getTotalInterestRate(), product.getTimeLimit(),
				order.getAmount());
		order.setInterest(interest);
		order.setStatus(OrderStatusEnum.PAYMENT_SUCCESS.getCode());
		orderMapper.insertSelective(order);

		// 订单产品
		OrderProductEntity orderProductEntity = new OrderProductEntity();
		orderProductEntity.setUserId(userId);
		orderProductEntity.setOrderNum(orderNum);
		orderProductEntity.setProductId(product.getId());
		orderProductEntity.setGmtValueDate(product.getGmtValueDate());
		orderProductEntity.setGmtExpirationDate(product.getGmtExpirationDate());
		orderProductEntity.setTotalInterestRate(product.getTotalInterestRate());
		orderProductEntity.setSubsidyInterestRate(product.getSubsidyInterestRate());
		orderProductEntity.setTimeLimit(product.getTimeLimit());
		orderProductEntity.setProductTitle(product.getTitle());
		orderProductEntity.setValueDateMethod(product.getValueDateMethod());
		orderProductMapper.insertSelective(orderProductEntity);
		if (couponUserDto != null) {
			// 订单优惠券
			OrderCouponEntity orderCouponEntity = new OrderCouponEntity();
			orderCouponEntity.setOrderNum(order.getOrderNum());
			orderCouponEntity.setUserId(userId);
			orderCouponEntity.setInvestMoney(amount);
			orderCouponEntity.setUserCouponId(couponUserId);
			orderCouponEntity.setCouponId(couponUserDto.getCouponId());
			orderCouponEntity.setCouponType(couponUserDto.getType());
			orderCouponEntity.setBonusAmount(couponUserDto.getAmount());
			orderCouponMapper.insertSelective(orderCouponEntity);
		}
		return new OrderDto(order, orderProductEntity, couponUserDto);
	}

	/**
	 * 根据订单，变更库存
	 * 
	 * @param orderDto
	 *            订单
	 */
	private ProductDto changeInventory(ProductDto product, OrderDto orderDto) {
		ProductEntity entity = new ProductEntity();
		BeanHelper.copyProperties(entity, product);

		entity.setSoldAmount(entity.getSoldAmount() + orderDto.getAmount());
		entity.setUnsoldAmount(entity.getAmount() - entity.getSoldAmount());
		if (entity.getUnsoldAmount() == 0) {
			// entity.setGmtValueDate(new Date());
			// entity.setGmtExpirationDate(DateUtils.addDay(entity.getGmtValueDate(),
			// product.getTimeLimit()));
			// entity.setStatus(ProductStatusEnum.INTEREST.getCode());
		} else if (entity.getUnsoldAmount() < 0) {
			throw new BizException(ApiStatusEnum.COMMON_BIZ_SERVICE_ERROR);
		}
		productMapper.updateByPrimaryKeySelective(entity);
		BeanHelper.copyProperties(product, entity);
		return product;
	}

	private void clipCoupons(CouponUserDto couponUserDto) {
		if (couponUserDto != null) {
			CouponUserEntity couponUserEntity = new CouponUserEntity();
			BeanHelper.copyProperties(couponUserEntity, couponUserDto);
			couponUserEntity.setStatus(CouponUserStatusEnum.USED.getCode());
			couponUserEntity.setGmtUse(new Date());
			couponUserMapper.updateByPrimaryKeySelective(couponUserEntity);
		}
	}

	/**
	 * 支付
	 * 
	 * @param userId
	 * @param orderDto
	 */
	private void payment(Long userId, OrderDto orderDto) {

		AccountEntity accountEntity = accountMapper.getByUserId(userId);
		if (orderDto.getCouponUserId() == null) {
			orderDto.setBonusAmount(0L);
		}
		accountMapper.updateAmountFromInvestment(userId, orderDto.getPaymentAmount(), orderDto.getAmount(),
				orderDto.getInterest(), orderDto.getBonusAmount());

		// //变更账户余额
		// accountEntity.setWalletBalance(accountEntity.getWalletBalance()-orderDto.getPaymentAmount());
		// //变更当前投资中金额（投资的本金）
		// accountEntity.setCurrentInvestmentAmount(accountEntity.getCurrentInvestmentAmount()+orderDto.getAmount());
		// //变更代收利息（投资的利息）
		// accountEntity.setWaitInterest(accountEntity.getWaitInterest()+orderDto.getInterest());
		// //记录优惠券的收益
		// if(orderDto.getCouponUserId()!=null){
		// accountEntity.setCouponBonusIncome(accountEntity.getCouponBonusIncome()+orderDto.getBonusAmount());
		// }else{
		// orderDto.setBonusAmount(0L);
		// }
		// //变更总的投资金额
		// accountEntity.setTotalInvestmentAmount(accountEntity.getTotalInvestmentAmount()+orderDto.getAmount());
		// //要替换成为直接更新的形式
		// accountMapper.updateByPrimaryKeySelective(accountEntity);

		AccountMoneyRecordEntity accountMoneyRecordEntity = null;
		Long bongusAmount = 0L;
		if (orderDto.getCouponUserId() != null) {
			bongusAmount = orderDto.getBonusAmount();
			// 使用优惠券收益
			accountMoneyRecordEntity = new AccountMoneyRecordEntity();
			accountMoneyRecordEntity.setUserId(userId);
			accountMoneyRecordEntity.setAccountId(accountEntity.getId());
			accountMoneyRecordEntity.setType(AccountMoneyRecordTypeEnum.COUPON_RECHARGE.getCode());
			accountMoneyRecordEntity.setObjectId(orderDto.getOrderNum());
			accountMoneyRecordEntity.setTitle(AccountMoneyRecordTypeEnum.COUPON_RECHARGE.getText());
			accountMoneyRecordEntity.setAmount(bongusAmount);
			accountMoneyRecordEntity.setBalance(accountEntity.getWalletBalance() + bongusAmount);
			accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);
		}
		// 投资支付
		accountMoneyRecordEntity = new AccountMoneyRecordEntity();
		accountMoneyRecordEntity.setUserId(userId);
		accountMoneyRecordEntity.setAccountId(accountEntity.getId());
		accountMoneyRecordEntity.setType(AccountMoneyRecordTypeEnum.INVESTMENT.getCode());
		accountMoneyRecordEntity.setObjectId(orderDto.getOrderNum());
		accountMoneyRecordEntity.setTitle(AccountMoneyRecordTypeEnum.INVESTMENT.getText());
		accountMoneyRecordEntity.setAmount(orderDto.getAmount());
		/* 是否有用优惠券 */
		accountMoneyRecordEntity.setBalance(accountEntity.getWalletBalance() - orderDto.getPaymentAmount());
		accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);
	}

	/**
	 * 产品介绍
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "introduce", method = RequestMethod.GET)
	public ProductIntroduceDto introduce(@RequestParam("id") Long id) {
		ProductEntity entity = productMapper.selectByPrimaryKey(id);
		ProductIntroduceDto productIntroduce = productMapper.introduce(id);
		FinancingUserCountDto financingUserCount = financingUserMapper.financingCount(entity.getFinancingUserId());
		productIntroduce.setFinancingUserCount(financingUserCount);

		ContractRepaymentTypeEnum repaymentTypeEnum = ContractRepaymentTypeEnum
				.fromCode(productIntroduce.getRepaymentType());
		if (repaymentTypeEnum != null) {
			productIntroduce.setRepaymentType(repaymentTypeEnum.getText());
		}
		ProductValueDateMethodEnum valueDateMethodEnum = ProductValueDateMethodEnum
				.fromCode(productIntroduce.getValueDateMethod());
		if (valueDateMethodEnum != null) {
			productIntroduce.setValueDateMethod(valueDateMethodEnum.getText());
		}
		mergeLoanAgreementImage(productIntroduce);
		ProductExtend ex = productExtendMapper.selectByPrimaryKey(id);
		if (ex != null) {
			productIntroduce.setBillBank(ex.getAttr1());
			String path = ex.getAttr2();
			productIntroduce.setAgreement(ex.getAgreement());
			if (StringUtils.isNotBlank(path)) {
				String[] pics = path.split(",");
				productIntroduce.setBillImgPath(pics);
			}

		}
		return productIntroduce;
	}

	private void mergeLoanAgreementImage(ProductIntroduceDto productIntroduce) {
		if (productIntroduce.getBusinessLicenseImage() != null) {
			productIntroduce.setBusinessLicenseImage(
					fileObjectService.getPublicFileUrl(productIntroduce.getBusinessLicenseImage()));
		}
		if (productIntroduce.getIdCardBackImage() != null) {
			productIntroduce
					.setIdCardBackImage(fileObjectService.getPublicFileUrl(productIntroduce.getIdCardBackImage()));
		}
		if (productIntroduce.getIdCardFaceImage() != null) {
			productIntroduce
					.setIdCardFaceImage(fileObjectService.getPublicFileUrl(productIntroduce.getIdCardFaceImage()));
		}
		if (productIntroduce.getServiceAgreement() != null) {
			List<String> imageUrls = new ArrayList<String>();
			String[] images = productIntroduce.getServiceAgreement().split(",");
			for (String image : images) {
				String imageUrl = fileObjectService.getPublicFileUrl(image);
				imageUrls.add(imageUrl);
			}
			productIntroduce.setServiceAgreementUrls(imageUrls);
		}
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

	@Override
	@RequestMapping("sellOutTask")
	public int sellOutTask() {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setStatus(ProductStatusEnum.ON_SALE.getCode());
		productQuery.setUnsoldAmount(0L);
		List<ProductEntity> entityList = productMapper.listByCondition(productQuery);
		if (CollectionUtils.isEmpty(entityList)) {
			return 0;
		}
		entityList.forEach(entity -> {
			try {
				this.updateSellOut(entity.getId());
			} catch (Exception e) {
				LOGGER.error("product sell out error: ", e);
			}
		});
		return entityList.size();
	}

	@Override
	@RequestMapping("updateSellOut")
	@Transactional
	@RedisLock(prefix = "product:sell:out:")
	public int updateSellOut(@RedisLockParam @RequestParam("productId") Long productId) {

		ProductEntity productEntity = productMapper.selectByPrimaryKey(productId);
		if (!ProductStatusEnum.ON_SALE.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_SELL_OUT_STATUS_NOT_ON_SALE);
		}
		if (!productEntity.getAmount().equals(productEntity.getSoldAmount()) || productEntity.getUnsoldAmount() > 0) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_SELL_OUT);
		}

		OrderQuery orderQuery = new OrderQuery();
		orderQuery.setStatus(OrderStatusEnum.PAYMENT_SUCCESS.getCode());
		orderQuery.setProductId(productId);
		orderQuery.setUserAccountType(UserAccountTypeEnum.NORMAL.getCode());
		List<OrderDto> orderDtoList = orderMapper.listManagerByCondition(orderQuery);
		if (CollectionUtils.isEmpty(orderDtoList)) {
			return 0;
		}
		String repaymentType = productEntity.getRepaymentType();

		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// 计息时间
		if (productEntity.getValueDateMethod().equals(ProductValueDateMethodEnum.T1.getCode())) {
			calendar.add(Calendar.DATE, 1);
		} else if (productEntity.getValueDateMethod().equals(ProductValueDateMethodEnum.T2.getCode())) {
			calendar.add(Calendar.DATE, 2);
		} else {
			// todo
			calendar.add(Calendar.DATE, 2);
		}
		Date gmtValueDate = calendar.getTime();
		calendar.add(Calendar.DATE, productEntity.getTimeLimit());
		// 到期时间 / 还款时间
		Date gmtExpirationDate = calendar.getTime();

		// 正常用户购买的本金总量
		Long normalSoldPrincipal = 0L;
		// 产品该还款的金额
		Long productRepaymentAmount = 0L;
		List<AccountRepaymentEntity> accountRepaymentEntityList = Lists
				.newArrayListWithExpectedSize(orderDtoList.size());

		for (OrderDto dto : orderDtoList) {
			AccountRepaymentEntity accountRepaymentEntity = new AccountRepaymentEntity();
			accountRepaymentEntity.setUserId(dto.getUserId());
			accountRepaymentEntity.setAccountId(dto.getAccountId());
			accountRepaymentEntity.setProductId(productId);
			accountRepaymentEntity.setOrderNum(dto.getOrderNum());
			// 当还款类型为本息，订单 amount: 本金，还款 amount : 本金 + 利息
			accountRepaymentEntity.setAmount(dto.getAmount() + dto.getInterest());
			accountRepaymentEntity.setPrincipal(dto.getAmount());
			accountRepaymentEntity.setInterest(dto.getInterest());
			accountRepaymentEntity.setType(repaymentType);
			accountRepaymentEntity.setStatus(AccountRepaymentStatusEnum.NO_REPAYMENT.getCode());
			accountRepaymentEntity.setGmtRepayment(gmtExpirationDate);
			accountRepaymentEntityList.add(accountRepaymentEntity);
			normalSoldPrincipal += dto.getAmount();
			productRepaymentAmount += (dto.getAmount() + dto.getInterest());
		}
		if (normalSoldPrincipal > productEntity.getAmount()) {
			LOGGER.error("productId: " + productId + " 超卖");
		}
		// 批量插入投资用户还款计划
		accountRepaymentMapper.insertBatch(accountRepaymentEntityList);

		ProductRepaymentEntity productRepaymentEntity = new ProductRepaymentEntity();
		productRepaymentEntity.setProductId(productId);
		productRepaymentEntity.setAmount(productRepaymentAmount);
		productRepaymentEntity.setPrincipal(normalSoldPrincipal);
		productRepaymentEntity.setInterest(productRepaymentAmount - normalSoldPrincipal);
		productRepaymentEntity.setType(repaymentType);
		productRepaymentEntity.setStatus(ProductRepaymentStatusEnum.NO_REPAYMENT.getCode());
		productRepaymentEntity.setGmtExpectedRepayment(gmtExpirationDate);
		// 插入产品还款计划
		productRepaymentMapper.insertSelective(productRepaymentEntity);

		// 融资客户还款操作
		FinancingUserRepaymentDto financingUserRepaymentDto = new FinancingUserRepaymentDto();
		financingUserRepaymentDto.setFinancingUserId(productEntity.getFinancingUserId());
		financingUserRepaymentDto.setContractId(productEntity.getContractId());
		financingUserRepaymentDto.setProductId(productEntity.getId());
		financingUserRepaymentDto.setGmtExpectedRepayment(gmtExpirationDate);
		financingUserRepaymentDto.setType(repaymentType);
		financingUserRepaymentDto.setExpectedAmount(productRepaymentEntity.getAmount());
		financingUserRepaymentDto.setStatus(FinancingUserRepaymentStatusEnum.NO_REPAYMENT.getCode());
		financingUserRepaymentDto.setPrincipal(productRepaymentEntity.getPrincipal());
		financingUserService.updateProductRepayment(financingUserRepaymentDto);

		// 融资打款表
		FinancingUserRemittanceDto financingUserRemittanceDto = new FinancingUserRemittanceDto();
		financingUserRemittanceDto.setFinancingUserId(productEntity.getFinancingUserId());
		financingUserRemittanceDto.setContractId(productEntity.getContractId());
		financingUserRemittanceDto.setProductId(productEntity.getId());

		financingUserRemittanceDto.setExpectedAmount(normalSoldPrincipal);
		financingUserRemittanceDto.setGmtExpectedRemittance(now);
		financingUserRemittanceDto.setStatus(ProductRemittanceStatusEnum.NO_REMITTANCE.getCode());
		financingUserService.updateProductSellOut(financingUserRemittanceDto);

		Date gmtSellOut = now;
		OrderDto orderDto = orderMapper.getLastOrderByProductId(productId);
		if (orderDto != null) {
			gmtSellOut = orderDto.getGmtCreate();
		}
		// 更新产品状态及计息时间等
		ProductEntity updateProductEntity = new ProductEntity();
		updateProductEntity.setId(productId);
		updateProductEntity.setStatus(ProductStatusEnum.INTEREST.getCode());
		updateProductEntity.setGmtValueDate(gmtValueDate);
		updateProductEntity.setGmtExpirationDate(gmtExpirationDate);
		updateProductEntity.setGmtSellOut(gmtSellOut);
		return productMapper.updateByPrimaryKeySelective(updateProductEntity);
	}

	@Override
	@RequestMapping("interestExpire")
	public int interestExpire() {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setStatus(ProductStatusEnum.INTEREST.getCode());
		productQuery.setGmtEndExpirationDate(new Date());
		List<ProductEntity> entityList = productMapper.listByCondition(productQuery);

		if (CollectionUtils.isEmpty(entityList)) {
			return 0;
		}
		entityList.forEach(entity -> {
			try {
				this.updateInterestExpire(entity.getId());
			} catch (Exception e) {
				LOGGER.error("product sell out error: ", e);
			}
		});
		return entityList.size();
	}

	@Override
	@RequestMapping("updateInterestExpire")
	@Transactional
	@RedisLock(prefix = "product:interest:expire:")
	public int updateInterestExpire(@RedisLockParam @RequestParam("productId") Long productId) {

		ProductEntity productEntity = productMapper.selectByPrimaryKey(productId);
		if (!ProductStatusEnum.INTEREST.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_EXPIRE_STATUS_NOT_INTEREST);
		}

		ProductEntity updateProductEntity = new ProductEntity();
		updateProductEntity.setId(productId);
		updateProductEntity.setStatus(ProductStatusEnum.REPAYMENT.getCode());
		return productMapper.updateByPrimaryKeySelective(updateProductEntity);
	}

}
