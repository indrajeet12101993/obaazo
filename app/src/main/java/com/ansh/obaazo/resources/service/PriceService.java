package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.PriceRequest;
import com.ansh.obaazo.resources.response.PriceResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class PriceService extends ApiService<PriceService.PriceApi, PriceRequest, PriceResponse> {

    public PriceService(Context context) {
        super(context);
    }

    @Override
    protected Class<PriceApi> getAPI() {
        return PriceApi.class;
    }

    @Override
    protected Call<PriceResponse> onExecute(PriceApi api, PriceRequest request) {
        return api.price(request);
    }

    public interface PriceApi {
        @POST("Api/Calculation")
        Call<PriceResponse> price(@Body PriceRequest request);
    }
}
