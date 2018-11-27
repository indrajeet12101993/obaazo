package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.SendOtpResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class SendOtpService extends ApiService<SendOtpService.SendOtpApi, BaseRequest, SendOtpResponse> {

    public SendOtpService(Context context) {
        super(context);
    }

    @Override
    protected Class<SendOtpApi> getAPI() {
        return SendOtpApi.class;
    }

    @Override
    protected Call<SendOtpResponse> onExecute(SendOtpApi api, BaseRequest request) {
        return api.sendOtp(request.getId(),request.getId2());
    }

    public interface SendOtpApi {
        @FormUrlEncoded
        @POST("Api/login")
        Call<SendOtpResponse> sendOtp(@Field("mobile") String mobileNo,@Field("type")String type);
    }
}
