package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;

import java.util.ArrayList;

public class CouponListResponse extends ApiResponse {


    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"18","coupon_name":"HSPH","coupon_percent":"5","max_discount":"50","vendor_id":"78"}]
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
         * id : 18
         * coupon_name : HSPH
         * coupon_percent : 5
         * max_discount : 50
         * vendor_id : 78
         */

        private String id;
        private String coupon_name;
        private String coupon_percent;
        private String max_discount;
        private String vendor_id;
        private boolean isSelected=false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public String getCoupon_percent() {
            return coupon_percent;
        }

        public void setCoupon_percent(String coupon_percent) {
            this.coupon_percent = coupon_percent;
        }

        public String getMax_discount() {
            return max_discount;
        }

        public void setMax_discount(String max_discount) {
            this.max_discount = max_discount;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }
    }
}
