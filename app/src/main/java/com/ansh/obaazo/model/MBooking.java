package com.ansh.obaazo.model;

/**
 * Created by Jeevan Gupta on 12/18/2018.
 */
public class MBooking {
    private String hotelId;
    private String adultCount;
    private String childCount;
    private String adultPrice;
    private String childPrice;
    private String extraGst;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(String adultCount) {
        this.adultCount = adultCount;
    }

    public String getChildCount() {
        return childCount;
    }

    public void setChildCount(String childCount) {
        this.childCount = childCount;
    }

    public String getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(String adultPrice) {
        this.adultPrice = adultPrice;
    }

    public String getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(String childPrice) {
        this.childPrice = childPrice;
    }

    public String getExtraGst() {
        return extraGst;
    }

    public void setExtraGst(String extraGst) {
        this.extraGst = extraGst;
    }
}
