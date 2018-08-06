package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.entity.ManagerUserEntity;
import com.ruixun.sincfin.domain.query.ManagerUserQuery;

import java.util.List;

public interface ManagerUserMapper {
    int insert(ManagerUserEntity record);

    int insertSelective(ManagerUserEntity record);

    ManagerUserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerUserEntity record);

    int updateByPrimaryKey(ManagerUserEntity record);

    int deleteByPrimaryKey(Long id);

    ManagerUserEntity getByUserName(String userName);

    List<ManagerUserDto> listByCondition(ManagerUserQuery query);

    int countByCondition(ManagerUserQuery query);

}