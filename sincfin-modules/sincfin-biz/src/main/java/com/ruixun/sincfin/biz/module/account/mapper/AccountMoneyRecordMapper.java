package com.ruixun.sincfin.biz.module.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruixun.sincfin.domain.entity.AccountMoneyRecordEntity;
import com.ruixun.sincfin.domain.query.AccountMoneyRecordQuery;

public interface AccountMoneyRecordMapper {
	
    int insert(AccountMoneyRecordEntity record);

    int insertSelective(AccountMoneyRecordEntity record);

    AccountMoneyRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountMoneyRecordEntity record);

    int updateByPrimaryKey(AccountMoneyRecordEntity record);

    List<AccountMoneyRecordEntity> selectByUserIdAndType(@Param("userId")Long userId, @Param("type")String type);

	List<AccountMoneyRecordEntity> listByCondition(AccountMoneyRecordQuery query);
}