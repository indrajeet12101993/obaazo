package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ApiResponse {
    private String response_code;
    @SerializedName("result")
    private UserDetails data;

    public String getResponseCode() {
        return this.response_code;
    }

    public void setResponseCode(String response_code) {
        this.response_code = response_code;
    }

    private String response_message;

    public String getResponseMessage() {
        return this.response_message;
    }

    public void setResponseMessage(String response_message) {
        this.response_message = response_message;
    }

    public UserDetails getData() {
        return this.data;
    }

    public void setData(UserDetails data) {
        this.data = data;
    }
}
