package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HotelSearchResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     * result : [{"hotel_id":"11","hotel_name":"ginger","hotel_actual_price":"1500","hotel_type":"Hotel","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Noida","hotel_amenties1":"coffee,lock,wifi,battery","Rating":"3","check_in":"13:14","check_out":"13:14","city_name":"Noida","google_map":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d28007.82537807472!2d77.357256247083!3d28.660372231702997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cfaa1c1508c11%3A0xdd43351e11bdc374!2sVasundhara%2C+Ghaziabad%2C+Uttar+Pradesh!5e0!3m","no_of_rest":"2","no_of_floor":"3","country":"India","locality":"Noida, India","state":"Uttar Pradesh","zipcode":"110096","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/1.jpg","pah":"","status":"1","lat":"28.628454","longg":"77.376945","id":"10","tour_policy":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","cancellation":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","services":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","admission":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","date_change_allowed":"Not Allowed","distance":"6.478555384300721"},{"hotel_id":"12","hotel_name":"test noida2","hotel_actual_price":"2100","hotel_type":"Bungalow","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Noida","hotel_amenties1":"battery,newspaper,snowflake","Rating":"1","check_in":"13:14","check_out":"13:14","city_name":"Noida","google_map":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d28007.82537807472!2d77.357256247083!3d28.660372231702997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cfaa1c1508c11%3A0xdd43351e11bdc374!2sVasundhara%2C+Ghaziabad%2C+Uttar+Pradesh!5e0!3m","no_of_rest":"2","no_of_floor":"3","country":"India","locality":"Delhi, India","state":"Delhi","zipcode":"110096","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/2.jpg","pah":"","status":"0","lat":"28.7041","longg":"77.1025","id":"11","tour_policy":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","cancellation":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","services":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","admission":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","date_change_allowed":"Not Allowed","distance":"21.01989648650171"},{"hotel_id":"27","hotel_name":"hotel jaipur","hotel_actual_price":"","hotel_type":"Hotel","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Jaipur","hotel_amenties1":"Non-smoking,Shops (nearby),Swimming pool","Rating":"3","check_in":"12:00","check_out":"12:00","city_name":"Jaipur","google_map":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d227748.99974120766!2d75.65046890997147!3d26.885141676236344!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x396c4adf4c57e281%3A0xce1c63a0cf22e09!2sJaipur%2C+Rajasthan!5e0!3m2!1sen!2sin!4v15344","no_of_rest":"22","no_of_floor":"22","country":"India","locality":"noida","state":"Rajasthan","zipcode":"20130","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/13441ea86f6b11e7b2050a434cef95d023.jpg","pah":"","status":"0","lat":"26.9124","longg":"75.7873","id":"15","tour_policy":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","cancellation":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","services":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","admission":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","date_change_allowed":"Allowed","distance":"148.9755017331909"},{"hotel_id":"26","hotel_name":"hotel roomer","hotel_actual_price":"","hotel_type":"Hotel","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Goa","hotel_amenties1":"battery,newspaper,snowflake","Rating":"3","check_in":"12:00","check_out":"01:00","city_name":"Goa","google_map":"","no_of_rest":"22","no_of_floor":"22","country":"India","locality":"noida","state":"Goa","zipcode":"20130","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/0d4bfdb6632d11e8b3b6022fd3fb960a.jfif","pah":"1","status":"0","lat":"15.2993","longg":"74.1240","id":"13","tour_policy":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","cancellation":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","services":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","admission":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","date_change_allowed":"null","distance":"938.002026090029"}]
     * price : [[],[],[],[]]
     */

    private String response_code;
    private String response_message;
    private ArrayList<ResultBean> result;

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }




    public static class ResultBean {
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
         * status : 1
         * lat : 28.628454
         * longg : 77.376945
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
        @SerializedName("status")
        private String statusX;
        private String lat;
        private String longg;
        private String id;
        private String tour_policy;
        private String cancellation;
        private String services;
        private String admission;
        private String date_change_allowed;
        private String distance;

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

        public String getStatusX() {
            return statusX;
        }

        public void setStatusX(String statusX) {
            this.statusX = statusX;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
