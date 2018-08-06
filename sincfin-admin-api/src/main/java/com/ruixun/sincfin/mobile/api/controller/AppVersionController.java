package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.AppVersionClient;
import com.ruixun.sincfin.biz.feign.client.BankClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.AppVersionDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.AppVersionQuery;
import com.ruixun.sincfin.domain.query.BankQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("appVersion")
public class AppVersionController extends BaseController {

    @Resource
    private AppVersionClient appVersionClient;

    @RequestMapping("insert")
    public ApiResponse insert(AppVersionDto appVersionDto) {
        Assert.assertNotNull(appVersionDto);
        Assert.assertNotNull(appVersionDto.getVersionName());
        return ApiResponse.successApiResponse(appVersionClient.insert(appVersionDto));
    }

    @RequestMapping("update")
    public ApiResponse update(AppVersionDto appVersionDto) {
        Assert.assertNotNull(appVersionDto);
        Assert.assertNotNull(appVersionDto.getId());
        return ApiResponse.successApiResponse(appVersionClient.update(appVersionDto));
    }

    @RequestMapping("getById")
    public ApiResponse getById(Long id) {
        Assert.assertNotNull(id);
        return ApiResponse.successApiResponse(appVersionClient.getById(id));
    }

    @RequestMapping("listPage")
    public ApiResponse listPage(AppVersionQuery query){
        return ApiResponse.successApiResponse(appVersionClient.listPageByCondition(query));
    }

    @RequestMapping("updateStatus")
    public ApiResponse updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        Assert.assertNotNull(id);
        Assert.assertNotNull(status);
        AppVersionDto appVersionDto = new AppVersionDto();
        appVersionDto.setId(id);
        appVersionDto.setStatus(status);
        return ApiResponse.successApiResponse(appVersionClient.update(appVersionDto));
    }
}
