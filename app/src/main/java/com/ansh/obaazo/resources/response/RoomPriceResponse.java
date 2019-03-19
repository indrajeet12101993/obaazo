package com.ansh.obaazo.resources.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.ansh.obaazo.web.ApiResponse;

import java.util.ArrayList;

public class RoomPriceResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"6168","date":"10/28/2018","availability":"10","inventory_detail":"10","room_id":"177","hotel_id":"98","city_name":"Greater Noida","adult_price":"4000","two_adult":"4500","extra_adult":"500","extra_child":"","gst_adult":"","gst_twoadult":"","gst_extraadult":"","gst_child":"","is_blocked":"0","vendor_id":"59"},{"id":"6169","date":"10/29/2018","availability":"10","inventory_detail":"10","room_id":"177","hotel_id":"98","city_name":"Greater Noida","adult_price":"4000","two_adult":"4500","extra_adult":"500","extra_child":"","gst_adult":"","gst_twoadult":"","gst_extraadult":"","gst_child":"","is_blocked":"0","vendor_id":"59"}]
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
         * id : 6168
         * date : 10/28/2018
         * availability : 10
         * inventory_detail : 10
         * room_id : 177
         * hotel_id : 98
         * city_name : Greater Noida
         * adult_price : 4000
         * two_adult : 4500
         * extra_adult : 500
         * extra_child :
         * gst_adult :
         * gst_twoadult :
         * gst_extraadult :
         * gst_child :
         * is_blocked : 0
         * vendor_id : 59
         */

        private String id;
        private String date;
        private String availability;
        private String inventory_detail;
        private String room_id;
        private String hotel_id;
        private String city_name;
        private String adult_price;
        private String two_adult;
        private String extra_adult;
        private String extra_child;
        private String gst_adult;
        private String gst_twoadult;
        private String gst_extraadult;
        private String gst_child;
        private String is_blocked;
        private String vendor_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getInventory_detail() {
            return inventory_detail;
        }

        public void setInventory_detail(String inventory_detail) {
            this.inventory_detail = inventory_detail;
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

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getAdult_price() {
            return adult_price;
        }

        public void setAdult_price(String adult_price) {
            this.adult_price = adult_price;
        }

        public String getTwo_adult() {
            return TextUtils.isEmpty(two_adult) ? "0" : two_adult;
        }

        public void setTwo_adult(String two_adult) {
            this.two_adult = two_adult;
        }

        public String getExtra_adult() {
            return extra_adult;
        }

        public void setExtra_adult(String extra_adult) {
            this.extra_adult = extra_adult;
        }

        public String getExtra_child() {
            return TextUtils.isEmpty(extra_child) ? "0" : extra_child;
        }

        public void setExtra_child(String extra_child) {
            this.extra_child = extra_child;
        }

        public String getGst_adult() {
            return TextUtils.isEmpty(gst_adult) ? "0" : gst_adult;

        }

        public void setGst_adult(String gst_adult) {
            this.gst_adult = gst_adult;
        }

        public String getGst_twoadult() {
            return TextUtils.isEmpty(gst_twoadult) ? "0" : gst_twoadult;
        }

        public void setGst_twoadult(String gst_twoadult) {
            this.gst_twoadult = gst_twoadult;
        }

        public String getGst_extraadult() {
            return TextUtils.isEmpty(gst_extraadult) ? "0" : gst_extraadult;
        }

        public void setGst_extraadult(String gst_extraadult) {
            this.gst_extraadult = gst_extraadult;
        }

        public String getGst_child() {
            return TextUtils.isEmpty(gst_child) ? "0" : gst_child;
        }

        public void setGst_child(String gst_child) {
            this.gst_child = gst_child;
        }

        public String getIs_blocked() {
            return is_blocked;
        }

        public void setIs_blocked(String is_blocked) {
            this.is_blocked = is_blocked;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.date);
            dest.writeString(this.availability);
            dest.writeString(this.inventory_detail);
            dest.writeString(this.room_id);
            dest.writeString(this.hotel_id);
            dest.writeString(this.city_name);
            dest.writeString(this.adult_price);
            dest.writeString(this.two_adult);
            dest.writeString(this.extra_adult);
            dest.writeString(this.extra_child);
            dest.writeString(this.gst_adult);
            dest.writeString(this.gst_twoadult);
            dest.writeString(this.gst_extraadult);
            dest.writeString(this.gst_child);
            dest.writeString(this.is_blocked);
            dest.writeString(this.vendor_id);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.id = in.readString();
            this.date = in.readString();
            this.availability = in.readString();
            this.inventory_detail = in.readString();
            this.room_id = in.readString();
            this.hotel_id = in.readString();
            this.city_name = in.readString();
            this.adult_price = in.readString();
            this.two_adult = in.readString();
            this.extra_adult = in.readString();
            this.extra_child = in.readString();
            this.gst_adult = in.readString();
            this.gst_twoadult = in.readString();
            this.gst_extraadult = in.readString();
            this.gst_child = in.readString();
            this.is_blocked = in.readString();
            this.vendor_id = in.readString();
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", date='" + date + '\'' +
                    ", availability='" + availability + '\'' +
                    ", inventory_detail='" + inventory_detail + '\'' +
                    ", room_id='" + room_id + '\'' +
                    ", hotel_id='" + hotel_id + '\'' +
                    ", city_name='" + city_name + '\'' +
                    ", adult_price='" + adult_price + '\'' +
                    ", two_adult='" + two_adult + '\'' +
                    ", extra_adult='" + extra_adult + '\'' +
                    ", extra_child='" + extra_child + '\'' +
                    ", gst_adult='" + gst_adult + '\'' +
                    ", gst_twoadult='" + gst_twoadult + '\'' +
                    ", gst_extraadult='" + gst_extraadult + '\'' +
                    ", gst_child='" + gst_child + '\'' +
                    ", is_blocked='" + is_blocked + '\'' +
                    ", vendor_id='" + vendor_id + '\'' +
                    '}';
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
