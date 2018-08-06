package com.ruixun.sincfin.biz.module.user.mapper;

import com.ruixun.sincfin.common.dao.EntityDao;
import com.ruixun.sincfin.domain.dto.InvitationRecordDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.entity.UserEntity;
import com.ruixun.sincfin.domain.query.UserQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper extends EntityDao<UserEntity>{

    UserEntity getByMobile(String mobile);

    List<UserDto> listManagerByCondition(UserQuery query);
    /**
     * 是否为新用户，根据投资和未投资为依据判断
     * @param userId
     * @return
     */
    boolean isNewUser(Long userId);
    
    /**
     * 根据身份证号获取用户
     * @param idCard
     * @return
     */
	UserEntity getByIdCord(String idCard);

	/**
	 * 更新用户风险评测
	 * @param userId
	 * @param riskType
	 * @return
	 */
	int updateRiskTest(@Param("userId")Long userId, @Param("riskType")Integer riskType);

	/**
	 * 查询邀友记录
	 * @param query
	 * @return
	 */
	List<InvitationRecordDto> listInvitationRecord(UserQuery query);

	int updateFirstInvestmentByTask();
	
	  
	    /**  
	     * @Description 根据日期查询单日用户注册数 
	     * @Author      HJJ 
	     * @Date        2018年7月17日 下午3:38:55  
	     * @param @param date
	     * @param @return 参数  
	     * @return int 返回类型   
	     * @throws  
	     */  
	    
	int getRegUserCountsByDate(@Param("date") String date);

	  
	    /**  
	     * @Description 根据日期查询单日注册并投资的用户数
	     * @Author      HJJ 
	     * @Date        2018年7月17日 下午3:49:51  
	     * @param @param date
	     * @param @return 参数  
	     * @return int 返回类型   
	     * @throws  
	     */  
	    
	int getRegUserAndInvestmentCountsByDate(@Param("date") String date);
}