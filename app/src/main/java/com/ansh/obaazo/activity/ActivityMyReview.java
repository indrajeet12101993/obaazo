package com.ansh.obaazo.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.MyReviewAdapter;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.MyReviewResponse;
import com.ansh.obaazo.resources.service.MyReviewService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;

public class ActivityMyReview extends BaseActivity {
    private RecyclerView rvMyReview;
    private MyReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_review;
    }

    @Override
    protected void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("My Review");
        initCustomToolbar();
        rvMyReview = findViewById(R.id.rv_my_review);
        rvMyReview.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new MyReviewAdapter(this, new ArrayList<MyReviewResponse.Result>());
        rvMyReview.setAdapter(reviewAdapter);

        hitMyReviewApi();

    }

    private void hitMyReviewApi() {
        showLoadingDialog();
        BaseRequest baseRequest = new BaseRequest();

        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        if (!TextUtils.isEmpty(tempDetails)) {
            UserDetails results = new Gson().fromJson(tempDetails, UserDetails.class);
            baseRequest.setId(results.getId());
        }
        new MyReviewService(this).execute(baseRequest, new ApiCallback<MyReviewResponse>() {
            @Override
            public void onSuccess(Call<MyReviewResponse> call, MyReviewResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    if (response.getResult() != null && response.getResult().size() != 0) {
                        findViewById(R.id.iv_empty).setVisibility(View.GONE);
                        reviewAdapter.setmData(response.getResult());
                    } else {
                        findViewById(R.id.iv_empty).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(ActivityMyReview.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivityMyReview.this, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {


    }
}
