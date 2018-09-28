package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.BottomNavigationViewHelper;
import com.ansh.obaazo.fragment.FragmentCash;
import com.ansh.obaazo.fragment.FragmentHome;
import com.ansh.obaazo.fragment.FragmentProfile;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView navigation;
    private NavigationView navigationView;
    private ImageView ivUserImage;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //  toolbar.setTitle(setToolbarName());
            //  toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_dehaze));

        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void initListener() {
        ivUserImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        ivUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.setSelectedItemId(R.id.nav_bottom_profile);
                drawer.closeDrawer(Gravity.START);
            }
        });
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_my_booking:
                startActivity(new Intent(MainActivity.this, ActivityMyBooking.class));
                break;
            case R.id.nav_my_review:
                startActivity(new Intent(MainActivity.this, ActivityMyReview.class));
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
}
