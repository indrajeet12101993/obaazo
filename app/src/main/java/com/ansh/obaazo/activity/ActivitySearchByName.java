package com.ansh.obaazo.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import java.util.ArrayList;

import retrofit2.Call;

public class ActivitySearchByName extends BaseActivity {

    private RecyclerView rvHotelList;
    private AdapterHotelList adapterHotelList;
    private EditText etTitle;
    private String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        date = PreferencesUtils.getString(AppConstant.START_DATE);
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
        request.setId2(date);
        HotelSearchServiceByName serviceByName = new HotelSearchServiceByName(this);
        serviceByName.execute(request, new ApiCallback<HotelSearchResponse>() {
            @Override
            public void onSuccess(Call<HotelSearchResponse> call, HotelSearchResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    adapterHotelList.setmList(response.getResult());
                    adapterHotelList.setHotelPrice(response.getHotelPrices());
                } else {
                    adapterHotelList.setmList(response.getResult());
                   // Toast.makeText(ActivitySearchByName.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivitySearchByName.this, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
