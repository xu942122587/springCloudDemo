package com.ruixun.sincfin.biz.module.account.mapper;

import java.util.List;

import com.ruixun.sincfin.domain.dto.AccountRechargeDto;
import com.ruixun.sincfin.domain.entity.AccountRechargeEntity;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;

public interface AccountRechargeMapper {

	int insert(AccountRechargeEntity record);

	int insertSelective(AccountRechargeEntity record);

	AccountRechargeEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AccountRechargeEntity record);

	int updateByPrimaryKey(AccountRechargeEntity record);

	List<AccountRechargeEntity> selectByUserId(Long userId);

	List<AccountRechargeDto> listByCondition(AccountRechargeQuery query);

	List<AccountRechargeDto> listManagerByCondition(AccountRechargeQuery query);

	/**
	 * 根据充值码获取充值记录
	 * 
	 * @param rechargeNum
	 * @return
	 */
	AccountRechargeEntity getByRechargeNum(String rechargeNum);

	/**
	 * 根据状态获取充值记录
	 * 
	 * @return
	 */
	List<AccountRechargeEntity> listByStatus(String status);

	/** 查询用户充值次数 */
	int selectChargeCount(Long userId);

}