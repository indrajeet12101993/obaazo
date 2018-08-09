package com.ansh.obaazo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.ansh.obaazo.adapter.BottomNavigationViewHelper;
import com.ansh.obaazo.adapter.OfferAdapter;
import com.ansh.obaazo.adapter.TreandingAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTreading;
    private RecyclerView rvOffer;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        TreandingAdapter adapter = new TreandingAdapter(this, new ArrayList<String>());

        rvTreading.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTreading.setAdapter(adapter);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivitySearch.class));
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
