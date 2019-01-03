package com.ansh.obaazo.fragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyReview extends BaseFragment {

    private RecyclerView rvMyReview;
    private MyReviewAdapter reviewAdapter;
    private View mView;

    public FragmentMyReview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fragment_my_review, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        ((TextView) mView.findViewById(R.id.tv_title)).setText("My Review");
        rvMyReview = mView.findViewById(R.id.rv_my_review);
        rvMyReview.setLayoutManager(new LinearLayoutManager(getActivity()));
        reviewAdapter = new MyReviewAdapter(getActivity(), new ArrayList<MyReviewResponse.Result>());
        rvMyReview.setAdapter(reviewAdapter);

        hitMyReviewApi();
    }

    @Override
    protected void initListener() {

    }

    private void hitMyReviewApi() {
        ((BaseActivity) getActivity()).showLoadingDialog();
        BaseRequest baseRequest = new BaseRequest();

        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        if (!TextUtils.isEmpty(tempDetails)) {
            UserDetails results = new Gson().fromJson(tempDetails, UserDetails.class);
            baseRequest.setId(results.getId());
        }
        new MyReviewService(getActivity()).execute(baseRequest, new ApiCallback<MyReviewResponse>() {
            @Override
            public void onSuccess(Call<MyReviewResponse> call, MyReviewResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    if (response.getResult() != null && response.getResult().size() != 0) {
                        mView.findViewById(R.id.iv_empty).setVisibility(View.GONE);
                        reviewAdapter.setmData(response.getResult());
                    } else {
                        mView.findViewById(R.id.iv_empty).setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getActivity(), response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                if (getActivity() != null)
                    ((BaseActivity) getActivity()).hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(getActivity(), "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void bindDataWithUi() {

    }

}
