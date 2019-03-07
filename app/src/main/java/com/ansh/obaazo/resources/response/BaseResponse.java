package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.model.BookingDetails;
import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

public class BaseResponse extends ApiResponse {


    /**
     * response_code : 200
     * response_message : success
     */

    private String response_code;
    private String response_message;

    @SerializedName("result")
    private BookingDetails details;

    public BookingDetails getDetails() {
        return details;
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

}
