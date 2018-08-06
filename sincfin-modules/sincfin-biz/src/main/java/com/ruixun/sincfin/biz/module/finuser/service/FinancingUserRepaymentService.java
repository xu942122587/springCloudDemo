package com.ruixun.sincfin.biz.module.finuser.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.FinancingUserRepaymentClient;
import com.ruixun.sincfin.biz.module.finuser.mapper.FinancingUserRepaymentMapper;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.dto.RemittanceAndReplayDto;
import com.ruixun.sincfin.domain.entity.FinancingUserRepaymentEntity;
import com.ruixun.sincfin.domain.enums.FinancingUserRepaymentStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceAndRepaymentQuery;
import com.ruixun.sincfin.domain.query.FinancingUserRepaymentQuery;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("financingUserRepayment")
public class FinancingUserRepaymentService implements FinancingUserRepaymentClient {

    @Resource
    private FinancingUserRepaymentMapper financingUserRepaymentMapper;

    @RequestMapping("listPageByCondition")
    public Pagination<FinancingUserRepaymentDto> listPageByCondition(
            @RequestBody FinancingUserRepaymentQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<FinancingUserRepaymentDto> financingUserRepaymentDtoList
                = financingUserRepaymentMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(financingUserRepaymentDtoList);

        return new Pagination(query, financingUserRepaymentDtoList, (int) pageInfo.getTotal());
    }
    
    @RequestMapping("listPageSelect")
    public Pagination<RemittanceAndReplayDto> listPageSelect(@RequestBody FinancingUserRemittanceAndRepaymentQuery query) {
    	
    	PageHelper.startPage(query.getPageIndex(), query.getPageSize());
    	List<RemittanceAndReplayDto> financingUserRepaymentDtoList
    	= financingUserRepaymentMapper.listPageSelect(query);
    	PageInfo pageInfo = new PageInfo(financingUserRepaymentDtoList);
    	
    	return new Pagination(query, financingUserRepaymentDtoList, (int) pageInfo.getTotal());
    }

    @RequestMapping("ensureRepaymentById")
	public int ensureRepaymentById(Long id) {
    	FinancingUserRepaymentEntity financingUserRepaymentEntity = financingUserRepaymentMapper.selectByPrimaryKey(id);
		if (financingUserRepaymentEntity == null) {
			throw new BizException(ApiStatusEnum.PRODUCE_REPAYMENT_NOT_EXIST);
		}
		if (!FinancingUserRepaymentStatusEnum.NO_REPAYMENT.getCode().equals(financingUserRepaymentEntity.getStatus())) {
			throw new BizException(ApiStatusEnum.PRODUCE_REPAYMENT_STATUS_ERROR);
		}

		FinancingUserRepaymentEntity updateFinancingUserRepaymentEntity = new FinancingUserRepaymentEntity();
		updateFinancingUserRepaymentEntity.setId(id);
		updateFinancingUserRepaymentEntity.setStatus(FinancingUserRepaymentStatusEnum.REPAYMENT.getCode());
		return financingUserRepaymentMapper.updateByPrimaryKeySelective(updateFinancingUserRepaymentEntity);
	}

}
