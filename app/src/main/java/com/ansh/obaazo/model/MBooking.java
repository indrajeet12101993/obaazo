package com.ansh.obaazo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jeevan Gupta on 12/18/2018.
 */
public class MBooking implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.hotelId);
        dest.writeInt(this.roomId);
        dest.writeInt(this.adultCount);
        dest.writeValue(this.roomPriceWithoutGst);
        dest.writeValue(this.roomGstPrice);
        dest.writeInt(this.childCount);
        dest.writeValue(this.adultPrice);
        dest.writeValue(this.childPrice);
        dest.writeValue(this.extraGst);
    }

    public MBooking() {
    }

    protected MBooking(Parcel in) {
        this.hotelId = in.readInt();
        this.roomId = in.readInt();
        this.adultCount = in.readInt();
        this.roomPriceWithoutGst = (Double) in.readValue(Double.class.getClassLoader());
        this.roomGstPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.childCount = in.readInt();
        this.adultPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.childPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.extraGst = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<MBooking> CREATOR = new Parcelable.Creator<MBooking>() {
        @Override
        public MBooking createFromParcel(Parcel source) {
            return new MBooking(source);
        }

        @Override
        public MBooking[] newArray(int size) {
            return new MBooking[size];
        }
    };
}
