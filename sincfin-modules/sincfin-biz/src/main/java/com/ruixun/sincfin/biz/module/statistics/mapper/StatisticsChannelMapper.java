package com.ruixun.sincfin.biz.module.statistics.mapper;

import com.ruixun.sincfin.domain.dto.StatisticsBillDto;
import com.ruixun.sincfin.domain.dto.StatisticsChannelDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.entity.StatisticsChannelEntity;
import com.ruixun.sincfin.domain.query.StatisticsChannelQuery;

import java.util.List;

public interface StatisticsChannelMapper {
    int insert(StatisticsChannelEntity record);

    int insertSelective(StatisticsChannelEntity record);

    StatisticsChannelEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StatisticsChannelEntity record);

    int updateByPrimaryKey(StatisticsChannelEntity record);

    List<StatisticsChannelDto> listChannel(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listRegisterCount(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listFirstInvestmentCount(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listRepeatedlyInvestmentCount(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listFirstInvestmentAmount(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listTotalInvestmentAmount(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listTimeIntervalStock(StatisticsChannelQuery query);

    List<StatisticsChannelDto> listCurrentStock(StatisticsChannelQuery query);

    List<UserDto> listRegisterUser(StatisticsChannelQuery query);

    List<UserDto> listFirstInvestmentUser(StatisticsChannelQuery query);

    List<UserDto> listRepeatedlyInvestmentUser(StatisticsChannelQuery query);

}