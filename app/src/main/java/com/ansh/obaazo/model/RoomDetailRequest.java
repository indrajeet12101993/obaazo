package com.ansh.obaazo.model;

import com.google.gson.annotations.SerializedName;

public class RoomDetailRequest {


    @SerializedName("Adult")
    private String adult;
    @SerializedName("child")
    private String child;
    @SerializedName("room_no")
    private String roomNo;
    @SerializedName("hotel_id")
    private String hotelId;
    @SerializedName("room_id")
    private String roomId;
    @SerializedName("adult_price")
    private String adultPrice;
    @SerializedName("child_price")
    private String childPrice;
    @SerializedName("totalprice")
    private String totalPrice;


    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
