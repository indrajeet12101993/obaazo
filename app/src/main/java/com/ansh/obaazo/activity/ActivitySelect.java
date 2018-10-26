package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.PersonAdapter;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.PersonInfo;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ActivitySelect extends BaseActivity {
    private RecyclerView rvPersonDetails;
    private BookingInfo info = new BookingInfo();
    private PersonAdapter personAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    protected void initView() {

        rvPersonDetails = findViewById(R.id.rv_person_details);
        rvPersonDetails.setLayoutManager(new LinearLayoutManager(this));
        personAdapter = new PersonAdapter(this, new ArrayList<PersonInfo>());
        rvPersonDetails.getItemAnimator().setChangeDuration(0);
        rvPersonDetails.setAdapter(personAdapter);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesUtils.putString(AppConstant.BOOKING_DETAILS, new Gson().toJson(info));
                onBackPressed();
            }
        });

        findViewById(R.id.tv_add_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personAdapter.addRoom();
            }
        });


    }

    @Override
    protected void bindDataWithUi() {

    }




}
