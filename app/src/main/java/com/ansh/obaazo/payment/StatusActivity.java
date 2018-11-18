package com.ansh.obaazo.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;

public class StatusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_status;
    }

    @Override
    protected void initView() {
        Intent mainIntent = getIntent();
        TextView tv4 = (TextView) findViewById(R.id.textView1);
        tv4.setText(mainIntent.getStringExtra("transStatus"));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }
}
