package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.UserBankClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.UserBankQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author t
 * @date 2018/5/23 15:02
 */
@RestController
@RequestMapping("/userBank")
public class UserBankController extends BaseController {

    @Resource
    private UserBankClient userBankClient;

    @RequestMapping("listPageByUser")
    public ApiResponse listPage(UserBankQuery query) {
        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getUserId());

        query.setBindStatus(UserBankBindStatusEnum.BIND.getCode());
        return ApiResponse.successApiResponse(userBankClient.listPageByCondition(query));

    }

}
