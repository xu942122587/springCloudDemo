package com.ruixun.sincfin.biz.module.coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.CouponClient;
import com.ruixun.sincfin.biz.module.coupon.mapper.CouponMapper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.CouponDto;
import com.ruixun.sincfin.domain.dto.StatisticsCouponDto;
import com.ruixun.sincfin.domain.query.CouponQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiea on 2018/5/25.
 */
@RestController
@RequestMapping("coupon")
public class CouponService implements CouponClient {

    @Resource
    private CouponMapper couponMapper;

    @RequestMapping("listPageByCondition")
    public Pagination<CouponDto> listPageByCondition(@RequestBody CouponQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<CouponDto> dtoList = couponMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(dtoList);

        return new Pagination(query, dtoList, (int) pageInfo.getTotal());
    }


    @RequestMapping("getStatisticsCoupon")
    public StatisticsCouponDto getStatisticsCoupon() {
        return couponMapper.getStatisticsCoupon();
    }

    @RequestMapping("getStatisticsCouponById")
    public StatisticsCouponDto getStatisticsCouponById(@RequestParam("couponId") Long couponId) {
        return couponMapper.getStatisticsCouponById(couponId);
    }

}
