package com.ruixun.sincfin.biz.module.account.service;

import java.util.List;

import javax.annotation.Resource;

import com.ruixun.sincfin.biz.feign.client.AccountMoneyRecordClient;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMoneyRecordMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountMoneyRecordDto;
import com.ruixun.sincfin.domain.dto.AccountRechargeDto;
import com.ruixun.sincfin.domain.entity.AccountMoneyRecordEntity;
import com.ruixun.sincfin.domain.entity.AccountRechargeEntity;
import com.ruixun.sincfin.domain.query.AccountMoneyRecordQuery;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;


@RestController
@RequestMapping("accountMoneyRecord")
public class AccountMoneyRecordService implements AccountMoneyRecordClient {
	
	
	@Resource
	private AccountMoneyRecordMapper accountMoneyRecordMapper;
	
	/**
	 * 添加
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="add",method = RequestMethod.POST)
	public AccountMoneyRecordDto add(@RequestBody AccountMoneyRecordDto accountMoneyRecordDto) {
		AccountMoneyRecordEntity accountMoneyRecordEntity = new AccountMoneyRecordEntity();
    	BeanHelper.copyProperties(accountMoneyRecordEntity, accountMoneyRecordDto);
    	accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);
		return accountMoneyRecordDto;
	}
	/**
	 * 删除
	 * @param accountDto
	 */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public void delete(@RequestBody AccountMoneyRecordDto accountMoneyRecordDto){
		//TODO
	}
	
	/**
	 * 更新
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="update",method = RequestMethod.PUT)
	public AccountMoneyRecordDto update(@RequestBody AccountMoneyRecordDto accountMoneyRecordDto) {
		AccountMoneyRecordEntity accountMoneyRecordEntity = new AccountMoneyRecordEntity();
    	BeanHelper.copyProperties(accountMoneyRecordEntity, accountMoneyRecordDto);
    	accountMoneyRecordMapper.updateByPrimaryKeySelective(accountMoneyRecordEntity);
		return accountMoneyRecordDto;
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getById",method = RequestMethod.GET)
	public AccountMoneyRecordDto getById(@RequestParam Long id) {

		AccountMoneyRecordEntity accountMoneyRecordEntity = accountMoneyRecordMapper.selectByPrimaryKey(id);
		if (accountMoneyRecordEntity == null) {
			return null;
		}
		AccountMoneyRecordDto accountMoneyRecordDto = new AccountMoneyRecordDto();
		BeanHelper.copyProperties(accountMoneyRecordDto, accountMoneyRecordEntity);

		return accountMoneyRecordDto;
	}
	
	
	/**
	 * 分页查找
	 * @param query
	 * @return
	 */
	@RequestMapping(value="listPageByCondition",method = RequestMethod.POST)
    public Pagination<AccountMoneyRecordDto> listPageByCondition(@RequestBody AccountMoneyRecordQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<AccountMoneyRecordEntity> accountMoneyRecordList = accountMoneyRecordMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(accountMoneyRecordList);

        List<AccountMoneyRecordDto> accountMoneyRecordDtoList = Lists.newArrayList();

        if (CollectionUtils.isEmpty(accountMoneyRecordList)) {
            return new Pagination(query, accountMoneyRecordList, 0);
        }

        accountMoneyRecordList.forEach( entity -> {
        	AccountMoneyRecordDto accountMoneyRecordDto = new AccountMoneyRecordDto();
            BeanHelper.copyProperties(accountMoneyRecordDto, entity);
            accountMoneyRecordDtoList.add(accountMoneyRecordDto);
        });

        return new Pagination(query, accountMoneyRecordDtoList, (int) pageInfo.getTotal());
    }

}
