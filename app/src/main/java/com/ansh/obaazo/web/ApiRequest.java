package com.ansh.obaazo.web;


/**
 * Created by SurvivoR on 6/5/2017.
 * {@link ApiRequest}
 */

public abstract class ApiRequest {

    public boolean isValid() {
        return true;
    }

    public String getBaseUrl() {
        return AppConstant.SERVER_URL;
    }
}