package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.TrendingHotelResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public class TrendingHotelService extends ApiService<TrendingHotelService.TrendingHotelApi, BaseRequest, TrendingHotelResponse> {

    public TrendingHotelService(Context context) {
        super(context);
    }

    @Override
    protected Class<TrendingHotelApi> getAPI() {
        return TrendingHotelApi.class;
    }

    @Override
    protected Call<TrendingHotelResponse> onExecute(TrendingHotelApi api, BaseRequest request) {
        return api.trendingApi();
    }

    public interface TrendingHotelApi {
        @GET("Api/trending")
        Call<TrendingHotelResponse> trendingApi();
    }
}
