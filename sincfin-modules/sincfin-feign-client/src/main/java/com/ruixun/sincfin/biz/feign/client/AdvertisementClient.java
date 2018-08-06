package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AdvertisementDto;
import com.ruixun.sincfin.domain.query.AdvertisementQuery;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiantiea on 2018/6/5.
 */
@FeignClient("${biz.feign.name}")
public interface AdvertisementClient {

    @RequestMapping("/advertisement/insert")
    AdvertisementDto insert(@RequestBody AdvertisementDto advertisementDto);

    @RequestMapping("/advertisement/update")
    AdvertisementDto update(@RequestBody AdvertisementDto advertisementDto);

    @RequestMapping("/advertisement/getById")
    AdvertisementDto getById(@RequestParam("id") Long id);

    @RequestMapping("/advertisement/listPageByCondition")
    Pagination<AdvertisementDto> listPageByCondition(@RequestBody AdvertisementQuery query);

    
    @RequestMapping("/advertisement/listByPosition")
	List<AdvertisementDto> listByPosition(@RequestParam("position")String position,@RequestParam("packageType")String packageType);

}
