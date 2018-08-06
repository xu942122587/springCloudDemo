package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.CouponUserClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.enums.CouponTypeEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.CouponUserQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/6/4.
 */
@RestController
@RequestMapping("couponUser")
public class CouponUserController extends BaseController {

    @Resource
    private CouponUserClient couponUserClient;

    @RequestMapping("listBonusPage")
    public ApiResponse listBonusPage(CouponUserQuery query) {

        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getCouponId());
        query.setCouponType(CouponTypeEnum.BONUS.getCode());
        return ApiResponse.successApiResponse(couponUserClient.listManagerPageByCondition(query));
    }

    @RequestMapping("listBonusPageByUser")
    public ApiResponse listBonusPageByUser(CouponUserQuery query) {

        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getUserId());
        query.setCouponType(CouponTypeEnum.BONUS.getCode());
        return ApiResponse.successApiResponse(couponUserClient.listManagerPageByCondition(query));
    }

}
