package com.ansh.obaazo.resources.response;

import com.ansh.obaazo.web.ApiResponse;

public class PriceResponse extends ApiResponse {

    /**
     * response_code : 200
     * response_message : success
     * Price : 5712
     */

    private String response_code;
    private String response_message;
    private int[] Price;

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

    public int[] getPrice() {
        return Price;
    }

    public void setPrice(int[] Price) {
        this.Price = Price;
    }
}
