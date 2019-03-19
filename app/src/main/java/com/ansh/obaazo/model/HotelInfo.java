package com.ansh.obaazo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class HotelInfo implements Parcelable/*, Comparable<HotelInfo>*/ {

    /**
     * hotel_id : 11
     * hotel_name : ginger
     * hotel_actual_price : 1500
     * hotel_type : Hotel
     * address : Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Noida
     * hotel_amenties1 : coffee,lock,wifi,battery
     * Rating : 3
     * check_in : 13:14
     * check_out : 13:14
     * city_name : Noida
     * google_map : https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d28007.82537807472!2d77.357256247083!3d28.660372231702997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cfaa1c1508c11%3A0xdd43351e11bdc374!2sVasundhara%2C+Ghaziabad%2C+Uttar+Pradesh!5e0!3m
     * no_of_rest : 2
     * no_of_floor : 3
     * country : India
     * locality : Noida, India
     * state : Uttar Pradesh
     * zipcode : 110096
     * vendor_id : 1
     * image1 : http://technowhizzit.com/obaazo/vendor/images/users/1.jpg
     * pah :
     * isblocked : 0
     * lat : 28.628454
     * longg : 77.376945
     * status : 1
     * id : 10
     * tour_policy : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua
     * cancellation : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
     * services : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
     * admission : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
     * date_change_allowed : Not Allowed
     * distance : 6.478555384300721
     */

    private String hotel_id;
    private String hotel_name;
    private String hotel_actual_price;
    private String hotel_type;
    private String address;
    private String hotel_amenties1;
    private String Rating;
    private String check_in;
    private String check_out;
    private String city_name;
    private String google_map;
    private String no_of_rest;
    private String no_of_floor;
    private String country;
    private String locality;
    private String state;
    private String zipcode;
    private String vendor_id;
    private String image1;
    private String pah;
    private String isblocked;
    private String lat;
    private String longg;
    private String status;
    private String id;
    private String tour_policy;
    private String cancellation;
    private String review;
    private boolean isAvailable = true;
    @SerializedName("Price")
    private String startFrom;
    @SerializedName("rating5")
    private int ratingStart5;
    @SerializedName("rating4")
    private int ratingStart4;
    @SerializedName("rating3")
    private int ratingStart3;
    @SerializedName("rating2")
    private int ratingStart2;
    private String flag;

    public int getRatingStart5() {
        return ratingStart5;
    }

    public void setRatingStart5(int ratingStart5) {
        this.ratingStart5 = ratingStart5;
    }

    public int getRatingStart4() {
        return ratingStart4;
    }

    public void setRatingStart4(int ratingStart4) {
        this.ratingStart4 = ratingStart4;
    }

    public int getRatingStart3() {
        return ratingStart3;
    }

    public void setRatingStart3(int ratingStart3) {
        this.ratingStart3 = ratingStart3;
    }

    public int getRatingStart2() {
        return ratingStart2;
    }

    public void setRatingStart2(int ratingStart2) {
        this.ratingStart2 = ratingStart2;
    }

    public int getRatingStart1() {
        return ratingStart1;
    }

    public void setRatingStart1(int ratingStart1) {
        this.ratingStart1 = ratingStart1;
    }

    @SerializedName("rating1")
    private int ratingStart1;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public static Creator<HotelInfo> getCREATOR() {
        return CREATOR;
    }

    public String getReview() {
        if (TextUtils.isEmpty(review)) {
            return "0";
        }
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setHotelrating(int hotelrating) {
        this.hotelrating = hotelrating;
    }

    private String services;
    private String admission;
    private String date_change_allowed;
    private float distance;
    private String couple;
    private int hotelrating;

    public int getHotelrating() {
        return hotelrating;
    }

    public String getCouple() {
        return couple;
    }

    public void setCouple(String couple) {
        this.couple = couple;
    }


    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_actual_price() {
        return hotel_actual_price;
    }

    public void setHotel_actual_price(String hotel_actual_price) {
        this.hotel_actual_price = hotel_actual_price;
    }

    public String getHotel_type() {
        return hotel_type;
    }

    public void setHotel_type(String hotel_type) {
        this.hotel_type = hotel_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotel_amenties1() {
        return hotel_amenties1;
    }

    public void setHotel_amenties1(String hotel_amenties1) {
        this.hotel_amenties1 = hotel_amenties1;
    }

    public String getRating() {
        if (TextUtils.isEmpty(Rating)) {
            return "0";
        } else if (Rating.length() > 4) {
            return Rating.substring(0, 3);
        }
        return Rating;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getGoogle_map() {
        return google_map;
    }

    public void setGoogle_map(String google_map) {
        this.google_map = google_map;
    }

    public String getNo_of_rest() {
        return no_of_rest;
    }

    public void setNo_of_rest(String no_of_rest) {
        this.no_of_rest = no_of_rest;
    }

    public String getNo_of_floor() {
        return no_of_floor;
    }

    public void setNo_of_floor(String no_of_floor) {
        this.no_of_floor = no_of_floor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getPah() {
        return pah;
    }

    public void setPah(String pah) {
        this.pah = pah;
    }

    public String getIsblocked() {
        return isblocked;
    }

    public void setIsblocked(String isblocked) {
        this.isblocked = isblocked;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongg() {
        return longg;
    }

    public void setLongg(String longg) {
        this.longg = longg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTour_policy() {
        return tour_policy;
    }

    public void setTour_policy(String tour_policy) {
        this.tour_policy = tour_policy;
    }

    public String getCancellation() {
        return cancellation;
    }

    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getDate_change_allowed() {
        return date_change_allowed;
    }

    public void setDate_change_allowed(String date_change_allowed) {
        this.date_change_allowed = date_change_allowed;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public HotelInfo() {
    }

    public String getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hotel_id);
        dest.writeString(this.hotel_name);
        dest.writeString(this.hotel_actual_price);
        dest.writeString(this.hotel_type);
        dest.writeString(this.address);
        dest.writeString(this.hotel_amenties1);
        dest.writeString(this.Rating);
        dest.writeString(this.check_in);
        dest.writeString(this.check_out);
        dest.writeString(this.city_name);
        dest.writeString(this.google_map);
        dest.writeString(this.no_of_rest);
        dest.writeString(this.no_of_floor);
        dest.writeString(this.country);
        dest.writeString(this.locality);
        dest.writeString(this.state);
        dest.writeString(this.zipcode);
        dest.writeString(this.vendor_id);
        dest.writeString(this.image1);
        dest.writeString(this.pah);
        dest.writeString(this.isblocked);
        dest.writeString(this.lat);
        dest.writeString(this.longg);
        dest.writeString(this.status);
        dest.writeString(this.id);
        dest.writeString(this.tour_policy);
        dest.writeString(this.cancellation);
        dest.writeString(this.review);
        dest.writeByte(this.isAvailable ? (byte) 1 : (byte) 0);
        dest.writeString(this.startFrom);
        dest.writeInt(this.ratingStart5);
        dest.writeInt(this.ratingStart4);
        dest.writeInt(this.ratingStart3);
        dest.writeInt(this.ratingStart2);
        dest.writeString(this.flag);
        dest.writeInt(this.ratingStart1);
        dest.writeString(this.services);
        dest.writeString(this.admission);
        dest.writeString(this.date_change_allowed);
        dest.writeFloat(this.distance);
        dest.writeString(this.couple);
        dest.writeInt(this.hotelrating);
    }

    protected HotelInfo(Parcel in) {
        this.hotel_id = in.readString();
        this.hotel_name = in.readString();
        this.hotel_actual_price = in.readString();
        this.hotel_type = in.readString();
        this.address = in.readString();
        this.hotel_amenties1 = in.readString();
        this.Rating = in.readString();
        this.check_in = in.readString();
        this.check_out = in.readString();
        this.city_name = in.readString();
        this.google_map = in.readString();
        this.no_of_rest = in.readString();
        this.no_of_floor = in.readString();
        this.country = in.readString();
        this.locality = in.readString();
        this.state = in.readString();
        this.zipcode = in.readString();
        this.vendor_id = in.readString();
        this.image1 = in.readString();
        this.pah = in.readString();
        this.isblocked = in.readString();
        this.lat = in.readString();
        this.longg = in.readString();
        this.status = in.readString();
        this.id = in.readString();
        this.tour_policy = in.readString();
        this.cancellation = in.readString();
        this.review = in.readString();
        this.isAvailable = in.readByte() != 0;
        this.startFrom = in.readString();
        this.ratingStart5 = in.readInt();
        this.ratingStart4 = in.readInt();
        this.ratingStart3 = in.readInt();
        this.ratingStart2 = in.readInt();
        this.flag = in.readString();
        this.ratingStart1 = in.readInt();
        this.services = in.readString();
        this.admission = in.readString();
        this.date_change_allowed = in.readString();
        this.distance = in.readFloat();
        this.couple = in.readString();
        this.hotelrating = in.readInt();
    }

    public static final Creator<HotelInfo> CREATOR = new Creator<HotelInfo>() {
        @Override
        public HotelInfo createFromParcel(Parcel source) {
            return new HotelInfo(source);
        }

        @Override
        public HotelInfo[] newArray(int size) {
            return new HotelInfo[size];
        }
    };

/*
    @Override
    public int compareTo(HotelInfo hotelInfo) {
       *//* if (hotelInfo.getStartFrom() != null && startFrom!=null) {
            return hotelInfo.getStartFrom().compareTo(startFrom);
        }
        return 0;*//*
        if (hotelInfo.getStartFrom() != null && startFrom != null) {
            int result = this.startFrom.compareToIgnoreCase(hotelInfo.startFrom);
            if (result != 0) {
                return result;
            } else {
                return Double.valueOf(this.startFrom).compareTo(Double.valueOf(hotelInfo.startFrom));
            }
        } else {
            return 0;
        }
    }*/
}
