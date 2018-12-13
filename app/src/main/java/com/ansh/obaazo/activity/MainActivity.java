package com.ansh.obaazo.activity;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.ImageView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.BottomNavigationViewHelper;
import com.ansh.obaazo.fragment.FragmentCash;
import com.ansh.obaazo.fragment.FragmentHome;
import com.ansh.obaazo.fragment.FragmentMyBooking;
import com.ansh.obaazo.fragment.FragmentMyReview;
import com.ansh.obaazo.fragment.FragmentProfile;
import com.ansh.obaazo.listener.LocationListener;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;

public class MainActivity extends BaseActivity {


    private BottomNavigationView navigation;
  //  private NavigationView navigationView;
    private ImageView ivUserImage;
  //  private DrawerLayout drawer;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // addFragment(new FragmentHome(), R.id.fm_main);


    }


    @Override
    protected void onLocationFound(Location location) {
        super.onLocationFound(location);
        this.listener.onLocationFound(location);
    }


    public void setLocationListener(LocationListener listener) {
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        navigation =  findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        addFragment(new FragmentHome(), R.id.fm_main);

  /*      Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //  toolbar.setTitle(setToolbarName());
            //  toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_dehaze));

        }*/
       /* drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/


    }

    @Override
    protected void initListener() {
       /* ivUserImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        ivUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.setSelectedItemId(R.id.nav_bottom_profile);
                drawer.closeDrawer(Gravity.START);
            }
        });*/
    }

    @Override
    protected void bindDataWithUi() {


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            navigation.getMenu().findItem(R.id.nav_bottom_search).setIcon((R.id.nav_bottom_search == item.getItemId()) ? R.drawable.ic_search_nav : R.drawable.ic_search_nav_u);
            navigation.getMenu().findItem(R.id.nav_bottom_cash).setIcon((R.id.nav_bottom_cash == item.getItemId()) ? R.drawable.ic_cash : R.drawable.ic_cash_u);
            navigation.getMenu().findItem(R.id.nav_bottom_profile).setIcon((R.id.nav_bottom_profile == item.getItemId()) ? R.drawable.ic_profile_bottom : R.drawable.ic_profile_bottom_u);
            navigation.getMenu().findItem(R.id.nav_bottom_my_bookig).setIcon((R.id.nav_bottom_my_bookig == item.getItemId()) ? R.drawable.ic_my_booking_nav_a : R.drawable.ic_my_booking_nav);
            navigation.getMenu().findItem(R.id.nav_bottom_my_review).setIcon((R.id.nav_bottom_my_review == item.getItemId()) ? R.drawable.ic_review_nav_a : R.drawable.ic_review_nav);

            invalidateOptionsMenu();
            switch (item.getItemId()) {

                case R.id.nav_bottom_search:
                    replaceFragment(new FragmentHome(), R.id.fm_main, false);
                    break;
                case R.id.nav_bottom_cash:
                    if (PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                        replaceFragment(new FragmentCash(), R.id.fm_main, false);
                    } else {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    break;
                case R.id.nav_bottom_profile:
                    if (PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                        replaceFragment(new FragmentProfile(), R.id.fm_main, false);
                    } else {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    break;
                case R.id.nav_bottom_my_bookig:
                    if (PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                        replaceFragment(new FragmentMyBooking(), R.id.fm_main, false);
                    } else {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    break;

                case R.id.nav_bottom_my_review:
                    if (PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                        replaceFragment(new FragmentMyReview(), R.id.fm_main, false);
                    } else {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    break;
            }

            return true;
        }
    };


 /*   @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_my_review:
                if (PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                    startActivity(new Intent(MainActivity.this, ActivityMyReview.class));
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                break;
            case R.id.nav_rate_us:
                Intent ishare = new Intent(Intent.ACTION_SEND);
                ishare.setType("text/plain");
                ishare.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_name) + " - http://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(ishare);
                break;
            case R.id.nav_logout:
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    /*  @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
           *//* if (getSupportFragmentManager().findFragmentById(R.id.fm_main) instanceof FragmentProfile) {
            menu.findItem(R.id.action_logout).setVisible(true);
        } else {
            menu.findItem(R.id.action_logout).setVisible(false);
        }*//*
        return super.onPrepareOptionsMenu(menu);
    }*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            PreferencesUtils.clearSharedPrefs();
            replaceFragment(new FragmentHome(), R.id.fm_main, false);
        }
        return super.onOptionsItemSelected(item);
    }*/
}
