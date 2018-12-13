package com.ansh.obaazo.payment;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PaymentClient {

     private final String accessCode = "4YRUXLSRO20O8NIH";
  //  private final String accessCode = "AVCV81FJ00AW02VCWA";

    private final String currencyType = "INR";
      private final String redirectUrl = "http://122.182.6.216/merchant/ccavResponseHandler.jsp";
  //  private final String redirectUrl = "https://obaazo.com/Cc/ccavResponseHandler/";
    // private final String cancelUrl = "http://122.182.6.216/merchant/ccavResponseHandler.jsp";
    private final String cancelUrl = "http://122.182.6.216/merchant/ccavResponseHandler.jsp";

  //  private final String baseUrl = "https://obaazo.com/Cc/ccavRequestHandler/";
 //   private final String baseUrl = "https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction/";
     private final String baseUrl = "https://secure.ccavenue.com/transaction/jsp/";
    //  private final String merchantId = "190880";
    private final String merchantId = "190880";
    private String orderId = String.valueOf(ServiceUtility.randInt(0, 9999999));

    public Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder().addHeader("Content-Type", "text/json;Charset=UTF-8");
                        return chain.proceed(builder.build());
                    }
                })
                .connectTimeout(60, SECONDS)
                .readTimeout(60, SECONDS);
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public String getMerchantId() {
        return merchantId;
    }


    public String getOrderId() {
        return orderId;
    }


}
