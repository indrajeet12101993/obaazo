package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.ObazoMoneyResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ObaazoMoneyService extends ApiService<ObaazoMoneyService.ObaazoMoneyApi, BaseRequest, ObazoMoneyResponse> {
    public ObaazoMoneyService(Context context) {
        super(context);
    }

    @Override
    protected Class<ObaazoMoneyApi> getAPI() {
        return ObaazoMoneyApi.class;
    }

    @Override
    protected Call<ObazoMoneyResponse> onExecute(ObaazoMoneyApi api, BaseRequest request) {
        return api.moneyDetails(request.getId());
    }

    public interface ObaazoMoneyApi {
        @FormUrlEncoded
        @POST("Api/obaazomoney")
        Call<ObazoMoneyResponse> moneyDetails(@Field("user_id") String userId);
    }
}
