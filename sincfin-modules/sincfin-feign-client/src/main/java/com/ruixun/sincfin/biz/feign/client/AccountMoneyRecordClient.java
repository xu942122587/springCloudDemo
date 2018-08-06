package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountMoneyRecordDto;
import com.ruixun.sincfin.domain.query.AccountMoneyRecordQuery;

@FeignClient("${biz.feign.name}")
public interface AccountMoneyRecordClient {
	
	
	/**
	 * 添加
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/accountMoneyRecord/add",method = RequestMethod.POST)
	public AccountMoneyRecordDto add(@RequestBody AccountMoneyRecordDto accountMoneyRecordDto);
	/**
	 * 删除
	 * @param accountDto
	 */
	@RequestMapping(value="/accountMoneyRecord/delete",method = RequestMethod.DELETE)
	public void delete(@RequestBody AccountMoneyRecordDto accountMoneyRecordDto);
	
	/**
	 * 更新
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="/accountMoneyRecord/update",method = RequestMethod.PUT)
	public AccountMoneyRecordDto update(@RequestBody AccountMoneyRecordDto accountMoneyRecordDto);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/accountMoneyRecord/getById",method = RequestMethod.GET)
	public AccountMoneyRecordDto getById(@RequestParam("id") Long id);
	
	
	
	@RequestMapping(value="/accountMoneyRecord/listPageByCondition",method = RequestMethod.POST)
    public Pagination<AccountMoneyRecordDto> listPageByCondition(@RequestBody AccountMoneyRecordQuery query);

}
