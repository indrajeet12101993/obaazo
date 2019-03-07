package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.BookingRequest;
import com.ansh.obaazo.resources.response.BaseResponse;
import com.ansh.obaazo.resources.response.BookingDetailsResponse;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class BookingService extends ApiService<BookingService.BookingApi, BookingRequest, MyBookingResponse> {


    public BookingService(Context context) {
        super(context);
    }

    @Override
    protected Class<BookingApi> getAPI() {
        return BookingApi.class;
    }

    @Override
    protected Call<MyBookingResponse> onExecute(BookingApi api, BookingRequest request) {
        return api.addBookingApi(request);
    }

    public interface BookingApi {
        @POST("Api/hotelbooking")
        Call<MyBookingResponse> addBookingApi(@Body BookingRequest request);
    }
}
