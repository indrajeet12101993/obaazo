package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelSearchResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class HotelSearchServiceByName extends ApiService<HotelSearchServiceByName.HotelApi, BaseRequest, HotelSearchResponse> {

    public HotelSearchServiceByName(Context context) {
        super(context);
    }

    @Override
    protected Class<HotelApi> getAPI() {
        return HotelApi.class;
    }

    @Override
    protected Call<HotelSearchResponse> onExecute(HotelApi api, BaseRequest request) {
        return api.search(request.getId(), request.getId2());
    }

    public interface HotelApi {
        @FormUrlEncoded
        @POST("Api/Searchhotel")
        Call<HotelSearchResponse> search(@Field("name") String key, @Field("date") String date);
    }
}
