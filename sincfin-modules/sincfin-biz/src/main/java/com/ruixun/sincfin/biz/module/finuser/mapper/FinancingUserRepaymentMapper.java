package com.ruixun.sincfin.biz.module.finuser.mapper;

import java.util.List;

import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.dto.RemittanceAndReplayDto;
import com.ruixun.sincfin.domain.entity.FinancingUserRepaymentEntity;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceAndRepaymentQuery;
import com.ruixun.sincfin.domain.query.FinancingUserRepaymentQuery;

public interface FinancingUserRepaymentMapper {
	int insert(FinancingUserRepaymentEntity record);

	int insertSelective(FinancingUserRepaymentEntity record);

	FinancingUserRepaymentEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(FinancingUserRepaymentEntity record);

	int updateByPrimaryKey(FinancingUserRepaymentEntity record);

	List<FinancingUserRepaymentDto> listByCondition(FinancingUserRepaymentQuery query);
	
	List<RemittanceAndReplayDto> listPageSelect(FinancingUserRemittanceAndRepaymentQuery query);

	int updateFinancingRepaymentStatus(FinancingUserRepaymentEntity record);

}