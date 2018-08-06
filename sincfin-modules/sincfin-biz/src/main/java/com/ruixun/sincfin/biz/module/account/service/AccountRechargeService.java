package com.ruixun.sincfin.biz.module.account.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.AccountRechargeClient;
import com.ruixun.sincfin.biz.module.account.mapper.AccountRechargeMapper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountRechargeDto;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiantiea on 2018/5/24.
 */
@RestController
@RequestMapping("accountRecharge")
public class AccountRechargeService implements AccountRechargeClient {

    @Resource
    private AccountRechargeMapper accountRechargeMapper;

    @RequestMapping("listPageByCondition")
    public Pagination<AccountRechargeDto> listPageByCondition(@RequestBody AccountRechargeQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<AccountRechargeDto> dtoList = accountRechargeMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(dtoList);

        return new Pagination(query, dtoList, (int) pageInfo.getTotal());
    }


    /**
     * 管理平台 充值管理列表
     * @param query
     * @return
     */
    @RequestMapping("listManagerPage")
    public Pagination<AccountRechargeDto> listManagerPage(@RequestBody AccountRechargeQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<AccountRechargeDto> dtoList = accountRechargeMapper.listManagerByCondition(query);
        PageInfo pageInfo = new PageInfo(dtoList);

        return new Pagination(query, dtoList, (int) pageInfo.getTotal());
    }


}
