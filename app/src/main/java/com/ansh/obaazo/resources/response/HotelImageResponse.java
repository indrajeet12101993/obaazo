package com.ansh.obaazo.resources.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.ansh.obaazo.web.ApiResponse;

import java.util.ArrayList;

public class HotelImageResponse extends ApiResponse implements Parcelable {


    /**
     * response_code : 200
     * response_message : success
     * result : [{"id":"118","image_n":"http://obaazo.com/vendor/images/users/145845726.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845726.jpg","room_id":"7"},{"id":"119","image_n":"http://obaazo.com/vendor/images/users/145845730.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845730.jpg","room_id":"7"},{"id":"120","image_n":"http://obaazo.com/vendor/images/users/145845737.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845737.jpg","room_id":"7"},{"id":"121","image_n":"http://obaazo.com/vendor/images/users/145845740.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845740.jpg","room_id":"7"},{"id":"122","image_n":"http://obaazo.com/vendor/images/users/145845756.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845756.jpg","room_id":"7"},{"id":"123","image_n":"http://obaazo.com/vendor/images/users/145845765.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845765.jpg","room_id":"7"},{"id":"124","image_n":"http://obaazo.com/vendor/images/users/145845772.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845772.jpg","room_id":"7"},{"id":"125","image_n":"http://obaazo.com/vendor/images/users/145845774.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845774.jpg","room_id":"7"},{"id":"126","image_n":"http://obaazo.com/vendor/images/users/145845791.jpg","vendor_id":"1","hotel_id":"28","image_name":"145845791.jpg","room_id":"7"}]
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
         * id : 118
         * image_n : http://obaazo.com/vendor/images/users/145845726.jpg
         * vendor_id : 1
         * hotel_id : 28
         * image_name : 145845726.jpg
         * room_id : 7
         */

        private String id;
        private String image_n;
        private String vendor_id;
        private String hotel_id;
        private String image_name;
        private String room_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage_n() {
            return image_n;
        }

        public void setImage_n(String image_n) {
            this.image_n = image_n;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.image_n);
            dest.writeString(this.vendor_id);
            dest.writeString(this.hotel_id);
            dest.writeString(this.image_name);
            dest.writeString(this.room_id);
        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.id = in.readString();
            this.image_n = in.readString();
            this.vendor_id = in.readString();
            this.hotel_id = in.readString();
            this.image_name = in.readString();
            this.room_id = in.readString();
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
        dest.writeList(this.result);
    }

    public HotelImageResponse() {
    }

    protected HotelImageResponse(Parcel in) {
        this.response_code = in.readString();
        this.response_message = in.readString();
        this.result = new ArrayList<ResultBean>();
        in.readList(this.result, ResultBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HotelImageResponse> CREATOR = new Parcelable.Creator<HotelImageResponse>() {
        @Override
        public HotelImageResponse createFromParcel(Parcel source) {
            return new HotelImageResponse(source);
        }

        @Override
        public HotelImageResponse[] newArray(int size) {
            return new HotelImageResponse[size];
        }
    };
}
