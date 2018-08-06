package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.entity.AdvertisementEntity;
import com.ruixun.sincfin.domain.query.AdvertisementQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdvertisementMapper {
	
	
    int insert(AdvertisementEntity record);

    int insertSelective(AdvertisementEntity record);

    AdvertisementEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdvertisementEntity record);

    int updateByPrimaryKey(AdvertisementEntity record);

    List<AdvertisementDto> listByCondition(AdvertisementQuery query);

	List<AdvertisementDto> listByPosition(@Param("position")String position,@Param("packageType") String packageType);

    int updateUnshelve(@Param("position") String position);

    int countByPosition(@Param("position") String position);

}