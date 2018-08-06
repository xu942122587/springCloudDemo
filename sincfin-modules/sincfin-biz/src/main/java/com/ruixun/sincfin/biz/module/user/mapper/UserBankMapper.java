package com.ruixun.sincfin.biz.module.user.mapper;

import java.util.List;

import com.ruixun.sincfin.common.dao.EntityDao;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.UserBankDto;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.query.UserBankQuery;
import org.apache.ibatis.annotations.Param;

public interface UserBankMapper extends EntityDao<UserBankEntity>{

    List<UserBankDto> listByCondition(UserBankQuery query);

	UserBankEntity selectRecentUpdate(Long userId);

	/**
	 * 根据卡号获取银行卡信息
	 * @param bankCardNo
	 * @return
	 */
	UserBankEntity getByCard(String bankCardNo);
	/**
	 * 根据id获取可用的银行卡
	 * @param bankCardNo
	 * @return
	 */
	UserBankEntity getAvailable(Long id);

	/**
	 * 判断用户是否绑定银行卡
	 * @param userId
	 * @return
	 */
	boolean isBindBankCode(Long userId);

	int updateTotalWithdraw(@Param("id") Long id, @Param("amount") Long amount);

	/**
	 * 变更银行卡通过 - 解绑
	 * @param id
	 * @return
	 */
	int updateChangeUnbind(Long id);

	/**
	 * 变更银行卡拒绝 - 绑定
	 * @param id
	 * @return
	 */
	int updateChangeBind(Long id);

	/**
	 * 银行卡变更逻辑
	 * @param beforeUserBankId
	 * @param afterUserBankId
	 * @return
	 */
	int updateUserBankChange(@Param("beforeUserBankId") Long beforeUserBankId,
							 @Param("afterUserBankId") Long afterUserBankId);
	
	
	List<UserBankEntity> listByUser(UserBankQuery query);


}