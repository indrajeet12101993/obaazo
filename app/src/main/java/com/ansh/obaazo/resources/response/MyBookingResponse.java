package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MyBookingResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"226","booking_id":"OBAAZO330539","user_id":"58","hotel_id":"47","user_name":"sanchit","checkin":"09/20/2018","checkout":"09/20/2018","booking_amount":"200","final_amount":"200","check_status":"1","payment_option":"1","is_settle":"0","guest_time":"12:00 pm to 2:00 pm","gst_value":"0","gst_per":"0","coupon_name":"0","coupon_discount":"0","obaazo_money":"0","created":"2018-09-20","booking_status":"0","cancel_id":"","otp_cancel":"","cancellation_amount":"","cancel_date":"","vendor_amount":"120","obaazo_amount":"80"},{"id":"248","booking_id":"OBAAZO111653","user_id":"58","hotel_id":"57","user_name":"sanchit","checkin":"09/23/2018","checkout":"09/23/2018","booking_amount":"2000","final_amount":"2240","check_status":"1","payment_option":"1","is_settle":"0","guest_time":"12:00 pm to 2:00 pm","gst_value":"240","gst_per":"12","coupon_name":"0","coupon_discount":"0","obaazo_money":"0","created":"2018-09-22","booking_status":"0","cancel_id":"","otp_cancel":"","cancellation_amount":"","cancel_date":"","vendor_amount":"1200","obaazo_amount":"800"}]
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
         * id : 226
         * booking_id : OBAAZO330539
         * user_id : 58
         * hotel_id : 47
         * user_name : sanchit
         * checkin : 09/20/2018
         * checkout : 09/20/2018
         * booking_amount : 200
         * final_amount : 200
         * check_status : 1
         * payment_option : 1
         * is_settle : 0
         * guest_time : 12:00 pm to 2:00 pm
         * gst_value : 0
         * gst_per : 0
         * coupon_name : 0
         * coupon_discount : 0
         * obaazo_money : 0
         * created : 2018-09-20
         * booking_status : 0
         * cancel_id :
         * otp_cancel :
         * cancellation_amount :
         * cancel_date :
         * vendor_amount : 120
         * obaazo_amount : 80
         */

        private String id;
        private String booking_id;
        private String user_id;
        private String hotel_id;
        private String user_name;
        private String checkin;
        private String checkout;
        private String booking_amount;
        private String final_amount;
        private String check_status;
        private String payment_option;
        private String is_settle;
        private String guest_time;
        private String gst_value;
        private String gst_per;
        private String coupon_name;
        private String coupon_discount;
        private String obaazo_money;
        private String created;
        private String booking_status;
        private String cancel_id;
        private String otp_cancel;
        private String cancellation_amount;
        private String cancel_date;
        private String vendor_amount;
        private String obaazo_amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getCheckin() {
            return checkin;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        public String getCheckout() {
            return checkout;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }

        public String getBooking_amount() {
            return booking_amount;
        }

        public void setBooking_amount(String booking_amount) {
            this.booking_amount = booking_amount;
        }

        public String getFinal_amount() {
            return final_amount;
        }

        public void setFinal_amount(String final_amount) {
            this.final_amount = final_amount;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getPayment_option() {
            return payment_option;
        }

        public void setPayment_option(String payment_option) {
            this.payment_option = payment_option;
        }

        public String getIs_settle() {
            return is_settle;
        }

        public void setIs_settle(String is_settle) {
            this.is_settle = is_settle;
        }

        public String getGuest_time() {
            return guest_time;
        }

        public void setGuest_time(String guest_time) {
            this.guest_time = guest_time;
        }

        public String getGst_value() {
            return gst_value;
        }

        public void setGst_value(String gst_value) {
            this.gst_value = gst_value;
        }

        public String getGst_per() {
            return gst_per;
        }

        public void setGst_per(String gst_per) {
            this.gst_per = gst_per;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public String getCoupon_discount() {
            return coupon_discount;
        }

        public void setCoupon_discount(String coupon_discount) {
            this.coupon_discount = coupon_discount;
        }

        public String getObaazo_money() {
            return obaazo_money;
        }

        public void setObaazo_money(String obaazo_money) {
            this.obaazo_money = obaazo_money;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getBooking_status() {
            return booking_status;
        }

        public void setBooking_status(String booking_status) {
            this.booking_status = booking_status;
        }

        public String getCancel_id() {
            return cancel_id;
        }

        public void setCancel_id(String cancel_id) {
            this.cancel_id = cancel_id;
        }

        public String getOtp_cancel() {
            return otp_cancel;
        }

        public void setOtp_cancel(String otp_cancel) {
            this.otp_cancel = otp_cancel;
        }

        public String getCancellation_amount() {
            return cancellation_amount;
        }

        public void setCancellation_amount(String cancellation_amount) {
            this.cancellation_amount = cancellation_amount;
        }

        public String getCancel_date() {
            return cancel_date;
        }

        public void setCancel_date(String cancel_date) {
            this.cancel_date = cancel_date;
        }

        public String getVendor_amount() {
            return vendor_amount;
        }

        public void setVendor_amount(String vendor_amount) {
            this.vendor_amount = vendor_amount;
        }

        public String getObaazo_amount() {
            return obaazo_amount;
        }

        public void setObaazo_amount(String obaazo_amount) {
            this.obaazo_amount = obaazo_amount;
        }
    }
}
