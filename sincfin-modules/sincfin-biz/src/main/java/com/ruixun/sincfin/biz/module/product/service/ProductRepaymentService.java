package com.ruixun.sincfin.biz.module.product.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.ProductRepaymentClient;
import com.ruixun.sincfin.biz.module.account.service.AccountRepaymentService;
import com.ruixun.sincfin.biz.module.finuser.service.FinancingUserService;
import com.ruixun.sincfin.biz.module.order.mapper.OrderMapper;
import com.ruixun.sincfin.biz.module.product.mapper.ProductMapper;
import com.ruixun.sincfin.biz.module.product.mapper.ProductRepaymentMapper;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.lock.RedisLock;
import com.ruixun.sincfin.common.lock.RedisLockParam;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.dto.ProductRepaymentDto;
import com.ruixun.sincfin.domain.entity.ProductEntity;
import com.ruixun.sincfin.domain.entity.ProductRepaymentEntity;
import com.ruixun.sincfin.domain.enums.FinancingUserRepaymentStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductRemittanceStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductRepaymentStatusEnum;
import com.ruixun.sincfin.domain.enums.ProductStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.ProductRepaymentQuery;

/**
 * Created by tiea on 2018/5/31.
 */
@RestController
@RequestMapping("productRepayment")
public class ProductRepaymentService implements ProductRepaymentClient {

	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductRepaymentMapper productRepaymentMapper;
	@Resource
	private AccountRepaymentService accountRepaymentService;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private FinancingUserService financingUserService;

	@RequestMapping("listPageByCondition")
	public Pagination<ProductRepaymentDto> listPageByCondition(@RequestBody ProductRepaymentQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<ProductRepaymentDto> productRepaymentDtoList = productRepaymentMapper.listByCondition(query);
		PageInfo pageInfo = new PageInfo(productRepaymentDtoList);

		return new Pagination(query, productRepaymentDtoList, (int) pageInfo.getTotal());

	}

	/**
	 * 还款接口
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("updateRepayment")
	@Override
	@Transactional
	@RedisLock(prefix = "product:repayment:")
	public int updateRepayment(@RedisLockParam @RequestParam("productId") Long productId) {

		ProductRepaymentEntity productRepaymentEntity = productRepaymentMapper.getByProductId(productId);
		if (productRepaymentEntity == null) {
			throw new BizException(ApiStatusEnum.PRODUCE_REPAYMENT_NOT_EXIST);
		}
		if (!ProductRepaymentStatusEnum.NO_REPAYMENT.getCode().equals(productRepaymentEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_REPAYMENT_STATUS_ERROR);
		}
		if (productRepaymentEntity.getGmtExpectedRepayment().after(new Date())) {
			throw new BizException(ApiStatusEnum.PRODUCE_REPAYMENT_DATE_ERROR);
		}
		ProductEntity productEntity = productMapper.selectByPrimaryKey(productId);
		if (!ProductStatusEnum.REPAYMENT.getCode().equals(productEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_STATUS_NOT_REPAYMENT);
		}
		Date now = new Date();
		ProductEntity updateProductEntity = new ProductEntity();
		updateProductEntity.setId(productId);
		updateProductEntity.setStatus(ProductStatusEnum.END.getCode());
		productMapper.updateByPrimaryKeySelective(updateProductEntity);
		// 账户还款操作
		Long realtotalAmount = accountRepaymentService.updateRepayment(productId);

		// 订单还款操作
		orderMapper.updateRepayment(productId);

		// 融资客户还款操作
		FinancingUserRepaymentDto financingUserRepaymentDto = new FinancingUserRepaymentDto();
		financingUserRepaymentDto.setFinancingUserId(productEntity.getFinancingUserId());
		financingUserRepaymentDto.setContractId(productEntity.getContractId());
		financingUserRepaymentDto.setProductId(productEntity.getId());
		financingUserRepaymentDto.setGmtExpectedRepayment(now);
		financingUserRepaymentDto.setAmount(realtotalAmount);
		financingUserRepaymentDto.setStatus(FinancingUserRepaymentStatusEnum.REPAYMENT.getCode());
		financingUserRepaymentDto.setPrincipal(productRepaymentEntity.getPrincipal());
		financingUserService.updateFinancingRepayment(financingUserRepaymentDto);

		// 融资用户融资打款计划
		FinancingUserRemittanceDto financingUserRemittanceDto = new FinancingUserRemittanceDto();
		financingUserRemittanceDto.setFinancingUserId(productEntity.getFinancingUserId());
		financingUserRemittanceDto.setContractId(productEntity.getContractId());
		financingUserRemittanceDto.setProductId(productEntity.getId());
		financingUserRemittanceDto.setExpectedAmount(realtotalAmount);
		financingUserRemittanceDto.setGmtExpectedRemittance(now);
		financingUserRemittanceDto.setStatus(ProductRemittanceStatusEnum.REMITTANCE.getCode());
		financingUserService.updateFinancingRemittance(financingUserRemittanceDto);

		ProductRepaymentEntity updateProductRepaymentEntity = new ProductRepaymentEntity();
		updateProductRepaymentEntity.setId(productRepaymentEntity.getId());
		updateProductRepaymentEntity.setGmtRepayment(now);
		updateProductRepaymentEntity.setStatus(ProductRepaymentStatusEnum.REPAYMENT.getCode());
		return productRepaymentMapper.updateByPrimaryKeySelective(updateProductRepaymentEntity);

	}

}
