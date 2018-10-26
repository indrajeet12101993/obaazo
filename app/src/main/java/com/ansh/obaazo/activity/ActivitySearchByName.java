package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AdapterHotelList;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelSearchResponse;
import com.ansh.obaazo.resources.service.HotelSearchServiceByName;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import java.util.ArrayList;

import retrofit2.Call;

public class ActivitySearchByName extends BaseActivity {

    private RecyclerView rvHotelList;
    private AdapterHotelList adapterHotelList;
    private EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_by_name;

    }

    @Override
    protected void initView() {
        initCustomToolbar();
        rvHotelList = findViewById(R.id.rv_hotel_list);
        rvHotelList.setLayoutManager(new LinearLayoutManager(this));
        adapterHotelList = new AdapterHotelList(this, new ArrayList<HotelInfo>());
        rvHotelList.setAdapter(adapterHotelList);

        etTitle = findViewById(R.id.et_title);

    }

    @Override
    protected void initListener() {
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hitHotelApi(etTitle.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    protected void bindDataWithUi() {

    }


    public void hitHotelApi(String key) {
        BaseRequest request = new BaseRequest();
        request.setId(key);
        HotelSearchServiceByName serviceByName = new HotelSearchServiceByName(this);
        serviceByName.execute(request, new ApiCallback<HotelSearchResponse>() {
            @Override
            public void onSuccess(Call<HotelSearchResponse> call, HotelSearchResponse response) {
                adapterHotelList.setmList(response.getResult());
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivitySearchByName.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
