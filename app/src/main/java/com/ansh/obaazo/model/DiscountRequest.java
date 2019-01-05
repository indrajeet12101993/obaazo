package com.ansh.obaazo.model;

import com.google.gson.annotations.SerializedName;

public class DiscountRequest {

    @SerializedName("obaazo_moneyused")
    private String obaazoUsed;
    @SerializedName("obaazo_moneyreward")
    private String reward;
    @SerializedName("coupon_discount")
    private String couponDiscount;
    @SerializedName("coupon_name")
    private String couponName;

    public String getObaazoUsed() {
        return obaazoUsed;
    }

    public void setObaazoUsed(String obaazoUsed) {
        this.obaazoUsed = obaazoUsed;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(String couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
