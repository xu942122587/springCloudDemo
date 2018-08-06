package com.ruixun.sincfin.mobile.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.AdvertisementClient;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.enums.AdvertisementPositionEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

@RestController
@RequestMapping("advertisement")
public class AdvertisementController {
	
	@Resource
	public AdvertisementClient advertisementClient;
	
	
	/**
     * 广告list
     *
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResponse list(@RequestParam(value = "packageType", defaultValue = "basic") String packageType,
    		@RequestParam(value = "position",required = true) String position) {
    	if(AdvertisementPositionEnum.fromCode(position)==null){
    		throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
    	}
    	List<AdvertisementDto> list = advertisementClient.listByPosition(position,packageType);
        return ApiResponse.successApiResponse(list);
    }
    
    

}
