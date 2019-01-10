package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class MyBookingService extends ApiService<MyBookingService.MyBookingApi, BaseRequest, MyBookingResponse> {

    public MyBookingService(Context context) {
        super(context);
    }

    @Override
    protected Class<MyBookingApi> getAPI() {
        return MyBookingApi.class;
    }

    @Override
    protected Call<MyBookingResponse> onExecute(MyBookingApi api, BaseRequest request) {
        return api.myBooking(request.getId(), request.getId2());
    }

    public interface MyBookingApi {
        @FormUrlEncoded
        @POST("Api/Mybooking")
        Call<MyBookingResponse> myBooking(@Field("user_id") String userId, @Field("type") String type);
    }
}
