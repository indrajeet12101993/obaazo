package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ansh.obaazo.R;
import com.ansh.obaazo.fragment.FragmentLogin;
import com.facebook.login.LoginFragment;

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
        addFragment(new FragmentLogin(),R.id.fl_login);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }
}
