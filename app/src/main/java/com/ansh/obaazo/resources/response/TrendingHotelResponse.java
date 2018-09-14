package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrendingHotelResponse extends ApiResponse {


    private String response_code;
    private String response_message;
    @SerializedName("result")
    private ArrayList<HotelInfo> result;

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


}
