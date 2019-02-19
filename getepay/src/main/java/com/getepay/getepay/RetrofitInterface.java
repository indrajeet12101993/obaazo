package com.getepay.getepay;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
     @POST("merchantTxn/appMerchantTxn")
     Call<PaymentResult> getStringScalar(@Body Payment body);
}
