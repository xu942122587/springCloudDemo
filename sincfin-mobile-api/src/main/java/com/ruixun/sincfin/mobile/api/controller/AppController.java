package com.ruixun.sincfin.mobile.api.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.AppVersionClient;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.dto.AppVersionDto;
import com.ruixun.sincfin.domain.enums.DeviceTypeEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

@RestController
@RequestMapping("app")
public class AppController extends BaseController {

	@Resource
	public AppVersionClient appVersionClient;

	@RequestMapping(value = "max/version", method = RequestMethod.GET)
	public ApiResponse version(AppVersionDto appVersionDto) {
		if (appVersionDto != null && DeviceTypeEnum.fromCode(appVersionDto.getDeviceType()) == null) {
			throw new BizException(ApiStatusEnum.CLIENT_PARAM_ERROR);
		}
		String channelId = getRequest().getHeader(ConfigConstants.HTTP_HEADER_CHANNEL_ID);
		if (StringUtils.isNotBlank(channelId) && StringUtils.isLong(channelId)) {
			appVersionDto.setChannelId(Long.parseLong(channelId));
		}
		AppVersionDto appVo = appVersionClient.getMaxVersion(appVersionDto);
		if (appVo == null) {
			throw new BizException(ApiStatusEnum.DATA_NOT_FOUND);
		}
		return ApiResponse.successApiResponse(appVo);
	}

}
