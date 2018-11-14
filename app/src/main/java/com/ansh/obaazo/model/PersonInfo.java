package com.ansh.obaazo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PersonInfo implements Parcelable {
    private Boolean isEdit = false;
    private int noOfAdult = 1;
    private ArrayList<Integer> child = new ArrayList<>();
    public PersonInfo() {
    }

    public int getNoOfAdult() {
        return noOfAdult;
    }

    public void setNoOfAdult(int noOfAdult) {
        this.noOfAdult = noOfAdult;
    }

    public ArrayList<Integer> getChild() {
        return child;
    }

    public void setChild(ArrayList<Integer> child) {
        this.child = child;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.isEdit);
        dest.writeInt(this.noOfAdult);
        dest.writeList(this.child);
    }

    protected PersonInfo(Parcel in) {
        this.isEdit = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.noOfAdult = in.readInt();
        this.child = new ArrayList<Integer>();
        in.readList(this.child, Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<PersonInfo> CREATOR = new Parcelable.Creator<PersonInfo>() {
        @Override
        public PersonInfo createFromParcel(Parcel source) {
            return new PersonInfo(source);
        }

        @Override
        public PersonInfo[] newArray(int size) {
            return new PersonInfo[size];
        }
    };
}
