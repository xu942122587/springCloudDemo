package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.dto.FeedbackDto;

@FeignClient("${biz.feign.name}")
public interface FeedbackClient {
	
	/**
	 * 添加
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/feedback/add",method = RequestMethod.POST)
	public void add(@RequestBody FeedbackDto dto);
	
	/**
	 * 删除
	 * @param accountDto
	 */
	@RequestMapping(value="/feedback/delete",method = RequestMethod.DELETE)
	public void delete(@RequestBody FeedbackDto dto);
	
	/**
	 * 更新
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/feedback/update",method = RequestMethod.PUT)
	public FeedbackDto update(@RequestBody FeedbackDto dto);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/feedback/getById",method = RequestMethod.GET)
	public FeedbackDto getById(@RequestParam("id") Long id) ;

}
