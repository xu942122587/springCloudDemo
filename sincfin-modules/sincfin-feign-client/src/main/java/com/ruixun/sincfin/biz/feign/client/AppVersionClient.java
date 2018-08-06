package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AppVersionDto;
import com.ruixun.sincfin.domain.query.AppVersionQuery;

/**
 * Created by tiea on 2018/6/3.
 */
@FeignClient("${biz.feign.name}")
public interface AppVersionClient {

	@RequestMapping("/appVersion/insert")
	AppVersionDto insert(@RequestBody AppVersionDto bankDto);

	@RequestMapping("/appVersion/update")
	AppVersionDto update(@RequestBody AppVersionDto bankDto);

	@RequestMapping("/appVersion/getById")
	AppVersionDto getById(@RequestParam("id") Long id);

	@RequestMapping("/appVersion/listPageByCondition")
	Pagination<AppVersionDto> listPageByCondition(@RequestBody AppVersionQuery query);

	@RequestMapping("/appVersion/getMaxVersion")
	AppVersionDto getMaxVersion(AppVersionDto appVersionDto);

}
