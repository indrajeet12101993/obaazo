package com.ansh.obaazo.resources.service;

import android.content.Context;

import com.ansh.obaazo.resources.request.UpdateProfileRequest;
import com.ansh.obaazo.resources.response.LoginResponse;
import com.ansh.obaazo.resources.response.UpdateProfileResponse;
import com.ansh.obaazo.web.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class UpdateProfileService extends ApiService<UpdateProfileService.UpdateProfileApi, UpdateProfileRequest, UpdateProfileResponse> {

    public UpdateProfileService(Context context) {
        super(context);
    }

    @Override
    protected Class<UpdateProfileApi> getAPI() {
        return UpdateProfileApi.class;
    }

    @Override
    protected Call<UpdateProfileResponse> onExecute(UpdateProfileApi api, UpdateProfileRequest request) {
        return api.updateProfile(request.getUserId(), request.getName(), request.getEmail(), request.getMobileNo());
    }

    public interface UpdateProfileApi {
        @FormUrlEncoded
        @POST("Api/updateprofile")
        Call<UpdateProfileResponse> updateProfile(@Field("user_id") String userId
                , @Field("name") String userName
                , @Field("email") String email
                , @Field("mobile") String mobile);
    }
}
