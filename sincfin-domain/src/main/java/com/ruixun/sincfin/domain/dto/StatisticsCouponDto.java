package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tiea on 2018/6/2.
 */
public class StatisticsCouponDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long couponId;

    private String couponName;

    private Long bonusAmount;

    private Integer useCount;

    private Long useBonusAmount;

    private Long orderAmount;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Long getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Long bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Long getUseBonusAmount() {
        return useBonusAmount;
    }

    public void setUseBonusAmount(Long useBonusAmount) {
        this.useBonusAmount = useBonusAmount;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }
}
