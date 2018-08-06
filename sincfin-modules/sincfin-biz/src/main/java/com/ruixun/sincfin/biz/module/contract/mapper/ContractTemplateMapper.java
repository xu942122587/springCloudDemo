package com.ruixun.sincfin.biz.module.contract.mapper;

import com.ruixun.sincfin.domain.entity.ContractTemplateEntity;
import com.ruixun.sincfin.domain.query.ContractTemplateQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractTemplateMapper {
    int insert(ContractTemplateEntity record);

    int insertSelective(ContractTemplateEntity record);

    ContractTemplateEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractTemplateEntity record);

    int updateByPrimaryKey(ContractTemplateEntity record);

    List<ContractTemplateEntity> listByCondition(ContractTemplateQuery query);

}