package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jeevan Gupta on 11/17/2018.
 */
public class AddReviewResponse extends ApiResponse {


    @SerializedName("response_code")
    private String responseCode;
    @SerializedName("response_message")
    private String responseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
