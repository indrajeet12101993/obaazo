package com.ansh.obaazo.resources.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.HotelPrice;
import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HotelRoomResponse extends ApiResponse implements Parcelable {

    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"31","vendor_id":"19","room_type":"deluxe","price":"","room_size":"300","name":"Deluxe Room","description":"","room_view":"Palace View","bed_type":"twin","extra_bedtype":"Matress","adults_max":"3","child_max":"3","amenities":"battery,newspaper,snowflake,wifi","hotel_id":"45","active":"1"}]
     */

    private String response_code;
    private String response_message;
    private ArrayList<ResultBean> result;
    private ArrayList<BookingInfo> bookingInfos = new ArrayList<>();
    @SerializedName("roomprice")
    private ArrayList<HotelPrice> roomPrice;

    public ArrayList<HotelPrice> getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(ArrayList<HotelPrice> roomPrice) {
        this.roomPrice = roomPrice;
    }

    public ArrayList<BookingInfo> getBookingInfos() {
        return bookingInfos;
    }

    public void setBookingInfos(ArrayList<BookingInfo> bookingInfos) {
        this.bookingInfos = bookingInfos;
    }

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
         * id : 31
         * vendor_id : 19
         * room_type : deluxe
         * price :
         * room_size : 300
         * name : Deluxe Room
         * description :
         * room_view : Palace View
         * bed_type : twin
         * extra_bedtype : Matress
         * adults_max : 3
         * child_max : 3
         * amenities : battery,newspaper,snowflake,wifi
         * hotel_id : 45
         * active : 1
         */

        private String id;
        private String vendor_id;
        private String room_type;
        private String price;
        private String room_size;
        private String name;
        private String description;
        private String room_view;
        private String bed_type;
        private String extra_bedtype;
        private String adults_max;
        private String child_max;
        private String amenities;
        private String hotel_id;
        private String active;
        @SerializedName("rimg")
        private String image;
        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getImage() {
            return image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRoom_size() {
            return room_size;
        }

        public void setRoom_size(String room_size) {
            this.room_size = room_size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRoom_view() {
            return room_view;
        }

        public void setRoom_view(String room_view) {
            this.room_view = room_view;
        }

        public String getBed_type() {
            return bed_type;
        }

        public void setBed_type(String bed_type) {
            this.bed_type = bed_type;
        }

        public String getExtra_bedtype() {
            return extra_bedtype;
        }

        public void setExtra_bedtype(String extra_bedtype) {
            this.extra_bedtype = extra_bedtype;
        }

        public String getAdults_max() {
            return adults_max;
        }

        public void setAdults_max(String adults_max) {
            this.adults_max = adults_max;
        }

        public String getChild_max() {
            return child_max;
        }

        public void setChild_max(String child_max) {
            this.child_max = child_max;
        }

        public String getAmenities() {
            return amenities;
        }

        public void setAmenities(String amenities) {
            this.amenities = amenities;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public ResultBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.vendor_id);
            dest.writeString(this.room_type);
            dest.writeString(this.price);
            dest.writeString(this.room_size);
            dest.writeString(this.name);
            dest.writeString(this.description);
            dest.writeString(this.room_view);
            dest.writeString(this.bed_type);
            dest.writeString(this.extra_bedtype);
            dest.writeString(this.adults_max);
            dest.writeString(this.child_max);
            dest.writeString(this.amenities);
            dest.writeString(this.hotel_id);
            dest.writeString(this.active);
            dest.writeString(this.image);
        }

        protected ResultBean(Parcel in) {
            this.id = in.readString();
            this.vendor_id = in.readString();
            this.room_type = in.readString();
            this.price = in.readString();
            this.room_size = in.readString();
            this.name = in.readString();
            this.description = in.readString();
            this.room_view = in.readString();
            this.bed_type = in.readString();
            this.extra_bedtype = in.readString();
            this.adults_max = in.readString();
            this.child_max = in.readString();
            this.amenities = in.readString();
            this.hotel_id = in.readString();
            this.active = in.readString();
            this.image = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.response_code);
        dest.writeString(this.response_message);
        dest.writeTypedList(this.result);
        dest.writeTypedList(this.bookingInfos);
        dest.writeList(this.roomPrice);
    }

    public HotelRoomResponse() {
    }

    protected HotelRoomResponse(Parcel in) {
        this.response_code = in.readString();
        this.response_message = in.readString();
        this.result = in.createTypedArrayList(ResultBean.CREATOR);
        this.bookingInfos = in.createTypedArrayList(BookingInfo.CREATOR);
        this.roomPrice = new ArrayList<HotelPrice>();
        in.readList(this.roomPrice, HotelPrice.class.getClassLoader());
    }

    public static final Parcelable.Creator<HotelRoomResponse> CREATOR = new Parcelable.Creator<HotelRoomResponse>() {
        @Override
        public HotelRoomResponse createFromParcel(Parcel source) {
            return new HotelRoomResponse(source);
        }

        @Override
        public HotelRoomResponse[] newArray(int size) {
            return new HotelRoomResponse[size];
        }
    };
}
