package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.LoginResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class VerifyOtpService extends ApiService<VerifyOtpService.VerifyOtp, BaseRequest, LoginResponse> {

    public VerifyOtpService(Context context) {
        super(context);
    }

    @Override
    protected Class<VerifyOtp> getAPI() {
        return VerifyOtp.class;
    }

    @Override
    protected Call<LoginResponse> onExecute(VerifyOtp api, BaseRequest request) {
        return api.verifyOtp(request.getId(), request.getId2());
    }

    public interface VerifyOtp {
        @FormUrlEncoded
        @POST("Api/fetchotp")
        Call<LoginResponse> verifyOtp(@Field("mobile") String mobileNo, @Field("otp") String otp);
    }
}
