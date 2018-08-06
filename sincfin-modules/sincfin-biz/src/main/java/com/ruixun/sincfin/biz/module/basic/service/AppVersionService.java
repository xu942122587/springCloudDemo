package com.ruixun.sincfin.biz.module.basic.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.AppVersionClient;
import com.ruixun.sincfin.biz.module.basic.mapper.AppVersionMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AppVersionDto;
import com.ruixun.sincfin.domain.entity.AppVersionEntity;
import com.ruixun.sincfin.domain.query.AppVersionQuery;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("appVersion")
public class AppVersionService implements AppVersionClient {

	@Resource
	private AppVersionMapper appVersionMapper;

	@RequestMapping("insert")
	public AppVersionDto insert(@RequestBody AppVersionDto appVersionDto) {
		AppVersionEntity appVersionEntity = new AppVersionEntity();
		BeanHelper.copyProperties(appVersionEntity, appVersionDto);
		appVersionMapper.insertSelective(appVersionEntity);
		appVersionDto.setId(appVersionEntity.getId());
		return appVersionDto;
	}

	@RequestMapping("update")
	public AppVersionDto update(@RequestBody AppVersionDto appVersionDto) {
		AppVersionEntity appVersionEntity = new AppVersionEntity();
		BeanHelper.copyProperties(appVersionEntity, appVersionDto);
		appVersionMapper.updateByPrimaryKeySelective(appVersionEntity);
		return appVersionDto;
	}

	@RequestMapping("getById")
	public AppVersionDto getById(@RequestParam("id") Long id) {

		AppVersionDto bankDto = new AppVersionDto();
		AppVersionEntity appVersionEntity = appVersionMapper.selectByPrimaryKey(id);
		BeanHelper.copyProperties(bankDto, appVersionEntity);
		return bankDto;

	}

	@RequestMapping("listPageByCondition")
	public Pagination<AppVersionDto> listPageByCondition(@RequestBody AppVersionQuery query) {

		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<AppVersionDto> dtoList = appVersionMapper.listByCondition(query);
		PageInfo pageInfo = new PageInfo(dtoList);

		return new Pagination(query, dtoList, (int) pageInfo.getTotal());
	}

	@RequestMapping("getMaxVersion")
	public AppVersionDto getMaxVersion(@RequestBody AppVersionDto appVersionDto) {
		AppVersionDto appVersion = null;

		AppVersionEntity appVersionEntity = appVersionMapper.getMaxVersion(appVersionDto);
		if (appVersionEntity != null) {
			appVersion = new AppVersionDto();
			BeanHelper.copyProperties(appVersion, appVersionEntity);
		}

		return appVersion;
	}

}
