package com.ansh.obaazo.resources.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyBookingResponse extends ApiResponse {


    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"299","booking_id":"OBAAZO913703","user_id":"94","hotel_id":"97","user_name":"gurpreet","checkin":"10/13/2018","checkout":"10/13/2018","booking_amount":"2000","final_amount":"2240","check_status":"0","payment_option":"1","checkout_status":"0","is_settle":"0","guest_time":"2:00 pm to 5:00 pm","gst_value":"240","gst_per":"12","coupon_name":"0","coupon_discount":"0","obaazo_money":"0","created":"2018-10-13","booking_status":"1","cancel_id":"OBZ634441","otp_cancel":"","cancellation_amount":"","cancel_date":"2018/10/30","vendor_amount":"0","obaazo_amount":"0","txn_id":"","user_email":"","user_mobile":"","discount_status":"0","hotel_name":"Balaji Guest House","hotel_type":"5","address":"Greater Noida","hotel_amenties1":"non-smoking,nearby,TV LCD & Cable TV,Extra Mattress (On Request),Power Backup,Front Desk,Television,Free Parking,Room Service (24 Hours),Housekeeping,Restaurant,Doctor on Call,wifi,cctv,coffee,24 hour reception,battery,snowflake,newspaper,Internet access,Welcome Drink on Arrival (Non-Alcoholic)","Rating":"","hotelrating":"2","check_in":"12:00","check_out":"12:00","city_name":"Greater Noida","google_map":"","no_of_rest":"20","no_of_floor":"1","country":"","locality":"Balaji Guest house, Block B, Sector Sigma 1, Greater Noida, Uttar Pradesh, India","state":"Uttar Pradesh","zipcode":"201310","vendor_id":"77","image1":"https://obaazo.com/vendor/images/users/29906774.jpg","pah":"1","isblocked":"0","lat":"28.4527532","longg":"77.5492138","status":"0","hotel_del":"0","review":""},{"id":"383","booking_id":"OBAAZO414642","user_id":"94","hotel_id":"340","user_name":"gurpreet","checkin":"10/30/2018","checkout":"10/30/2018","booking_amount":"4000","final_amount":"4720","check_status":"0","payment_option":"1","checkout_status":"1","is_settle":"0","guest_time":"12:00 pm to 2:00 pm","gst_value":"720","gst_per":"","coupon_name":"0","coupon_discount":"0","obaazo_money":"0","created":"2018-10-31","booking_status":"0","cancel_id":"","otp_cancel":"","cancellation_amount":"","cancel_date":"","vendor_amount":"3776","obaazo_amount":"944","txn_id":"","user_email":"gurpreet.technowhiz@gmail.com","user_mobile":"9876543210","discount_status":"0","hotel_name":"test","hotel_type":"5","address":"Sector 39, Noida","hotel_amenties1":"","Rating":"4","hotelrating":"4","check_in":"12:00","check_out":"12:00","city_name":"Noida","google_map":"","no_of_rest":"2","no_of_floor":"","country":"","locality":"Noida City Centre Metro, Sector 39, Noida, Uttar Pradesh","state":"Punjab","zipcode":"201301","vendor_id":"315","image1":"https://obaazo.com/vendor/images/users/bed-bedroom-curtains-26139.jpg","pah":"","isblocked":"0","lat":"28.57447","longg":"77.35561","status":"0","hotel_del":"0","review":"1"},{"id":"384","booking_id":"OBAAZO124696","user_id":"94","hotel_id":"340","user_name":"gurpreet","checkin":"10/31/2018","checkout":"10/31/2018","booking_amount":"4000","final_amount":"4720","check_status":"2","payment_option":"1","checkout_status":"0","is_settle":"0","guest_time":"12:00 pm to 2:00 pm","gst_value":"720","gst_per":"","coupon_name":"0","coupon_discount":"0","obaazo_money":"0","created":"2018-10-31","booking_status":"0","cancel_id":"","otp_cancel":"","cancellation_amount":"","cancel_date":"","vendor_amount":"3776","obaazo_amount":"944","txn_id":"","user_email":"gurpreet.technowhiz@gmail.com","user_mobile":"3434343434","discount_status":"0","hotel_name":"test","hotel_type":"5","address":"Sector 39, Noida","hotel_amenties1":"","Rating":"4","hotelrating":"4","check_in":"12:00","check_out":"12:00","city_name":"Noida","google_map":"","no_of_rest":"2","no_of_floor":"","country":"","locality":"Noida City Centre Metro, Sector 39, Noida, Uttar Pradesh","state":"Punjab","zipcode":"201301","vendor_id":"315","image1":"https://obaazo.com/vendor/images/users/bed-bedroom-curtains-26139.jpg","pah":"","isblocked":"0","lat":"28.57447","longg":"77.35561","status":"0","hotel_del":"0","review":"1"}]
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

    public static class ResultBean implements Parcelable {
        /**
         * id : 299
         * booking_id : OBAAZO913703
         * user_id : 94
         * hotel_id : 97
         * user_name : gurpreet
         * checkin : 10/13/2018
         * checkout : 10/13/2018
         * booking_amount : 2000
         * final_amount : 2240
         * check_status : 0
         * payment_option : 1
         * checkout_status : 0
         * is_settle : 0
         * guest_time : 2:00 pm to 5:00 pm
         * gst_value : 240
         * gst_per : 12
         * coupon_name : 0
         * coupon_discount : 0
         * obaazo_money : 0
         * created : 2018-10-13
         * booking_status : 1
         * cancel_id : OBZ634441
         * otp_cancel :
         * cancellation_amount :
         * cancel_date : 2018/10/30
         * vendor_amount : 0
         * obaazo_amount : 0
         * txn_id :
         * user_email :
         * user_mobile :
         * discount_status : 0
         * hotel_name : Balaji Guest House
         * hotel_type : 5
         * address : Greater Noida
         * hotel_amenties1 : non-smoking,nearby,TV LCD & Cable TV,Extra Mattress (On Request),Power Backup,Front Desk,Television,Free Parking,Room Service (24 Hours),Housekeeping,Restaurant,Doctor on Call,wifi,cctv,coffee,24 hour reception,battery,snowflake,newspaper,Internet access,Welcome Drink on Arrival (Non-Alcoholic)
         * Rating :
         * hotelrating : 2
         * check_in : 12:00
         * check_out : 12:00
         * city_name : Greater Noida
         * google_map :
         * no_of_rest : 20
         * no_of_floor : 1
         * country :
         * locality : Balaji Guest house, Block B, Sector Sigma 1, Greater Noida, Uttar Pradesh, India
         * state : Uttar Pradesh
         * zipcode : 201310
         * vendor_id : 77
         * image1 : https://obaazo.com/vendor/images/users/29906774.jpg
         * pah : 1
         * isblocked : 0
         * lat : 28.4527532
         * longg : 77.5492138
         * status : 0
         * hotel_del : 0
         * review :
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
        private String checkout_status;
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
        private String txn_id;
        private String user_email;
        private String user_mobile;
        private String discount_status;
        private String hotel_name;
        private String hotel_type;
        private String address;
        private String hotel_amenties1;
        private String Rating;
        private String hotelrating;
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
        @SerializedName("status")
        private String statusX;
        private String hotel_del;
        private String review;

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

        public String getCheckout_status() {
            return checkout_status;
        }

        public void setCheckout_status(String checkout_status) {
            this.checkout_status = checkout_status;
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

        public String getTxn_id() {
            return txn_id;
        }

        public void setTxn_id(String txn_id) {
            this.txn_id = txn_id;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getDiscount_status() {
            return discount_status;
        }

        public void setDiscount_status(String discount_status) {
            this.discount_status = discount_status;
        }

        public String getHotel_name() {
            return hotel_name;
        }

        public void setHotel_name(String hotel_name) {
            this.hotel_name = hotel_name;
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

        public String getHotelrating() {
            return hotelrating;
        }

        public void setHotelrating(String hotelrating) {
            this.hotelrating = hotelrating;
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

        public String getStatusX() {
            return statusX;
        }

        public void setStatusX(String statusX) {
            this.statusX = statusX;
        }

        public String getHotel_del() {
            return hotel_del;
        }

        public void setHotel_del(String hotel_del) {
            this.hotel_del = hotel_del;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.booking_id);
            dest.writeString(this.user_id);
            dest.writeString(this.hotel_id);
            dest.writeString(this.user_name);
            dest.writeString(this.checkin);
            dest.writeString(this.checkout);
            dest.writeString(this.booking_amount);
            dest.writeString(this.final_amount);
            dest.writeString(this.check_status);
            dest.writeString(this.payment_option);
            dest.writeString(this.checkout_status);
            dest.writeString(this.is_settle);
            dest.writeString(this.guest_time);
            dest.writeString(this.gst_value);
            dest.writeString(this.gst_per);
            dest.writeString(this.coupon_name);
            dest.writeString(this.coupon_discount);
            dest.writeString(this.obaazo_money);
            dest.writeString(this.created);
            dest.writeString(this.booking_status);
            dest.writeString(this.cancel_id);
            dest.writeString(this.otp_cancel);
            dest.writeString(this.cancellation_amount);
            dest.writeString(this.cancel_date);
            dest.writeString(this.vendor_amount);
            dest.writeString(this.obaazo_amount);
            dest.writeString(this.txn_id);
            dest.writeString(this.user_email);
            dest.writeString(this.user_mobile);
            dest.writeString(this.discount_status);
            dest.writeString(this.hotel_name);
            dest.writeString(this.hotel_type);
            dest.writeString(this.address);
            dest.writeString(this.hotel_amenties1);
            dest.writeString(this.Rating);
            dest.writeString(this.hotelrating);
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
            dest.writeString(this.statusX);
            dest.writeString(this.hotel_del);
            dest.writeString(this.review);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.id = in.readString();
            this.booking_id = in.readString();
            this.user_id = in.readString();
            this.hotel_id = in.readString();
            this.user_name = in.readString();
            this.checkin = in.readString();
            this.checkout = in.readString();
            this.booking_amount = in.readString();
            this.final_amount = in.readString();
            this.check_status = in.readString();
            this.payment_option = in.readString();
            this.checkout_status = in.readString();
            this.is_settle = in.readString();
            this.guest_time = in.readString();
            this.gst_value = in.readString();
            this.gst_per = in.readString();
            this.coupon_name = in.readString();
            this.coupon_discount = in.readString();
            this.obaazo_money = in.readString();
            this.created = in.readString();
            this.booking_status = in.readString();
            this.cancel_id = in.readString();
            this.otp_cancel = in.readString();
            this.cancellation_amount = in.readString();
            this.cancel_date = in.readString();
            this.vendor_amount = in.readString();
            this.obaazo_amount = in.readString();
            this.txn_id = in.readString();
            this.user_email = in.readString();
            this.user_mobile = in.readString();
            this.discount_status = in.readString();
            this.hotel_name = in.readString();
            this.hotel_type = in.readString();
            this.address = in.readString();
            this.hotel_amenties1 = in.readString();
            this.Rating = in.readString();
            this.hotelrating = in.readString();
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
            this.statusX = in.readString();
            this.hotel_del = in.readString();
            this.review = in.readString();
        }

        public static final Parcelable.Creator<ResultBean> CREATOR = new Parcelable.Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }
}
