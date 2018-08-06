package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.CouponClient;
import com.ruixun.sincfin.domain.dto.StatisticsCouponDto;
import com.ruixun.sincfin.domain.enums.CouponTypeEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.CouponQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/6/4.
 */
@RestController
@RequestMapping("coupon")
public class CouponController extends BaseController {

    @Resource
    private CouponClient couponClient;

    @RequestMapping("listBonusPage")
    public ApiResponse listBonusPage(CouponQuery query) {

        query.setType(CouponTypeEnum.BONUS.getCode());
        return ApiResponse.successApiResponse(couponClient.listPageByCondition(query));
    }

    @RequestMapping("listInterestPage")
    public ApiResponse listInterestPage(CouponQuery query) {

        query.setType(CouponTypeEnum.INTEREST.getCode());
        return ApiResponse.successApiResponse(couponClient.listPageByCondition(query));
    }

    @RequestMapping("getStatisticsCoupon")
    public ApiResponse getStatisticsCoupon() {
        return ApiResponse.successApiResponse(
                couponClient.getStatisticsCoupon());
    }

    @RequestMapping("getStatisticsCouponById")
    public ApiResponse getStatisticsCouponById(Long couponId) {
        return ApiResponse.successApiResponse(
                couponClient.getStatisticsCouponById(couponId));
    }

}
