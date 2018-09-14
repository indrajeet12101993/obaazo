package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;

import java.util.ArrayList;

public class OfferResponse extends ApiResponse {


    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"1","offer_image":"http://technowhizzit.com/obazo/image/online-hotel-booking-offers-online-domestic-hotel-booking-coupons-get-upto-60-offer-or-25-download.png"},{"id":"2","offer_image":"http://technowhizzit.com/obazo/image/holi-1425041253.jpg"},{"id":"3","offer_image":"http://technowhizzit.com/obazo/image/goibibo-free-hotel-booking-for-new-users.jpg"}]
     * backgroundimage : http://technowhizzit.com/obaazo//assets/img/home_1/beach-blue.jpg
     */

    private String response_code;
    private String response_message;
    private String backgroundimage;
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

    public String getBackgroundimage() {
        return backgroundimage;
    }

    public void setBackgroundimage(String backgroundimage) {
        this.backgroundimage = backgroundimage;
    }

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * offer_image : http://technowhizzit.com/obazo/image/online-hotel-booking-offers-online-domestic-hotel-booking-coupons-get-upto-60-offer-or-25-download.png
         */

        private String id;
        private String offer_image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOffer_image() {
            return offer_image;
        }

        public void setOffer_image(String offer_image) {
            this.offer_image = offer_image;
        }
    }
}
