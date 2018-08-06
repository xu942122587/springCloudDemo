package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.AdvertisementClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.enums.AdvertisementStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.AdvertisementQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/6/5.
 */
@RestController
@RequestMapping("advertisement")
public class AdvertisementController extends BaseController {

    @Resource
    private AdvertisementClient advertisementClient;

    @RequestMapping("insert")
    public ApiResponse insert(AdvertisementDto advertisementDto) {
        Assert.assertNotNull(advertisementDto);
        Assert.assertNotNull(advertisementDto.getPosition());
        // 默认下架状态
        advertisementDto.setStatus(AdvertisementStatusEnum.UNSHELVE.getCode());
        return ApiResponse.successApiResponse(advertisementClient.insert(advertisementDto));
    }

    @RequestMapping("update")
    public ApiResponse update(AdvertisementDto advertisementDto) {
        Assert.assertNotNull(advertisementDto);
        Assert.assertNotNull(advertisementDto.getId());
        return ApiResponse.successApiResponse(advertisementClient.update(advertisementDto));
    }

    @RequestMapping("getById")
    public ApiResponse getById(Long id) {
        Assert.assertNotNull(id);
        return ApiResponse.successApiResponse(advertisementClient.getById(id));

    }

    @RequestMapping("listPage")
    public ApiResponse listPage(AdvertisementQuery query){
        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getPosition());
        return ApiResponse.successApiResponse(advertisementClient.listPageByCondition(query));
    }

    @RequestMapping("updateStatus")
    public ApiResponse updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        Assert.assertNotNull(id);
        Assert.assertNotNull(status);
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setId(id);
        advertisementDto.setStatus(status);
        return ApiResponse.successApiResponse(advertisementClient.update(advertisementDto));
    }
    
}
