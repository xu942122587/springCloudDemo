package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.dto.AppVersionDto;
import com.ruixun.sincfin.domain.entity.AppVersionEntity;
import com.ruixun.sincfin.domain.query.AppVersionQuery;

import java.util.List;

public interface AppVersionMapper {

    int insert(AppVersionEntity record);

    int insertSelective(AppVersionEntity record);

    AppVersionEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppVersionEntity record);

    int updateByPrimaryKey(AppVersionEntity record);

    List<AppVersionDto> listByCondition(AppVersionQuery query);

	AppVersionEntity getMaxVersion(AppVersionDto appVersionDto);

}