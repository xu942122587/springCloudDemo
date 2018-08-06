package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.CouponDto;
import com.ruixun.sincfin.domain.dto.StatisticsCouponDto;
import com.ruixun.sincfin.domain.query.CouponQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiea on 2018/5/25.
 */
@FeignClient("${biz.feign.name}")
public interface CouponClient {

    @RequestMapping("/coupon/listPageByCondition")
    Pagination<CouponDto> listPageByCondition(@RequestBody CouponQuery query);

    @RequestMapping("/coupon/getStatisticsCoupon")
    StatisticsCouponDto getStatisticsCoupon();

    @RequestMapping("/coupon/getStatisticsCouponById")
    StatisticsCouponDto getStatisticsCouponById(@RequestParam("couponId") Long couponId);

}
