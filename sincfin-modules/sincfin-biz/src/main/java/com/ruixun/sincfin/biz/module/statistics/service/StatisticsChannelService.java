package com.ruixun.sincfin.biz.module.statistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.StatisticsChannelClient;
import com.ruixun.sincfin.biz.module.statistics.mapper.StatisticsChannelMapper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.dto.StatisticsBillDto;
import com.ruixun.sincfin.domain.dto.StatisticsChannelDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.enums.ChannelUserExportTypeEnum;
import com.ruixun.sincfin.domain.query.StatisticsChannelQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tiea on 2018/6/2.
 */
@RestController
@RequestMapping("statisticsChannel")
public class StatisticsChannelService implements StatisticsChannelClient {


    @Resource
    private StatisticsChannelMapper statisticsChannelMapper;

    @RequestMapping("listPageStatistics")
    public Pagination<StatisticsChannelDto> listPageStatistics(@RequestBody StatisticsChannelQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<StatisticsChannelDto> statisticsChannelDtoList = statisticsChannelMapper.listChannel(query);
        PageInfo pageInfo = new PageInfo(statisticsChannelDtoList);
        if (CollectionUtils.isEmpty(statisticsChannelDtoList)) {
            return new Pagination(query, statisticsChannelDtoList, (int) pageInfo.getTotal());
        }
        buildZeroObject(statisticsChannelDtoList);
        List<Long> channelIdList = statisticsChannelDtoList.stream()
                .map(dto -> dto.getId()).collect(Collectors.toList());

        query.setChannelIdList(channelIdList);

        Map<Long, StatisticsChannelDto> idMap = statisticsChannelDtoList.stream().collect(
                Collectors.toMap(StatisticsChannelDto::getId, e -> e));

        // 注册数量
        List<StatisticsChannelDto> listRegisterCount = statisticsChannelMapper.listRegisterCount(query);
        for (StatisticsChannelDto dto : listRegisterCount) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setRegisterCount(dto.getRegisterCount());
            }
        }

        // 首投数量
        List<StatisticsChannelDto> listFirstInvestmentCount = statisticsChannelMapper.listFirstInvestmentCount(query);
        for (StatisticsChannelDto dto : listFirstInvestmentCount) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setFirstInvestmentCount(dto.getFirstInvestmentCount());
            }
        }

        // 复投数量
        List<StatisticsChannelDto> listRepeatedlyInvestmentCount = statisticsChannelMapper.listRepeatedlyInvestmentCount(query);
        for (StatisticsChannelDto dto : listRepeatedlyInvestmentCount) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setRepeatedlyInvestmentCount(dto.getRepeatedlyInvestmentCount());
            }
        }

        // 首投金额
        List<StatisticsChannelDto> listFirstInvestmentAmount = statisticsChannelMapper.listFirstInvestmentAmount(query);
        for (StatisticsChannelDto dto : listFirstInvestmentAmount) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setFirstInvestmentAmount(dto.getFirstInvestmentAmount());
            }
        }

        // 总金额 及 复投金额
        List<StatisticsChannelDto> listTotalInvestmentAmount = statisticsChannelMapper.listTotalInvestmentAmount(query);
        for (StatisticsChannelDto dto : listTotalInvestmentAmount) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setTotalInvestmentAmount(dto.getTotalInvestmentAmount());
                channelDto.setRepeatedlyInvestmentAmount(dto.getTotalInvestmentAmount()
                        - channelDto.getFirstInvestmentAmount());
            }
        }

        // 首投用户期间存量
        List<StatisticsChannelDto> listTimeIntervalStock = statisticsChannelMapper.listTimeIntervalStock(query);
        for (StatisticsChannelDto dto : listTimeIntervalStock) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setTimeIntervalStock(dto.getTimeIntervalStock());
            }
        }

        // 首投用户当前存量
        List<StatisticsChannelDto> listCurrentStock = statisticsChannelMapper.listCurrentStock(query);
        for (StatisticsChannelDto dto : listCurrentStock) {
            StatisticsChannelDto channelDto = idMap.get(dto.getChannelId());
            if (channelDto != null) {
                channelDto.setCurrentStock(dto.getCurrentStock());
            }
        }

        return new Pagination(query, statisticsChannelDtoList, (int) pageInfo.getTotal());
    }

    @Override
    @RequestMapping("listUser")
    public List<UserDto> listUser(@RequestBody StatisticsChannelQuery query) {
        if (ChannelUserExportTypeEnum.ACTIVATE.getCode().equals(query.getExportType())) {
            return Lists.newArrayList();
        } else if (ChannelUserExportTypeEnum.REGISTER.getCode().equals(query.getExportType())) {
            return statisticsChannelMapper.listRegisterUser(query);
        } else if (ChannelUserExportTypeEnum.FIRST_INVESTMENT.getCode().equals(query.getExportType())) {
            return statisticsChannelMapper.listFirstInvestmentUser(query);
        } else if (ChannelUserExportTypeEnum.REPEATEDLY_INVESTMENT.getCode().equals(query.getExportType())) {
            return statisticsChannelMapper.listRepeatedlyInvestmentUser(query);
        }
        return Lists.newArrayList();
    }

    private void buildZeroObject(List<StatisticsChannelDto> statisticsChannelDtoList) {
        statisticsChannelDtoList.forEach( dto -> {
            dto.setActivateCount(0);
            dto.setRegisterCount(0);
            dto.setFirstInvestmentCount(0);
            dto.setRepeatedlyInvestmentCount(0);
            dto.setFirstInvestmentAmount(0L);
            dto.setRepeatedlyInvestmentAmount(0L);
            dto.setTotalInvestmentAmount(0L);
            dto.setTimeIntervalStock(0L);
            dto.setCurrentStock(0L);
        });
    }

}
