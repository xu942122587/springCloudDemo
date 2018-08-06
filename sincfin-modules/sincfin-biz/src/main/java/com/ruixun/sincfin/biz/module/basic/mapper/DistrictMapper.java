package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.entity.DistrictEntity;

public interface DistrictMapper {
    int insert(DistrictEntity record);

    int insertSelective(DistrictEntity record);

    DistrictEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DistrictEntity record);

    int updateByPrimaryKey(DistrictEntity record);
}