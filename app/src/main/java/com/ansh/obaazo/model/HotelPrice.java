package com.ansh.obaazo.model;

import java.util.ArrayList;

public class HotelPrice {

    private ArrayList<PriceBean> price;

    public ArrayList<PriceBean> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<PriceBean> price) {
        this.price = price;
    }

    public static class PriceBean {
        /**
         * adult_price : 1200
         * hotel_id : 99
         */

        private String adult_price;
        private String hotel_id;

        public String getAdult_price() {
            return adult_price;
        }

        public void setAdult_price(String adult_price) {
            this.adult_price = adult_price;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }
    }
}
