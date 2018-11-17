package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;

import java.util.ArrayList;

/**
 * Created by Jeevan Gupta on 11/17/2018.
 */
public class MyReviewResponse extends ApiResponse {
    private ArrayList<Result> result;

    private String response_message;

    private String response_code;

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }


    public class Result {
        private String hotel_del;

        private String city_name;

        private String hotel_id;

        private String google_map;

        private String state;

        private String longg;

        private String hotel_name;

        private String id;

        private String no_of_rest;

        private String created;

        private String hotelrating;

        private String user_id;

        private String lat;

        private String hotel_amenties1;

        private String pah;

        private String status;

        private String zipcode;

        private String no_of_floor;

        private String check_in;

        private String check_out;

        private String vendor_id;

        private String hotel_type;

        private String country;

        private String user_name;

        private String image1;

        private String address;

        private String Rating;

        private String isblocked;

        private String locality;

        private String rating;

        private String review;

        public String getHotel_del() {
            return hotel_del;
        }

        public void setHotel_del(String hotel_del) {
            this.hotel_del = hotel_del;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getGoogle_map() {
            return google_map;
        }

        public void setGoogle_map(String google_map) {
            this.google_map = google_map;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLongg() {
            return longg;
        }

        public void setLongg(String longg) {
            this.longg = longg;
        }

        public String getHotel_name() {
            return hotel_name;
        }

        public void setHotel_name(String hotel_name) {
            this.hotel_name = hotel_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo_of_rest() {
            return no_of_rest;
        }

        public void setNo_of_rest(String no_of_rest) {
            this.no_of_rest = no_of_rest;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getHotelrating() {
            return hotelrating;
        }

        public void setHotelrating(String hotelrating) {
            this.hotelrating = hotelrating;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getHotel_amenties1() {
            return hotel_amenties1;
        }

        public void setHotel_amenties1(String hotel_amenties1) {
            this.hotel_amenties1 = hotel_amenties1;
        }

        public String getPah() {
            return pah;
        }

        public void setPah(String pah) {
            this.pah = pah;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getNo_of_floor() {
            return no_of_floor;
        }

        public void setNo_of_floor(String no_of_floor) {
            this.no_of_floor = no_of_floor;
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

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getHotel_type() {
            return hotel_type;
        }

        public void setHotel_type(String hotel_type) {
            this.hotel_type = hotel_type;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }


        public String getIsblocked() {
            return isblocked;
        }

        public void setIsblocked(String isblocked) {
            this.isblocked = isblocked;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }
    }

}
