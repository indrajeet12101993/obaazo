package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.BookingDetailsResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class BookingDetailsService extends ApiService<BookingDetailsService.BookingDetailsApi, BaseRequest, BookingDetailsResponse> {
    public BookingDetailsService(Context context) {
        super(context);
    }

    @Override
    protected Class<BookingDetailsApi> getAPI() {
        return BookingDetailsApi.class;
    }

    @Override
    protected Call<BookingDetailsResponse> onExecute(BookingDetailsApi api, BaseRequest request) {
        return api.bookingDetails(request.getId());
    }

    public interface BookingDetailsApi {
        @FormUrlEncoded
        @POST("Api/MybookingDetails")
        Call<BookingDetailsResponse> bookingDetails(@Field("booking_id") String bookingId);
    }
}
