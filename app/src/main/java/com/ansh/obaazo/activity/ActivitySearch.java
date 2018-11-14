package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AdapterHotelList;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.request.HotelSearchRequest;
import com.ansh.obaazo.resources.response.HotelSearchResponse;
import com.ansh.obaazo.resources.service.HotelSearchService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;

public class ActivitySearch extends BaseActivity {

    private RecyclerView rvHotelList;
    private AdapterHotelList adapterHotelList;
    private ArrayList<HotelInfo> mList = new ArrayList<>();
    private String startDate;
    private String endDate;
    private RecyclerViewSkeletonScreen skeletonScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        initCustomToolbar();

        rvHotelList = findViewById(R.id.rv_hotel_list);
        rvHotelList.setLayoutManager(new LinearLayoutManager(ActivitySearch.this));
        adapterHotelList = new AdapterHotelList(this, mList);
        rvHotelList.setAdapter(adapterHotelList);


    }

    @Override
    protected void initListener() {

        findViewById(R.id.iv_short).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
        findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivitySearch.this, ActivitySearchByName.class));
            }
        });

        ((TextView) findViewById(R.id.tv_title)).setText(PreferencesUtils.getString(AppConstant.B_LOCATION));


        findViewById(R.id.fb_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ActivitySearch.this, FilterActivity.class), 1001);
            }
        });

        findViewById(R.id.ll_booking_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* TopSheetDialog dialog = new TopSheetDialog(ActivitySearch.this);
                dialog.setContentView(R.layout.sheet_content);
                dialog.show();*/
            }
        });

    }

    @Override
    protected void bindDataWithUi() {
        startDate = PreferencesUtils.getString(AppConstant.START_DATE);
        endDate = PreferencesUtils.getString(AppConstant.END_DATE);
        Calendar calStart = DateUtils.formatDate(startDate);
        Calendar calEnd = DateUtils.formatDate(endDate);
        String tempDates = "";
        if (calStart != null && calEnd != null) {
            tempDates = calStart.get(Calendar.DATE) + " " + DateUtils.parseMonth(calStart.get(Calendar.MONTH)) + " - " +
                    calEnd.get(Calendar.DATE) + " " + DateUtils.parseMonth(calEnd.get(Calendar.MONTH));
            ((TextView) findViewById(R.id.tv_dates)).setText(tempDates);
        }
        String personDetails = PreferencesUtils.getString(AppConstant.BOOKING_DETAILS);
        if (!TextUtils.isEmpty(personDetails)) {
            BookingInfo bookingInfo = new Gson().fromJson(personDetails, BookingInfo.class);
            int noOfAdult1 = 0;
            int noOfChild = 0;
            if (bookingInfo.getPersonInfos() != null)
                for (int i = 0; i < bookingInfo.getPersonInfos().size(); i++) {
                    ArrayList<Integer> child = bookingInfo.getPersonInfos().get(i).getChild();
                    noOfAdult1 += bookingInfo.getPersonInfos().get(i).getNoOfAdult();
                    noOfChild += child.size();
                }
            ((TextView) findViewById(R.id.tv_room_count)).setText("" + bookingInfo.getPersonInfos().size());
            ((TextView) findViewById(R.id.tv_adult_count)).setText("" + noOfAdult1);
            ((TextView) findViewById(R.id.tv_child_count)).setText("" + noOfChild);
            adapterHotelList.setBookingDetails(startDate, endDate, noOfAdult1, noOfChild, bookingInfo.getPersonInfos().size(), tempDates);
        }
        hitHotelSearchApi();
    }

    private void hitHotelSearchApi() {
        skeletonScreen = Skeleton.bind(rvHotelList)
                .adapter(adapterHotelList)
                .load(R.layout.item_skeleton_hotel)
                .show();
        // showLoadingDialog();
        HotelSearchRequest request = new HotelSearchRequest();
        request.setCheckInDate(PreferencesUtils.getString(AppConstant.START_DATE));    //       28.5355; 77.3910; "09/2/2018";*/"09/2/2018";*/
        request.setCheckOutDate(PreferencesUtils.getString(AppConstant.END_DATE));
        request.setLatitude(PreferencesUtils.getDouble(AppConstant.B_LATITUDE));
        request.setLongitude(PreferencesUtils.getDouble(AppConstant.B_LONGITUDE));
        new HotelSearchService(this).execute(request, new ApiCallback<HotelSearchResponse>() {
            @Override
            public void onSuccess(Call<HotelSearchResponse> call, HotelSearchResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    adapterHotelList.setmList(response.getResult());
                    findViewById(R.id.iv_empty).setVisibility((response.getResult().size() == 0) ? View.VISIBLE : View.INVISIBLE);
                }
            }

            @Override
            public void onComplete() {
                skeletonScreen.hide();
                //  hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {

            }
        });
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();
    }
}
