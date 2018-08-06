package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.UserBankDto;
import com.ruixun.sincfin.domain.query.UserBankQuery;

/**
 * @author t
 * @date 2018/5/23 15:04
 */
@FeignClient("${biz.feign.name}")
public interface UserBankClient {
	
	
	/**
	 * 添加
	 * @param userBankDto
	 * @return
	 */
	@RequestMapping(value="/userBank/add",method = RequestMethod.POST)
	public UserBankDto add(@RequestBody UserBankDto userBankDto);
	
	/**
	 * 更新
	 * @param userBankDto
	 * @return
	 */
	@RequestMapping(value="/userBank/update",method = RequestMethod.PUT)
	public UserBankDto update(@RequestBody UserBankDto userBankDto);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/userBank/getById",method = RequestMethod.GET)
	public UserBankDto getById(@RequestParam("id") Long id) ;

    @RequestMapping(value="/userBank/listPageByCondition",method = RequestMethod.POST)
    public Pagination<UserBankDto> listPageByCondition(@RequestBody UserBankQuery query);
    
    
    @RequestMapping(value="/userBank/listByUser",method = RequestMethod.POST)
    public Pagination<UserBankDto> listByUser(@RequestBody UserBankQuery query);
    
    
    /**
     * 查找用户最近使用的一张银行卡
     * @param userId
     * @return
     */
    @RequestMapping(value="/userBank/getRecentUpdate",method = RequestMethod.GET)
	public UserBankDto getRecentUpdate(@RequestParam("userId")Long userId);
    
    
    @RequestMapping(value="/userBank/getAvailable",method = RequestMethod.GET)
	public UserBankDto getAvailable(@RequestParam("id")Long id);
    
    @RequestMapping(value="/userBank/unbind",method = RequestMethod.POST)
	public void unbind(@RequestParam("id")Long id);
}
