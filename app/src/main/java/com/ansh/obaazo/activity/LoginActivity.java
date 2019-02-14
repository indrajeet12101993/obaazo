package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.ansh.obaazo.R;
import com.ansh.obaazo.fragment.FragmentLogin;

import androidx.fragment.app.Fragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        addFragment(new FragmentLogin(), R.id.fl_login);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment f : getSupportFragmentManager().getFragments()) {
            f.onActivityResult(requestCode, resultCode, data);
        }
    }
}
