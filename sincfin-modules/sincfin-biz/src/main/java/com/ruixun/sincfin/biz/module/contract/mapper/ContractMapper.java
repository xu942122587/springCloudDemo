package com.ruixun.sincfin.biz.module.contract.mapper;

import com.ruixun.sincfin.domain.dto.ContractDto;
import com.ruixun.sincfin.domain.entity.ContractEntity;
import com.ruixun.sincfin.domain.query.ContractQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractMapper {
    int insert(ContractEntity record);

    int insertSelective(ContractEntity record);

    ContractEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractEntity record);

    int updateByPrimaryKey(ContractEntity record);

    List<ContractDto> listByCondition(ContractQuery query);

    int deleteById(@Param("id") Long id);

    int updateUsableBalance(@Param("id") Long id,
                            @Param("usableBalance") Long usableBalance);

    int updateProductCount(@Param("id") Long id,
                           @Param("productCount") Integer productCount);

}