package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.model.HotelPrice;
import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HotelSearchResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     * result : [{"hotel_id":"11","hotel_name":"ginger","hotel_actual_price":"1500","hotel_type":"Hotel","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Noida","hotel_amenties1":"coffee,lock,wifi,battery","Rating":"3","check_in":"13:14","check_out":"13:14","city_name":"Noida","google_map":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d28007.82537807472!2d77.357256247083!3d28.660372231702997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cfaa1c1508c11%3A0xdd43351e11bdc374!2sVasundhara%2C+Ghaziabad%2C+Uttar+Pradesh!5e0!3m","no_of_rest":"2","no_of_floor":"3","country":"India","locality":"Noida, India","state":"Uttar Pradesh","zipcode":"110096","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/1.jpg","pah":"","status":"1","lat":"28.628454","longg":"77.376945","id":"10","tour_policy":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","cancellation":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","services":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","admission":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","date_change_allowed":"Not Allowed","distance":"6.478555384300721"},{"hotel_id":"12","hotel_name":"test noida2","hotel_actual_price":"2100","hotel_type":"Bungalow","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Noida","hotel_amenties1":"battery,newspaper,snowflake","Rating":"1","check_in":"13:14","check_out":"13:14","city_name":"Noida","google_map":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d28007.82537807472!2d77.357256247083!3d28.660372231702997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cfaa1c1508c11%3A0xdd43351e11bdc374!2sVasundhara%2C+Ghaziabad%2C+Uttar+Pradesh!5e0!3m","no_of_rest":"2","no_of_floor":"3","country":"India","locality":"Delhi, India","state":"Delhi","zipcode":"110096","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/2.jpg","pah":"","status":"0","lat":"28.7041","longg":"77.1025","id":"11","tour_policy":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","cancellation":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","services":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","admission":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","date_change_allowed":"Not Allowed","distance":"21.01989648650171"},{"hotel_id":"27","hotel_name":"hotel jaipur","hotel_actual_price":"","hotel_type":"Hotel","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Jaipur","hotel_amenties1":"Non-smoking,Shops (nearby),Swimming pool","Rating":"3","check_in":"12:00","check_out":"12:00","city_name":"Jaipur","google_map":"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d227748.99974120766!2d75.65046890997147!3d26.885141676236344!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x396c4adf4c57e281%3A0xce1c63a0cf22e09!2sJaipur%2C+Rajasthan!5e0!3m2!1sen!2sin!4v15344","no_of_rest":"22","no_of_floor":"22","country":"India","locality":"noida","state":"Rajasthan","zipcode":"20130","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/13441ea86f6b11e7b2050a434cef95d023.jpg","pah":"","status":"0","lat":"26.9124","longg":"75.7873","id":"15","tour_policy":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","cancellation":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","services":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","admission":"According to government regulations, a valid Photo ID has to be carried by every person above the age of 18 staying at the hotel. The identification proofs accepted are Drivers License, Voters Card, Passport, Ration Card. Without valid ID the guest will not be allowed to check in.","date_change_allowed":"Allowed","distance":"148.9755017331909"},{"hotel_id":"26","hotel_name":"hotel roomer","hotel_actual_price":"","hotel_type":"Hotel","address":"Behind Satkar Hotel, Kharadi Bypass, Chandannagar, Goa","hotel_amenties1":"battery,newspaper,snowflake","Rating":"3","check_in":"12:00","check_out":"01:00","city_name":"Goa","google_map":"","no_of_rest":"22","no_of_floor":"22","country":"India","locality":"noida","state":"Goa","zipcode":"20130","vendor_id":"1","image1":"http://technowhizzit.com/obaazo/vendor/images/users/0d4bfdb6632d11e8b3b6022fd3fb960a.jfif","pah":"1","status":"0","lat":"15.2993","longg":"74.1240","id":"13","tour_policy":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna ","cancellation":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","services":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","admission":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","date_change_allowed":"null","distance":"938.002026090029"}]
     * price : [[],[],[],[]]
     */

    private String response_code;
    private String response_message;
    @SerializedName("result")
    private ArrayList<HotelInfo> result;
    @SerializedName("price")
    private ArrayList<HotelPrice> hotelPrices;


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

    public ArrayList<HotelInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<HotelInfo> result) {
        this.result = result;
    }


    public ArrayList<HotelPrice> getHotelPrices() {
        return hotelPrices;
    }

    public void setHotelPrices(ArrayList<HotelPrice> hotelPrices) {
        this.hotelPrices = hotelPrices;
    }
}
