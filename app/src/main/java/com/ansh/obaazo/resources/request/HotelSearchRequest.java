package com.ansh.obaazo.resources.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.ansh.obaazo.web.ApiRequest;

public class HotelSearchRequest extends ApiRequest implements Parcelable {
    private double latitude;
    private double longitude;
    private String checkInDate;
    private String checkOutDate;
    private String min;
    private String max;
    private String star;
    private String hotelType;
    private String aminity;


    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getAminity() {
        return aminity;
    }

    public void setAminity(String aminity) {
        this.aminity = aminity;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.checkInDate);
        dest.writeString(this.checkOutDate);
        dest.writeString(this.min);
        dest.writeString(this.max);
        dest.writeString(this.star);
        dest.writeString(this.hotelType);
        dest.writeString(this.aminity);
    }

    public HotelSearchRequest() {
    }

    protected HotelSearchRequest(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.checkInDate = in.readString();
        this.checkOutDate = in.readString();
        this.min = in.readString();
        this.max = in.readString();
        this.star = in.readString();
        this.hotelType = in.readString();
        this.aminity = in.readString();
    }

    public static final Parcelable.Creator<HotelSearchRequest> CREATOR = new Parcelable.Creator<HotelSearchRequest>() {
        @Override
        public HotelSearchRequest createFromParcel(Parcel source) {
            return new HotelSearchRequest(source);
        }

        @Override
        public HotelSearchRequest[] newArray(int size) {
            return new HotelSearchRequest[size];
        }
    };
}
