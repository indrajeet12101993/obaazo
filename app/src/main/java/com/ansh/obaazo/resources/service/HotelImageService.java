package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HotelImageService extends ApiService<HotelImageService.HotelImageApi, BaseRequest, HotelImageResponse> {


    public HotelImageService(Context context) {
        super(context);
    }

    @Override
    protected Class<HotelImageApi> getAPI() {
        return HotelImageApi.class;
    }

    @Override
    protected Call<HotelImageResponse> onExecute(HotelImageApi api, BaseRequest request) {
        return api.hotelImage(request.getId());
    }

    public interface HotelImageApi {
        @FormUrlEncoded
        @POST("Api/listimage")
        Call<HotelImageResponse> hotelImage(@Field("hotel_id") String hotelId);
    }
}
