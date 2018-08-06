package com.ruixun.sincfin.biz.feign.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.CouponUserDto;
import com.ruixun.sincfin.domain.dto.InvitationRecordDto;
import com.ruixun.sincfin.domain.query.CouponUserQuery;
import com.ruixun.sincfin.domain.query.UserQuery;

@FeignClient("${biz.feign.name}")
public interface CouponUserClient {

	/**
	 * 添加
	 * 
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value = "/couponUser/add", method = RequestMethod.POST)
	public CouponUserDto add(@RequestBody CouponUserDto couponUserDto);

	/**
	 * 删除
	 * 
	 * @param accountDto
	 */
	@RequestMapping(value = "/couponUser/delete", method = RequestMethod.DELETE)
	public void delete(@RequestBody CouponUserDto couponUserDto);

	/**
	 * 更新
	 * 
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value = "/couponUser/update", method = RequestMethod.PUT)
	public CouponUserDto update(@RequestBody CouponUserDto couponUserDto);

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/couponUser/getById", method = RequestMethod.GET)
	public CouponUserDto getById(@RequestParam("id") Long id);

	/**
	 * 管理平台用户优惠券列表
	 * 
	 * @param query
	 * @return
	 */

	@RequestMapping(value = "/couponUser/listManagerPageByCondition")
	public Pagination<CouponUserDto> listManagerPageByCondition(@RequestBody CouponUserQuery query);

	/**
	 * 获取用户优惠券
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/couponUser/listByUserId")
	public Pagination<CouponUserDto> listByUserId(@RequestBody CouponUserQuery query);

	/**
	 * 判断是否有可使用的优惠券
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/couponUser/canUseCoupon", method = RequestMethod.GET)
	public Boolean canUseCoupon(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId);

	/**
	 * 获取金额最大的可用优惠券
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/couponUser/getMaxAmountByUserId", method = RequestMethod.GET)
	public CouponUserDto getMaxAmountByUserId(@RequestParam("userId") Long userId);

	/**
	 * 获取可用优惠券总金额
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/couponUser/getTotalAmountByUserId", method = RequestMethod.GET)
	public BigDecimal getTotalAmountByUserId(@RequestParam("userId") Long userId);

	@RequestMapping("/couponUser/couponUserExpiredTask")
	int couponUserExpiredTask();

}
