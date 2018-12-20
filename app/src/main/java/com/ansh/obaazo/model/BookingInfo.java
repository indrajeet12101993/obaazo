package com.ansh.obaazo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class BookingInfo implements Parcelable {
    private ArrayList<PersonInfo> personInfos;
    private int[] price;


    public int[] getPrice() {
        return price;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public static Creator<BookingInfo> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<PersonInfo> getPersonInfos() {
        return personInfos;
    }

    public void setPersonInfos(ArrayList<PersonInfo> personInfos) {
        this.personInfos = personInfos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.personInfos);
    }

    public BookingInfo() {
    }

    protected BookingInfo(Parcel in) {
        this.personInfos = new ArrayList<PersonInfo>();
        in.readList(this.personInfos, PersonInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<BookingInfo> CREATOR = new Parcelable.Creator<BookingInfo>() {
        @Override
        public BookingInfo createFromParcel(Parcel source) {
            return new BookingInfo(source);
        }

        @Override
        public BookingInfo[] newArray(int size) {
            return new BookingInfo[size];
        }
    };
}
