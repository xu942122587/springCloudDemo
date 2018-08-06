package com.ruixun.sincfin.biz.module.coupon.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.ruixun.sincfin.biz.feign.client.CouponUserClient;
import com.ruixun.sincfin.biz.module.product.service.ProductTypeService;
import com.ruixun.sincfin.domain.dto.ProductTypeDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.module.coupon.mapper.CouponUserMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.CouponUserDto;
import com.ruixun.sincfin.domain.entity.CouponUserEntity;
import com.ruixun.sincfin.domain.query.CouponUserQuery;

/**
 * Created by tiantiea on 2018/5/25.
 */
@RestController
@RequestMapping("couponUser")
public class CouponUserService implements CouponUserClient {

	@Resource
	private CouponUserMapper couponUserMapper;
	@Resource
	private ProductTypeService productTypeService;

	/**
	 * 添加
	 * 
	 * @param couponUserDto
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public CouponUserDto add(@RequestBody CouponUserDto couponUserDto) {
		CouponUserEntity couponUserEntity = new CouponUserEntity();
		BeanHelper.copyProperties(couponUserEntity, couponUserDto);
		couponUserMapper.insertSelective(couponUserEntity);
		return couponUserDto;
	}

	/**
	 * 删除
	 * 
	 * @param couponUserDto
	 */
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public void delete(@RequestBody CouponUserDto couponUserDto) {
		// TODO
	}

	/**
	 * 更新
	 * 
	 * @param couponUserDto
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public CouponUserDto update(@RequestBody CouponUserDto couponUserDto) {
		CouponUserEntity couponUserEntity = new CouponUserEntity();
		BeanHelper.copyProperties(couponUserEntity, couponUserDto);
		couponUserMapper.updateByPrimaryKeySelective(couponUserEntity);
		return couponUserDto;
	}

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public CouponUserDto getById(@RequestParam Long id) {

		CouponUserEntity couponUserEntity = couponUserMapper.selectByPrimaryKey(id);
		if (couponUserEntity == null) {
			return null;
		}
		CouponUserDto couponUserDto = new CouponUserDto();
		BeanHelper.copyProperties(couponUserDto, couponUserEntity);

		return couponUserDto;
	}

	/**
	 * 管理平台用户优惠券列表
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "listManagerPageByCondition")
	public Pagination<CouponUserDto> listManagerPageByCondition(@RequestBody CouponUserQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<CouponUserDto> couponUserDtoList = couponUserMapper.listManagerByCondition(query);
		PageInfo pageInfo = new PageInfo(couponUserDtoList);
		if (CollectionUtils.isEmpty(couponUserDtoList)) {
			return new Pagination(query, couponUserDtoList, (int) pageInfo.getTotal());
		}

		Map<String, ProductTypeDto> productTypeDtoMap = productTypeService.mapByProductCode();
		for (CouponUserDto dto : couponUserDtoList) {
			if (StringUtils.isEmpty(dto.getProductTypeList())) {
				continue;
			}
			List<String> productCodeList = Arrays.asList(dto.getProductTypeList().split(",")).stream()
					.map(s -> s.trim()).collect(Collectors.toList());
			StringBuffer tmpProductTypeDesc = new StringBuffer("");
			for (String productCode : productCodeList) {
				ProductTypeDto productTypeDto = productTypeDtoMap.get(productCode);
				if (productTypeDto == null) {
					continue;
				}
				tmpProductTypeDesc.append(productTypeDto.getName()).append(",");
			}
			if (StringUtils.isNotEmpty(tmpProductTypeDesc)) {
				dto.setProductTypeListDesc(
						StringUtils.substring(tmpProductTypeDesc.toString(), 0, tmpProductTypeDesc.length() - 1));
			}
		}

		return new Pagination(query, couponUserDtoList, (int) pageInfo.getTotal());
	}

	/**
	 * 获取用户优惠券
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "listByUserId")
	public Pagination<CouponUserDto> listByUserId(@RequestBody CouponUserQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<CouponUserDto> userCoupons = couponUserMapper.listByUserId(query);
		PageInfo pageInfo = new PageInfo(userCoupons);
		return new Pagination(query, userCoupons, (int) pageInfo.getTotal());
	}

	@RequestMapping(value = "canUseCoupon", method = RequestMethod.GET)
	public Boolean canUseCoupon(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
		return couponUserMapper.canUseCoupon(userId, productId);
	}

	/**
	 * 获取金额最大的可用优惠券
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getMaxAmountByUserId", method = RequestMethod.GET)
	public CouponUserDto getMaxAmountByUserId(@RequestParam("userId") Long userId) {
		return couponUserMapper.getMaxAmountByUserId(userId);
	}

	@RequestMapping("couponUserExpiredTask")
	public int couponUserExpiredTask() {
		return couponUserMapper.updateStatusExpired();
	}

	@Override
	@RequestMapping(value = "getTotalAmountByUserId", method = RequestMethod.GET)
	public BigDecimal getTotalAmountByUserId(Long userId) {
		return couponUserMapper.getTotalAmountByUserId(userId);
	}

}
