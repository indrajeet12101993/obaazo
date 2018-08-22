package com.ansh.obaazo.activit;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AdapterHotelList;
import com.ansh.obaazo.resources.request.HotelSearchRequest;
import com.ansh.obaazo.resources.response.HotelSearchResponse;
import com.ansh.obaazo.resources.service.HotelSearchService;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import java.util.ArrayList;

import retrofit2.Call;

public class ActivitySearch extends BaseActivity {

    private RecyclerView rvHotelList;
    private AdapterHotelList adapterHotelList;
    private ArrayList<HotelSearchResponse.ResultBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        rvHotelList = findViewById(R.id.rv_hotel_list);
        rvHotelList.setLayoutManager(new LinearLayoutManager(ActivitySearch.this));
        adapterHotelList = new AdapterHotelList(this, mList);
        rvHotelList.setAdapter(adapterHotelList);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {
        hitHotelSearchApi();

    }

    private void hitHotelSearchApi() {
        showLoadingDialog();
        new HotelSearchService(this).execute(new HotelSearchRequest(), new ApiCallback<HotelSearchResponse>() {
            @Override
            public void onSuccess(Call<HotelSearchResponse> call, HotelSearchResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    adapterHotelList.setmList(response.getResult());
                }
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {

            }
        });
    }

}
