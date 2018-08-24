package com.ansh.obaazo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activit.ActivitySearch;
import com.ansh.obaazo.activit.BaseActivity;
import com.ansh.obaazo.adapter.OfferAdapter;
import com.ansh.obaazo.adapter.TreandingAdapter;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.TrendingHotelResponse;
import com.ansh.obaazo.resources.service.TrendingHotelService;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends BaseFragment {

    private View mView;
    private RecyclerView rvTreading;
    private RecyclerView rvOffer;

    private TreandingAdapter adapter;
    private ArrayList<TrendingHotelResponse.ResultBean> mList = new ArrayList<>();

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        rvTreading = mView.findViewById(R.id.rv_trending);
        rvOffer = mView.findViewById(R.id.rv_offer);
        rvOffer.setNestedScrollingEnabled(false);
        rvTreading.setNestedScrollingEnabled(false);

        OfferAdapter offerAdapter = new OfferAdapter(getActivity(), new ArrayList<String>());
        rvOffer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvOffer.setAdapter(offerAdapter);

        adapter = new TreandingAdapter(getActivity(), mList);
        rvTreading.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTreading.setAdapter(adapter);

    }

    @Override
    protected void initListener() {
        mView.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActivitySearch.class));
            }
        });
    }

    @Override
    protected void bindDataWithUi() {
        hitTrendingApi();
    }

    private void hitTrendingApi() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showLoadingDialog();
        new TrendingHotelService(getActivity()).execute(new BaseRequest(), new ApiCallback<TrendingHotelResponse>() {
            @Override
            public void onSuccess(Call<TrendingHotelResponse> call, TrendingHotelResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    adapter.setmList(response.getResult());
                } else {
                    Toast.makeText(getActivity(), response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                ((BaseActivity) getActivity()).hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {

            }
        });
    }

}
