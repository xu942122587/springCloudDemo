package com.ruixun.sincfin.biz.module.finuser.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.FinancingUserRemittanceClient;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserRemittanceMapper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("financingUserRemittance")
public class FinancingUserRemittanceService implements FinancingUserRemittanceClient {

    @Resource
    private FinancingUserRemittanceMapper financingUserRemittanceMapper;

    @RequestMapping("listPageByCondition")
    public Pagination<FinancingUserRemittanceDto> listPageByCondition(
            @RequestBody FinancingUserRemittanceQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<FinancingUserRemittanceDto> financingUserRemittanceDtoList
                = financingUserRemittanceMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(financingUserRemittanceDtoList);

        return new Pagination(query, financingUserRemittanceDtoList, (int) pageInfo.getTotal());
    }
}
