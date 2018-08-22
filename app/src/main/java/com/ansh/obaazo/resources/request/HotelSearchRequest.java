package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.web.ApiRequest;

public class HotelSearchRequest extends ApiRequest {
    private double latitude = 28.5355;
    private double longitude = 77.3910;
    private String checkInDate = "09/2/2018";
    private String checkOutDate = "09/2/2018";


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
