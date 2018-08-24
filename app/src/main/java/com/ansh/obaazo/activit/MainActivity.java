package com.ansh.obaazo.activit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.BottomNavigationViewHelper;
import com.ansh.obaazo.fragment.FragmentHome;

public class MainActivity extends BaseActivity {


    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
        addFragment(new FragmentHome(),R.id.fm_main);


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
            navigation.getMenu().findItem(R.id.nav_bottom_notification).setIcon((R.id.nav_bottom_notification == item.getItemId()) ? R.drawable.ic_bottom_notification_a : R.drawable.ic_bottom_notification);
            navigation.getMenu().findItem(R.id.nav_bottom_profile).setIcon((R.id.nav_bottom_profile == item.getItemId()) ? R.drawable.ic_bottom_profile_a : R.drawable.ic_bottom_profile);

            switch (item.getItemId()) {
                case R.id.nav_bottom_search:
                   /* FragmentSearch f2 = new FragmentSearch();
                    loadFrag(f2, "search", fm);
                    toolbar.setTitle("Search");*/
                    return true;
                case R.id.nav_bottom_notification:
                   /* FragmentNotification notification = new FragmentNotification();
                    loadFrag(notification, "notification", fm);
                    toolbar.setTitle("Notification");*/
                    return true;
                case R.id.nav_bottom_profile:
                   /* FragmentProfile fprof = new FragmentProfile();
                    loadFrag(fprof, "profile", fm);
                    toolbar.setTitle("Profile");*/
                    return true;
            }
            return false;
        }
    };


}
