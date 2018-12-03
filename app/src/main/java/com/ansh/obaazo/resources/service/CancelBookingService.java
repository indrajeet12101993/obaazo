package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.BaseResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class CancelBookingService extends ApiService<CancelBookingService.CancelBookingApi, BaseRequest, BaseResponse> {

    public CancelBookingService(Context context) {
        super(context);
    }

    @Override
    protected Class<CancelBookingApi> getAPI() {
        return CancelBookingApi.class;
    }

    @Override
    protected Call<BaseResponse> onExecute(CancelBookingApi api, BaseRequest request) {
        return api.cancelBooking(request.getId(), request.getId2());
    }

    public interface CancelBookingApi {

        @FormUrlEncoded
        @POST("Api/cancelbooking")
        Call<BaseResponse> cancelBooking(@Field("booking_id") String bookingId, @Field("otp") String otp);
    }
}
