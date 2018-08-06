package com.ruixun.sincfin.mobile.api.controller;

import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.StatisticsChannelClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.enums.ChannelUserExportTypeEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.StatisticsChannelQuery;
import com.ruixun.sincfin.mobile.api.service.ExportExcelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("statisticsChannel")
public class StatisticsChannelController extends BaseController {

    @Resource
    private StatisticsChannelClient statisticsChannelClient;

    @Resource
    private ExportExcelService exportExcelService;

    @RequestMapping("listPageStatistics")
    public ApiResponse listPageStatistics(StatisticsChannelQuery query){
        return ApiResponse.successApiResponse(statisticsChannelClient.listPageStatistics(query));
    }

    @RequestMapping("exportListUser")
    public void  exportListUser(StatisticsChannelQuery query) throws IOException {
        Assert.assertNotNull(query);
        ChannelUserExportTypeEnum typeEnum = ChannelUserExportTypeEnum.fromCode(query.getExportType());
        Assert.assertNotNull(typeEnum);
        Assert.assertNotNull(query.getChannelId());

        exportExcelService.exportStatisticsChannelUser(statisticsChannelClient.listUser(query), typeEnum.getText());

    }

    @RequestMapping("exportListStatistics")
    public void  exportListStatistics(StatisticsChannelQuery query) throws IOException {
        List<Long> channelIdList = Lists.newArrayList();
        if (StringUtils.isNotEmpty(query.getChannelIds())) {
            channelIdList = Arrays.asList(query.getChannelIds().split(",")).stream()
                    .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        }
        Assert.assertNotNull(channelIdList);
        query.setChannelIdList(channelIdList);

        exportExcelService.exportStatisticsChannel(statisticsChannelClient.listPageStatistics(query).getData());
    }


}
