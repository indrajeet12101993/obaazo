package com.ansh.obaazo.resources.request;

import com.ansh.obaazo.web.ApiRequest;

public class BaseRequest extends ApiRequest {


    private String id;
    private String id2;

    public BaseRequest() {
    }


    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
