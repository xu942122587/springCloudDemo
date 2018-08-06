package com.ruixun.sincfin.mobile.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.AdvertisementClient;
import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.enums.AdvertisementPositionEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;


@RestController
public class IndexController {
	
	@Resource
	public AdvertisementClient advertisementClient;
	
	/**
     * 首页轮播banner
     *
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value = "/banner", method = RequestMethod.GET)
    public ApiResponse banner(@RequestParam(value = "packageType", defaultValue = "basic") String packageType) {
    	List<AdvertisementDto> list = advertisementClient.listByPosition(AdvertisementPositionEnum.BANNER.getCode(),packageType);
        return ApiResponse.successApiResponse(list);
    }
    
    /**
     * 首页导航
     *
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public ApiResponse nav(@RequestParam(value = "packageType", defaultValue = "basic") String packageType) {
    	List<AdvertisementDto> list = advertisementClient.listByPosition(AdvertisementPositionEnum.NAVIGATION.getCode(),packageType);
        return ApiResponse.successApiResponse(list);
    }

}
