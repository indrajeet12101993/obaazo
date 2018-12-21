package com.ansh.obaazo.model;

/**
 * Created by Jeevan Gupta on 12/18/2018.
 */
public class MBooking {
    private int hotelId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    private int roomId;
    private int adultCount;

    public Double getRoomPriceWithoutGst() {
        return roomPriceWithoutGst;
    }

    public void setRoomPriceWithoutGst(Double roomPriceWithoutGst) {
        this.roomPriceWithoutGst = roomPriceWithoutGst;
    }

    private Double roomPriceWithoutGst;

    public Double getRoomGstPrice() {
        return roomGstPrice;
    }

    public void setRoomGstPrice(Double roomGstPrice) {
        this.roomGstPrice = roomGstPrice;
    }

    private Double roomGstPrice;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public Double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Double childPrice) {
        this.childPrice = childPrice;
    }

    public Double getExtraGst() {
        return extraGst;
    }

    public void setExtraGst(Double extraGst) {
        this.extraGst = extraGst;
    }

    private int childCount;
    private Double adultPrice;
    private Double childPrice;
    private Double extraGst;


}
