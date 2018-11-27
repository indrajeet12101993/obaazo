package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.CouponListResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class CouponListService extends ApiService<CouponListService.CouponListApi, BaseRequest, CouponListResponse> {

    public CouponListService(Context context) {
        super(context);
    }

    @Override
    protected Class<CouponListApi> getAPI() {
        return CouponListApi.class;
    }

    @Override
    protected Call<CouponListResponse> onExecute(CouponListApi api, BaseRequest request) {
        return api.couponList(request.getId());
    }

    public interface CouponListApi {
        @FormUrlEncoded
        @POST("Api/coupon")
        Call<CouponListResponse> couponList(@Field("hotel_id") String hotelId);
    }
}
