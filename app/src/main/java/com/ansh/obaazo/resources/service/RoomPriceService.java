package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.RoomPriceRequest;
import com.ansh.obaazo.resources.response.RoomPriceResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RoomPriceService extends ApiService<RoomPriceService.RoomPriceApi, RoomPriceRequest, RoomPriceResponse> {
    public RoomPriceService(Context context) {
        super(context);
    }

    @Override
    protected Class<RoomPriceApi> getAPI() {
        return RoomPriceApi.class;
    }

    @Override
    protected Call<RoomPriceResponse> onExecute(RoomPriceApi api, RoomPriceRequest request) {
        return api.roomPrice(request.getCheckIn(), request.getCheckOut(), request.getRoomId());
    }

    public interface RoomPriceApi {
        @FormUrlEncoded
        @POST("Api/roomdetails")
        Call<RoomPriceResponse> roomPrice(@Field("checkin") String checkIn,
                                          @Field("checkout") String checkOut,
                                          @Field("room_id") String roomId);
    }
}
