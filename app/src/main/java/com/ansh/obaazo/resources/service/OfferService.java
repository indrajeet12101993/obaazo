package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.OfferResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.GET;

public class OfferService extends ApiService<OfferService.OfferApi, BaseRequest, OfferResponse> {

    public OfferService(Context context) {
        super(context);
    }

    @Override
    protected Class<OfferApi> getAPI() {
        return OfferApi.class;
    }

    @Override
    protected Call<OfferResponse> onExecute(OfferApi api, BaseRequest request) {
        return api.offer();
    }

    public interface OfferApi {
        @GET("Api/offer")
        Call<OfferResponse> offer();
    }
}
