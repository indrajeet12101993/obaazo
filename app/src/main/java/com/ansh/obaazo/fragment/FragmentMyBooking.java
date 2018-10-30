package com.ansh.obaazo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.adapter.MyBookingAdapter;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.resources.service.MyBookingService;
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
public class FragmentMyBooking extends BaseFragment {


    private View mView;
    private RecyclerView rvMyBooking;
    private MyBookingAdapter bookingAdapter;
    private UserDetails userDetails;

    public FragmentMyBooking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_booking, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        rvMyBooking = mView.findViewById(R.id.rv_my_booking);
        rvMyBooking.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookingAdapter = new MyBookingAdapter(getContext(), new ArrayList<MyBookingResponse.ResultBean>());
        rvMyBooking.setAdapter(bookingAdapter);


    }

    private void hitMyBookingApi() {
        if (getActivity() != null) ((BaseActivity) getActivity()).showLoadingDialog();
        BaseRequest request = new BaseRequest();
        request.setId(userDetails.getId());
        MyBookingService bookingService = new MyBookingService(getActivity());
        bookingService.execute(request, new ApiCallback<MyBookingResponse>() {
            @Override
            public void onSuccess(Call<MyBookingResponse> call, MyBookingResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    bookingAdapter.setmData(response.getResult());
                    mView.findViewById(R.id.iv_no_data).setVisibility((response.getResult() != null && response.getResult().size() != 0) ? View.GONE : View.VISIBLE);
                } else {
                    mView.findViewById(R.id.iv_no_data).setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                if (getActivity() != null) ((BaseActivity) getActivity()).hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(getContext(), "Api error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {
        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        if (!TextUtils.isEmpty(tempDetails)) {
            userDetails = new Gson().fromJson(tempDetails, UserDetails.class);
            hitMyBookingApi();
        } else {
            Toast.makeText(getActivity(), "Somethings Went Wrong", Toast.LENGTH_SHORT).show();
        }


    }

}
