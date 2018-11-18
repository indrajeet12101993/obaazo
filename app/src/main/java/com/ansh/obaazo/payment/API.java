package com.ansh.obaazo.payment;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface API {
    @POST("GetRSA.jsp")
    Call<String> payment(@Query("access_code") String accessCode, @Query("order_id") String orderId);
}