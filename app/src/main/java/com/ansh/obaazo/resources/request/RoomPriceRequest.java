package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.web.ApiRequest;

public class RoomPriceRequest extends ApiRequest {

    private String checkIn;
    private String checkOut;
    private String roomId;

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
