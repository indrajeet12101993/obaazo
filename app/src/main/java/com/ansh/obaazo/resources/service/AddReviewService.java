package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.AddReviewRequest;
import com.ansh.obaazo.resources.response.AddReviewResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jeevan Gupta on 11/17/2018.
 */
public class AddReviewService extends ApiService<AddReviewService.AddReviewApi, AddReviewRequest, AddReviewResponse> {


    public AddReviewService(Context context) {
        super(context);
    }

    @Override
    protected Class<AddReviewApi> getAPI() {
        return AddReviewApi.class;
    }

    @Override
    protected Call<AddReviewResponse> onExecute(AddReviewApi api, AddReviewRequest request) {
        return api.addReview(request.getUserId(), request.getUserName(), request.getHotelId(),
                request.getHotelName(), request.getHotelRating(), request.getComment());
    }

    public interface AddReviewApi {
        @FormUrlEncoded
        @POST("/Api/Givereview")
        Call<AddReviewResponse> addReview(@Field("user_id") String userId,
                                          @Field("user_name") String userName,
                                          @Field("hotel_id") String hotelId,
                                          @Field("hotel_name") String hotelName,
                                          @Field("rating") String rating,
                                          @Field("comment") String comments);
    }
}
