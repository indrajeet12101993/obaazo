package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ansh.obaazo.R;

public class ActivityMyReview extends BaseActivity {

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

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }
}
