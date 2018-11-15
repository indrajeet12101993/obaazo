package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelReviewResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HotelReviewService extends ApiService<HotelReviewService.HotelReviewApi, BaseRequest, HotelReviewResponse> {

    public HotelReviewService(Context context) {
        super(context);
    }

    @Override
    protected Class<HotelReviewApi> getAPI() {
        return HotelReviewApi.class;
    }

    @Override
    protected Call<HotelReviewResponse> onExecute(HotelReviewApi api, BaseRequest request) {
        return api.hotelReview(request.getId());
    }

    public interface HotelReviewApi {
        @FormUrlEncoded
        @POST("Api/Hotelreview")
        Call<HotelReviewResponse> hotelReview(@Field("hotel_id") String hotelId);
    }
}
