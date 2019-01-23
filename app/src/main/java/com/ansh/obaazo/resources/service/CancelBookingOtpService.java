package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.BaseResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class CancelBookingOtpService extends ApiService<CancelBookingOtpService.CancelBookingOtpApi, BaseRequest, BaseResponse> {

    public CancelBookingOtpService(Context context) {
        super(context);
    }

    @Override
    protected Class<CancelBookingOtpApi> getAPI() {
        return CancelBookingOtpApi.class;
    }

    @Override
    protected Call<BaseResponse> onExecute(CancelBookingOtpApi api, BaseRequest request) {
        return api.cancelBookingOtp(request.getId(), request.getId2());
    }

    public interface CancelBookingOtpApi {
        @FormUrlEncoded
        @POST("Api/newotp_cancel")
        Call<BaseResponse> cancelBookingOtp(@Field("booking_id") String bookingId,
                                            @Field("user_mobile") String userMobile);
    }
}
