package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ansh.obaazo.R;

public class ActivityMyBookingDetails extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_booking_details;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        ((TextView) findViewById(R.id.tv_title)).setText("Booking Details");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }
}
