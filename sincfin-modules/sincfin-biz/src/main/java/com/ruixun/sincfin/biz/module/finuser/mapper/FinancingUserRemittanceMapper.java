package com.ruixun.sincfin.biz.module.finuser.mapper;

import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.entity.FinancingUserRemittanceEntity;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceQuery;

import java.util.List;

public interface FinancingUserRemittanceMapper {
    int insert(FinancingUserRemittanceEntity record);

    int insertSelective(FinancingUserRemittanceEntity record);

    FinancingUserRemittanceEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancingUserRemittanceEntity record);

    int updateByPrimaryKey(FinancingUserRemittanceEntity record);

    List<FinancingUserRemittanceDto> listByCondition(FinancingUserRemittanceQuery query);
    
    
    int updateFinancingRemittanceStatus(FinancingUserRemittanceEntity record);
    
    
    
}