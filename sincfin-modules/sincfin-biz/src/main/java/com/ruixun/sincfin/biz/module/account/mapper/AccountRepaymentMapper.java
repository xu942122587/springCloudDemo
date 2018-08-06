package com.ruixun.sincfin.biz.module.account.mapper;

import com.ruixun.sincfin.domain.dto.AccountRepaymentDto;
import com.ruixun.sincfin.domain.entity.AccountRepaymentEntity;
import com.ruixun.sincfin.domain.query.AccountRepaymentQuery;

import java.util.List;

public interface AccountRepaymentMapper {
    int insert(AccountRepaymentEntity record);

    int insertSelective(AccountRepaymentEntity record);

    AccountRepaymentEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountRepaymentEntity record);

    int updateByPrimaryKey(AccountRepaymentEntity record);

    List<AccountRepaymentDto> listByCondition(AccountRepaymentQuery query);

    int insertBatch(List<AccountRepaymentEntity> list);

}