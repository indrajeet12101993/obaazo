package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.HotelSearchRequest;
import com.ansh.obaazo.resources.response.HotelSearchResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HotelSearchService extends ApiService<HotelSearchService.HotelSearchApi, HotelSearchRequest, HotelSearchResponse> {

    public HotelSearchService(Context context) {
        super(context);
    }

    @Override
    protected Class<HotelSearchApi> getAPI() {
        return HotelSearchApi.class;
    }

    @Override
    protected Call<HotelSearchResponse> onExecute(HotelSearchApi api, HotelSearchRequest request) {
        return api.hotelSearchApi(request.getLatitude(), request.getLongitude(), request.getCheckInDate(), request.getCheckOutDate());
    }

    public interface HotelSearchApi {
        @FormUrlEncoded
        @POST("Api/Listhotel")
        Call<HotelSearchResponse> hotelSearchApi(@Field("lat") Double latitude,
                                                 @Field("longg") Double longitude,
                                                 @Field("checkin") String checkInDate,
                                                 @Field("checkout") String checkOutDate);
    }
}
