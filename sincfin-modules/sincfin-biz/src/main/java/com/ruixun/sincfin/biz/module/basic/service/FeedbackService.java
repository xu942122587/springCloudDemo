package com.ruixun.sincfin.biz.module.basic.service;

import javax.annotation.Resource;

import com.ruixun.sincfin.biz.feign.client.FeedbackClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.module.basic.mapper.FeedbackMapper;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.dto.FeedbackDto;
import com.ruixun.sincfin.domain.entity.FeedbackEntity;

@RestController
@RequestMapping("feedback")
public class FeedbackService implements FeedbackClient {
	
	@Resource
	private FeedbackMapper feedbackMapper;
	
	/**
	 * 添加
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="add",method = RequestMethod.POST)
	public void add(@RequestBody FeedbackDto dto) {
		FeedbackEntity entity = new FeedbackEntity();
    	BeanHelper.copyProperties(entity, dto);
    	entity.setStatus("uncontact");
    	feedbackMapper.insertSelective(entity);
	}
	/**
	 * 删除
	 * @param accountDto
	 */
	@RequestMapping(value="delete",method = RequestMethod.DELETE)
	public void delete(@RequestBody FeedbackDto dto){
		//TODO
	}
	
	/**
	 * 更新
	 * @param accountDto
	 * @return
	 */
	@RequestMapping(value="update",method = RequestMethod.PUT)
	public FeedbackDto update(@RequestBody FeedbackDto dto) {
		FeedbackEntity entity = new FeedbackEntity();
    	BeanHelper.copyProperties(entity, dto);
    	feedbackMapper.updateByPrimaryKeySelective(entity);
		return dto;
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getById",method = RequestMethod.GET)
	public FeedbackDto getById(@RequestParam Long id) {

		FeedbackEntity entity = feedbackMapper.selectByPrimaryKey(id);
		if (entity == null) {
			return null;
		}
		FeedbackDto dto = new FeedbackDto();
		BeanHelper.copyProperties(dto, entity);

		return dto;
	}

}
