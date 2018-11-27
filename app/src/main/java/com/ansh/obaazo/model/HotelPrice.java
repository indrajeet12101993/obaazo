package com.ansh.obaazo.model;

import com.google.gson.annotations.SerializedName;

public class HotelPrice {
    @SerializedName("adult_price")
    private String adultPrice;
    @SerializedName("hotel_id")
    private String hotelId;
    @SerializedName("room_id")
    private String roomId;

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

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
