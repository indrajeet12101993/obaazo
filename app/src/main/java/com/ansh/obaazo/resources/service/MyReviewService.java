package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.MyReviewResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jeevan Gupta on 11/17/2018.
 */
public class MyReviewService extends ApiService<MyReviewService.MyReviewApi, BaseRequest, MyReviewResponse> {

    public MyReviewService(Context context) {
        super(context);
    }

    @Override
    protected Class<MyReviewApi> getAPI() {
        return MyReviewApi.class;
    }

    @Override
    protected Call<MyReviewResponse> onExecute(MyReviewApi api, BaseRequest request) {
        return api.myReview(request.getId());
    }

    public interface MyReviewApi {
        @FormUrlEncoded
        @POST("Api/review")
        Call<MyReviewResponse> myReview(@Field("user_id") String userId);
    }
}
