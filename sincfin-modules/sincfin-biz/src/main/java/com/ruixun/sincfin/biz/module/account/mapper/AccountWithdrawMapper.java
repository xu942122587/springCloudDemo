package com.ruixun.sincfin.biz.module.account.mapper;

import java.util.Date;
import java.util.List;

import com.ruixun.sincfin.domain.dto.AccountWithdrawDto;
import com.ruixun.sincfin.domain.entity.AccountWithdrawEntity;
import com.ruixun.sincfin.domain.query.AccountWithdrawQuery;
import org.apache.ibatis.annotations.Param;

public interface AccountWithdrawMapper {
	
    int insert(AccountWithdrawEntity record);

    int insertSelective(AccountWithdrawEntity record);

    AccountWithdrawEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountWithdrawEntity record);

    int updateByPrimaryKey(AccountWithdrawEntity record);


	List<AccountWithdrawEntity> selectByUserId(Long userId);

    List<AccountWithdrawDto> listByCondition(AccountWithdrawQuery query);

    /**
     * 管理平台 提现列表
     * @param query
     * @return
     */
    List<AccountWithdrawDto> listManagerByCondition(AccountWithdrawQuery query);

    int updatePass(@Param("idList") List<Long> idList, @Param("auditorId") Long auditorId);

    int updateReject(@Param("idList") List<Long> idList, @Param("auditorId") Long auditorId);

    int updateSuccess(@Param("idList") List<Long> idList, @Param("auditorId") Long auditorId);

	long countWithdrawByDate(@Param("userId")Long userId, @Param("time")Date time);
	
	long countTodayWithdraw(@Param("userId")Long userId);

}