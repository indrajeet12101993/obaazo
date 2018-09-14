package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.web.ApiRequest;

public class BaseRequest extends ApiRequest {


    private String id;

    public BaseRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
