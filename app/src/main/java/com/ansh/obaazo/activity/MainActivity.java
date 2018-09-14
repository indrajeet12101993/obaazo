package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.BottomNavigationViewHelper;
import com.ansh.obaazo.fragment.FragmentCash;
import com.ansh.obaazo.fragment.FragmentHome;
import com.ansh.obaazo.fragment.FragmentProfile;

public class MainActivity extends BaseActivity {


    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // addFragment(new FragmentHome(), R.id.fm_main);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        addFragment(new FragmentHome(), R.id.fm_main);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            navigation.getMenu().findItem(R.id.nav_bottom_search).setIcon((R.id.nav_bottom_search == item.getItemId()) ? R.drawable.ic_nav_bottom_search_a : R.drawable.ic_nav_bottom_search);
            navigation.getMenu().findItem(R.id.nav_bottom_cash).setIcon((R.id.nav_bottom_cash == item.getItemId()) ? R.drawable.ic_bottom_notification_a : R.drawable.ic_bottom_notification);
            navigation.getMenu().findItem(R.id.nav_bottom_profile).setIcon((R.id.nav_bottom_profile == item.getItemId()) ? R.drawable.ic_bottom_profile_a : R.drawable.ic_bottom_profile);

            switch (item.getItemId()) {
                case R.id.nav_bottom_search:
                    replaceFragment(new FragmentHome(), R.id.fm_main, false);
                    return true;
                case R.id.nav_bottom_cash:
                    replaceFragment(new FragmentCash(), R.id.fm_main, false);
                    return true;
                case R.id.nav_bottom_profile:
                    replaceFragment(new FragmentProfile(), R.id.fm_main, false);
                    return true;
            }
            return false;
        }
    };


}
