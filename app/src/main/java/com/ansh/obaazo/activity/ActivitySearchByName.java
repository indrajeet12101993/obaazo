package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ansh.obaazo.R;

public class ActivitySearchByName extends BaseActivity {

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

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void bindDataWithUi() {

    }
}
