package com.ruixun.sincfin.biz.feign.client;

import java.util.Date;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

@FeignClient("${biz.feign.name}")
public interface AccountClient {
	
	/**
	 * 添加
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/account/add",method = RequestMethod.POST)
	public AccountDto add(@RequestBody AccountDto accountDto);
	
	/**
	 * 删除
	 * @param accountDto
	 */
	@RequestMapping(value="/account/delete",method = RequestMethod.DELETE)
	public void delete(@RequestBody AccountDto accountDto);
	
	/**
	 * 更新
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/account/update",method = RequestMethod.PUT)
	public AccountDto update(@RequestBody AccountDto accountDto);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/account/getById",method = RequestMethod.GET)
	public AccountDto getById(@RequestParam("id") Long id) ;
	
	/**
	 * 根据userId查找
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/account/getByUserId",method = RequestMethod.GET)
	public AccountDto getByUserId(@RequestParam("userId") Long userId);

	/**
	 * 提现
	 * @param userId
	 * @param bankCodeId
	 * @param amount
	 */
	@RequestMapping(value="/account/withdraw",method = RequestMethod.POST)
	public ApiResponse withdraw(@RequestParam("userId")Long userId, @RequestParam("bankCodeId")Long bankCodeId, @RequestParam("amount")Long amount);

	
	/**
	 * 充值获取验证码
	 * @param id
	 * @param bankCodeId
	 * @param amount
	 */
	@RequestMapping(value="/account/recharge/smsCode",method = RequestMethod.POST)
	public ApiResponse rechargeSmsCode(@RequestParam("ip")String ip, @RequestParam("userId")Long userId, @RequestParam("bankCodeId")Long bankCodeId, @RequestParam("amount")Long amount,@RequestParam("type")String type);

	/**
	 * 充值
	 * @param id
	 * @param bankCodeId
	 * @param amount
	 */
	@RequestMapping(value="/account/recharge",method = RequestMethod.POST)
	public ApiResponse recharge(@RequestParam("ip")String ip,@RequestParam("rechargeNum")String rechargeNum, @RequestParam("smsCode")String smsCode);

	/**
	 * 统计规定时间内的提现次数
	 * @param id
	 * @param date
	 * @return
	 */
	@RequestMapping(value="/account/countWithdrawByDate",method = RequestMethod.GET)
	public int countWithdrawByDate(@RequestParam("userId")Long userId, @RequestParam("time")Date time);
	
	@RequestMapping(value="/account/countTodayWithdraw",method = RequestMethod.GET)
	public int countTodayWithdraw(@RequestParam("userId")Long userId);

	/**
	 * 处理充值中的订单
	 */
	@RequestMapping(value="/account/rechargeHandle",method = RequestMethod.POST)
	public void rechargeHandle();

	

	
}
