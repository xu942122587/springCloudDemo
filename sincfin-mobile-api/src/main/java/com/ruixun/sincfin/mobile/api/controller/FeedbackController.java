package com.ruixun.sincfin.mobile.api.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.FeedbackClient;
import com.ruixun.sincfin.domain.dto.FeedbackDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

@RestController
@RequestMapping("feedback")
public class FeedbackController extends BaseController {
	
	@Resource
    private FeedbackClient feedbackClient;
	
	
	/**
     * 提交反馈意见
     * @return
     */
    @RequestMapping(value="add",method = RequestMethod.POST)
    public ApiResponse add(FeedbackDto dto) {
    	Long userId = getCurrentUserId();
    	if(userId!=null){
    		dto.setUserId(userId);
    	}
    	feedbackClient.add(dto);
        return ApiResponse.successApiResponse();
    }
	
	
	

}
