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
        return api.hotelSearchApi(request.getLatitude(),
                request.getLongitude(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                request.getMin(),
                request.getMax(),
                request.getStar(),
                request.getHotelType(),
                request.getAminity());
    }

    public interface HotelSearchApi {
        @FormUrlEncoded
        @POST("Api/pricefilter")
        Call<HotelSearchResponse> hotelSearchApi(@Field("lat") Double latitude,
                                                 @Field("long") Double longitude,
                                                 @Field("checkin") String checkInDate,
                                                 @Field("checkout") String checkOutDate,
                                                 @Field("min") String min,
                                                 @Field("max") String max,
                                                 @Field("star") String star,
                                                 @Field("hoteltype") String hotelType,
                                                 @Field("amenities") String aminity);
    }
}
