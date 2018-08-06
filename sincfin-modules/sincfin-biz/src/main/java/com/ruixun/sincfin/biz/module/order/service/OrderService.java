package com.ruixun.sincfin.biz.module.order.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.OrderClient;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserMapper;
import com.ruixun.sincfin.biz.module.order.mapper.OrderMapper;
import com.ruixun.sincfin.biz.module.order.mapper.OrderProductMapper;
import com.ruixun.sincfin.biz.module.product.mapper.ProductMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserMapper;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.DateUtils;
import com.ruixun.sincfin.common.util.Snowflake;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.LoanAgreementDto;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.entity.FinancingUserEntity;
import com.ruixun.sincfin.domain.entity.OrderEntity;
import com.ruixun.sincfin.domain.entity.OrderProductEntity;
import com.ruixun.sincfin.domain.entity.ProductEntity;
import com.ruixun.sincfin.domain.entity.UserEntity;
import com.ruixun.sincfin.domain.enums.OrderStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.OrderQuery;

@RestController
@RequestMapping("/order")
public class OrderService implements OrderClient {

	@Resource
	private UserMapper userMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderProductMapper orderProductMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private FinancingUserMapper financingUserMapper;

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public OrderDto getById(@RequestParam("id") Long id) {

		OrderDto orderDto = orderMapper.getOrderById(id);

		return orderDto;
	}

	/**
	 * 管理平台投资订单接口
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("listManagerPage")
	public Pagination<OrderDto> listManagerPage(@RequestBody OrderQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<OrderDto> orderDtoList = orderMapper.listManagerByCondition(query);
		PageInfo pageInfo = new PageInfo(orderDtoList);

		return new Pagination(query, orderDtoList, (int) pageInfo.getTotal());

	}

	/**
	 * 管理平台幽灵用户投资
	 * 
	 * @param orderDto
	 * @param orderDto
	 *            userId
	 * @param orderDto
	 *            productId
	 * @param orderDto
	 *            amount
	 * @return
	 */
	@Transactional
	@RequestMapping("insertGhostInvestment")
	public OrderDto insertGhostInvestment(@RequestBody OrderDto orderDto) {
		// todo 业务判断及分布式锁
		Long productId = orderDto.getProductId();
		Long amount = orderDto.getAmount();

		ProductEntity productEntity = productMapper.selectByPrimaryKey(productId);

		if (productEntity == null) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_EXIST);
		}
		if (StringUtils.isEmpty(productEntity.getStatus())
				|| !ProductStatusEnum.ON_SALE.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_STATUS_NOT_ON_SALE);
		}
		if (productEntity.getUnsoldAmount() < orderDto.getAmount()) {
			throw new BizException(ApiStatusEnum.PRODUCE_NOT_SUFFICIENT_FUNDS);
		}

		productMapper.updateSoldAmount(productId, amount, -amount);

		String orderNum = Snowflake.getFlowSnowflakeInstance().nextId(ConfigConstants.SN_INVEST, orderDto.getUserId()); // todo
																														// 生成规则

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderNum(orderNum);
		orderEntity.setUserId(orderDto.getUserId());
		orderEntity.setAmount(orderDto.getAmount());
		orderEntity.setPaymentAmount(0L);
		orderEntity.setInterest(0L);
		orderEntity.setStatus(OrderStatusEnum.PAYMENT_SUCCESS.getCode());
		orderMapper.insertSelective(orderEntity);

		OrderProductEntity orderProductEntity = new OrderProductEntity();
		orderProductEntity.setUserId(orderDto.getUserId());
		orderProductEntity.setOrderNum(orderNum);
		orderProductEntity.setProductId(productEntity.getId());
		orderProductEntity.setProductTitle(productEntity.getTitle());
		orderProductEntity.setTotalInterestRate(productEntity.getTotalInterestRate());
		orderProductEntity.setSubsidyInterestRate(productEntity.getSubsidyInterestRate());
		orderProductEntity.setTimeLimit(productEntity.getTimeLimit());
		orderProductEntity.setValueDateMethod(productEntity.getValueDateMethod());
		orderProductMapper.insertSelective(orderProductEntity);

		return orderDto;
	}

	/**
	 * 获取借款协议
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "loan/agreement", method = RequestMethod.GET)
	public LoanAgreementDto loanAgreement(Long id) {

		LoanAgreementDto loanAgreementDto = new LoanAgreementDto();
		OrderDto orderDto = orderMapper.getOrderById(id);

		String date = DateUtils.format("yyyyMMdd", orderDto.getGmtCreate());
		loanAgreementDto.setAgreementNum("CY" + date + orderDto.getOrderNum());

		UserEntity user = userMapper.selectByPrimaryKey(orderDto.getUserId());
		loanAgreementDto.setBorrower(StringUtils.realNameEncryption(user.getRealName()));
		loanAgreementDto.setBorrowerCardNo(StringUtils.cardNoEncryption(user.getIdCardNo()));
		loanAgreementDto.setBorrowerPhone(StringUtils.mobileEncryption(user.getMobile()));

		ProductEntity productEntity = productMapper.selectByPrimaryKey(orderDto.getProductId());
		FinancingUserEntity financingUserEntity = financingUserMapper
				.selectByPrimaryKey(productEntity.getFinancingUserId());

		loanAgreementDto.setLender(StringUtils.realNameEncryption(financingUserEntity.getRealName()));
		loanAgreementDto.setLenderCardNo(financingUserEntity.getBusinessLicenseNo());

		loanAgreementDto.setMoney(orderDto.getAmount());
		loanAgreementDto.setInterest(orderDto.getTotalInterestRate().setScale(4, BigDecimal.ROUND_HALF_UP));
		loanAgreementDto.setTimeLimit(productEntity.getTimeLimit());

		return loanAgreementDto;
	}

}
