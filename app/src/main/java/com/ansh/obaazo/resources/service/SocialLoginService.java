package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.LoginResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class SocialLoginService extends ApiService<SocialLoginService.SocialLoginApi, BaseRequest, LoginResponse> {

    public SocialLoginService(Context context) {
        super(context);
    }

    @Override
    protected Class<SocialLoginApi> getAPI() {
        return SocialLoginApi.class;
    }

    @Override
    protected Call<LoginResponse> onExecute(SocialLoginApi api, BaseRequest request) {
        return api.socialLogin(request.getId(), request.getId2());
    }

    public interface SocialLoginApi {
        @FormUrlEncoded
        @POST("/Api/login")
        Call<LoginResponse> socialLogin(@Field("type") String type, @Field("email") String email);
    }
}
