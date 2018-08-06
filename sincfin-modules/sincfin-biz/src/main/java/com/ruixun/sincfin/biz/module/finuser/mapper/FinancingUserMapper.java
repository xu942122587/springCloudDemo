package com.ruixun.sincfin.biz.module.finuser.mapper;

import com.ruixun.sincfin.domain.dto.FinancingUserCountDto;
import com.ruixun.sincfin.domain.entity.FinancingUserEntity;
import com.ruixun.sincfin.domain.query.FinancingUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinancingUserMapper {
	
	
    int insert(FinancingUserEntity record);

    int insertSelective(FinancingUserEntity record);

    FinancingUserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancingUserEntity record);

    int updateByPrimaryKey(FinancingUserEntity record);

    List<FinancingUserEntity> listByCondition(FinancingUserQuery query);

    FinancingUserEntity getByRealName(String realName);

    /**
     * 借款人借款数据统计
     * @param financingUserId
     * @return
     */
	FinancingUserCountDto financingCount(Long financingUserId);


    int updateTotalFinancingAmount(@Param("financingUserId") Long financingUserId,
                                   @Param("amount") Long amount);

    int updateTotalUnpaidPrincipal(@Param("financingUserId") Long financingUserId,
                                   @Param("amount") Long amount);


}