package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.PersonAdapter;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.PersonInfo;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(AppConstant.PERSON_DETAILS);
            PreferencesUtils.putString(AppConstant.BOOKING_DETAILS, stringExtra);
            info = new Gson().fromJson(stringExtra, BookingInfo.class);
        }

        if (info != null && info.getPersonInfos() != null) {
            personAdapter = new PersonAdapter(this, info.getPersonInfos());
        } else {
            info= new BookingInfo();
            personAdapter = new PersonAdapter(this,info.getPersonInfos());
        }
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
                info.setPersonInfos(personAdapter.getDetails());
                Intent intent = new Intent();
                intent.putExtra(AppConstant.PERSON_DETAILS, new Gson().toJson(info));
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        });

        findViewById(R.id.tv_add_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personAdapter.getItemCount() < 3) {
                    personAdapter.addRoom();
                } else {
                    Toast.makeText(ActivitySelect.this, "You have selected maximum no of rooms,for more rooms please contact Obazzo or visit Obazzo.com.", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    protected void bindDataWithUi() {

    }


}
