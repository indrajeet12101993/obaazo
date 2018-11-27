package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HotelRoomService extends ApiService<HotelRoomService.HotelRoomApi, BaseRequest, HotelRoomResponse> {


    public HotelRoomService(Context context) {
        super(context);
    }

    @Override
    protected Class<HotelRoomApi> getAPI() {
        return HotelRoomApi.class;
    }

    @Override
    protected Call<HotelRoomResponse> onExecute(HotelRoomApi api, BaseRequest request) {
        return api.hotelRoomApi(request.getId(), request.getId2(), request.getId3());
    }

    public interface HotelRoomApi {
        @FormUrlEncoded
        @POST("Api/Listroom")
        Call<HotelRoomResponse> hotelRoomApi(@Field("hotel_id") String hotelId,
                                             @Field("checkin") String startDate,
                                             @Field("checkout") String endDate);
    }
}
