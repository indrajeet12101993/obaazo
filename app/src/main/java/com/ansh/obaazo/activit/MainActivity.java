package com.ansh.obaazo.activit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.BottomNavigationViewHelper;
import com.ansh.obaazo.adapter.OfferAdapter;
import com.ansh.obaazo.adapter.TreandingAdapter;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.TrendingHotelResponse;
import com.ansh.obaazo.resources.service.TrendingHotelService;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import java.util.ArrayList;

import retrofit2.Call;

public class MainActivity extends BaseActivity {

    private RecyclerView rvTreading;
    private RecyclerView rvOffer;
    private BottomNavigationView navigation;
    private TreandingAdapter adapter;
    private ArrayList<TrendingHotelResponse.ResultBean> mList = new ArrayList<>();

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

        rvTreading = findViewById(R.id.rv_trending);
        rvOffer = findViewById(R.id.rv_offer);
        rvOffer.setNestedScrollingEnabled(false);
        rvTreading.setNestedScrollingEnabled(false);

        OfferAdapter offerAdapter = new OfferAdapter(this, new ArrayList<String>());
        rvOffer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvOffer.setAdapter(offerAdapter);

        adapter = new TreandingAdapter(this, mList);

        rvTreading.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTreading.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivitySearch.class));
            }
        });
    }

    @Override
    protected void bindDataWithUi() {
        hitTrendingApi();

    }

    private void hitTrendingApi() {
        showLoadingDialog();
        new TrendingHotelService(this).execute(new BaseRequest(), new ApiCallback<TrendingHotelResponse>() {
            @Override
            public void onSuccess(Call<TrendingHotelResponse> call, TrendingHotelResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    adapter.setmList(response.getResult());
                } else {
                    Toast.makeText(MainActivity.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {

            }
        });
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
