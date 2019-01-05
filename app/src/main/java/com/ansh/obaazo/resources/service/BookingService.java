package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BookingRequest;
import com.ansh.obaazo.resources.response.BaseResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class BookingService extends ApiService<BookingService.BookingApi, BookingRequest, BaseResponse> {


    public BookingService(Context context) {
        super(context);
    }

    @Override
    protected Class<BookingApi> getAPI() {
        return BookingApi.class;
    }

    @Override
    protected Call<BaseResponse> onExecute(BookingApi api, BookingRequest request) {
        return api.addBookingApi(request);
    }

    public interface BookingApi {
        @POST("Api/hotelbooking")
        Call<BaseResponse> addBookingApi(@Body BookingRequest request);
    }
}
