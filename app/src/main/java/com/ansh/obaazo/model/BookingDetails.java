package com.ansh.obaazo.model;

import com.google.gson.annotations.SerializedName;

public class BookingDetails {

    /**
     * id : 115
     * booking_id : OBAAZO754184
     * booking_rand_id : OBAAZO754184
     * adult : 1
     * child : 0
     * no_night : 5
     * room_id : 182
     * hotel_id : 100
     * room_no : 1
     * adult_price : 8500
     * child_price : 0
     * netprice : 8500
     * no_days : 5
     * checkin : 10/13/2018
     * checkout : 10/17/2018
     * user_id : 73
     * user_name : Ganesh
     * booking_amount : 8500
     * final_amount : 10880
     * check_status : 1
     * payment_option : 1
     * checkout_status : 0
     * is_settle : 0
     * guest_time : 0
     * gst_value : 2380
     * gst_per : 28
     * coupon_name : 0
     * coupon_discount : 0
     * obaazo_money : 0
     * created : 2018-10-13
     * booking_status : 0
     * cancel_id :
     * otp_cancel :
     * cancellation_amount :
     * cancel_date :
     * vendor_amount : 5100
     * obaazo_amount : 3400
     * txn_id :
     * user_email :
     * user_mobile :
     * discount_status : 0
     * hotel_name : Lotus House
     * hotel_type : 7
     * address : B8/182 Sawarn Nagri Greater Noida
     * hotel_amenties1 : nearby,Laundry Service (At a charge),Front Desk,Intercom,Dining area,Television,Free Parking,battery
     * Rating :
     * hotelrating : 2
     * check_in : 12:14
     * check_out : 12:01
     * city_name : Greater Noida
     * google_map :
     * no_of_rest : 5
     * no_of_floor : 3
     * country :
     * locality : Swarn Nagri Fire Station
     * state : Uttar Pradesh
     * zipcode : 201310
     * vendor_id : 79
     * image1 : https://obaazo.com/vendor/images/users/124114513 (1).jpg
     * pah :
     * isblocked : 0
     * lat : 28.4634257
     * longg : 77.5299278
     * status : 0
     * hotel_del : 0
     * review :
     * roomname : Standard Double Room
     * tour_policy : Lotus Guest house offers accommodation in Greater Noida. Guests can make use of a garden.
     * <p>
     * At the guest house, rooms are equipped with a wardrobe, a flat-screen TV and a private bathroom. All rooms will provide guests with a fridge.
     * <p>
     * Lotus Guest house offers a children's playground.
     * <p>
     * New Delhi is 42 km from the accommodation, while Gurgaon is 63 km from the property. The nearest airport is Delhi International Airport, 55 km from Lotus Guest house.
     * cancellation : •Non-Refundable on cancellation or No Show
     * •   Obaazo Cash used in the booking will be non-refundable
     * •   Any Add On charges are non-refundable
     * •   You cannot change the check-in or check-out date
     * services : •The standard check-in time is 12:00 PM and the standard check-out time is 11:00 AM. Early check-in or late check-out is strictly subjected to availability and may be chargeable by the hotel. Any early check-in or late check-out request must be directed and reconfirmed with hotel directly
     * •   1. All guests requested to carry Valid ID proof for Check Inn.
     * •   2.Unmarried couples not allowed
     * •   3. Cancellation and prepayment policies vary according to room type.
     * •   Hotel Policy:
     * •   Most hotels do not allow unmarried/unrelated couples to check-in. This is at the full discretion of the hotel                                                   management. No refund would be applicable in case the hotel denies check-in under such circumstances.
     * admission :
     * date_change_allowed :
     * couple : 1
     */

    private String id;
    private String booking_id;
    private String booking_rand_id;
    private String adult;
    private String child;
    private String no_night;
    private String room_id;
    private String hotel_id;
    private String room_no;
    private String adult_price;
    private String child_price;
    private String netprice;
    private String no_days;
    private String checkin;
    private String checkout;
    private String user_id;
    private String user_name;
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
    private String roomname;
    private String tour_policy;
    private String cancellation;
    private String services;
    private String admission;
    private String date_change_allowed;
    private String couple;

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

    public String getBooking_rand_id() {
        return booking_rand_id;
    }

    public void setBooking_rand_id(String booking_rand_id) {
        this.booking_rand_id = booking_rand_id;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getNo_night() {
        return no_night;
    }

    public void setNo_night(String no_night) {
        this.no_night = no_night;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(String adult_price) {
        this.adult_price = adult_price;
    }

    public String getChild_price() {
        return child_price;
    }

    public void setChild_price(String child_price) {
        this.child_price = child_price;
    }

    public String getNetprice() {
        return netprice;
    }

    public void setNetprice(String netprice) {
        this.netprice = netprice;
    }

    public String getNo_days() {
        return no_days;
    }

    public void setNo_days(String no_days) {
        this.no_days = no_days;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
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

    public String getCouple() {
        return couple;
    }

    public void setCouple(String couple) {
        this.couple = couple;
    }
}
