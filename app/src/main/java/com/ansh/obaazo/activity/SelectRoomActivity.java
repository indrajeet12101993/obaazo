package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.RoomsAdapter;
import com.ansh.obaazo.listener.RItemListener;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.request.RoomPriceRequest;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.resources.response.RoomPriceResponse;
import com.ansh.obaazo.resources.service.HotelRoomService;
import com.ansh.obaazo.resources.service.RoomPriceService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;

public class SelectRoomActivity extends BaseActivity implements RItemListener<HotelRoomResponse.ResultBean> {
    private RecyclerView rvRooms;
    private RoomsAdapter roomsAdapter;
    private HotelInfo hotelDetails;
    private String hotelName = "";
    private int selectedPosition = 0;
    private TextView tvDates;
    private String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_selecte_room;
    }

    @Override
    protected void initView() {
        hotelDetails = getIntent().getParcelableExtra(AppConstant.HOTEL_DETAILS);
        hotelName = hotelDetails.getHotel_name();
        tvDates = findViewById(R.id.tv_dates);
        initCustomToolbar();
        rvRooms = findViewById(R.id.rv_rooms);
        rvRooms.setLayoutManager(new LinearLayoutManager(this));
        roomsAdapter = new RoomsAdapter(this, new HotelRoomResponse(), this);
        rvRooms.setAdapter(roomsAdapter);
        hitRoomApi();


    }

    @Override
    public String setToolbarName() {
        return hotelName;
    }

    private void hitRoomApi() {
        showLoadingDialog();
        BaseRequest request = new BaseRequest();
        String hotelId = getIntent().getStringExtra(AppConstant.HOTEL_ID);
        request.setId(hotelId);
        request.setId2(PreferencesUtils.getString(AppConstant.START_DATE));
        request.setId3(PreferencesUtils.getString(AppConstant.END_DATE));
        new HotelRoomService(this).execute(request, new ApiCallback<HotelRoomResponse>() {
            @Override
            public void onSuccess(Call<HotelRoomResponse> call, HotelRoomResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    roomsAdapter.setRoomPriceData(response.getRoomPrice());
                    roomsAdapter.setmData(response);
                } else {
                    Toast.makeText(SelectRoomActivity.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(SelectRoomActivity.this, "Api Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void initListener() {

        findViewById(R.id.tv_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                    initBooking();
                } else {
                    startActivityForResult(new Intent(SelectRoomActivity.this, LoginActivity.class), 1009);
                }

            }
        });

        tvDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SelectRoomActivity.this, ActivityDateSelecte.class), 1003);

            }
        });

    }

    @Override
    protected void bindDataWithUi() {
        bindDateData();
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
        }
    }


    @Override
    public void onItemClick(HotelRoomResponse.ResultBean item, int position) {
        selectedPosition = position;
        roomId = item.getId();
        startActivityForResult(new Intent(SelectRoomActivity.this, ActivitySelect.class), 1004);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1004 && resultCode == RESULT_OK) {
            String stringExtra = data.getStringExtra(AppConstant.PERSON_DETAILS);
            if (!TextUtils.isEmpty(stringExtra)) {
                BookingInfo bookingInfo = new Gson().fromJson(stringExtra, BookingInfo.class);
                hitRoomPriceApi(bookingInfo);
            }
        }
        if (requestCode == 1003 && resultCode == RESULT_OK) {
            bindDateData();
        }
        if (requestCode == 1009 && resultCode == RESULT_OK) {
            initBooking();
        }
    }


    public void initBooking() {
        startActivity(new Intent(SelectRoomActivity.this, ActivityBookRoom.class)
                .putExtra(AppConstant.HOTEL_DETAILS, hotelDetails));
    }


    private void bindDateData() {
        String startDate = PreferencesUtils.getString(AppConstant.START_DATE);
        String endDate = PreferencesUtils.getString(AppConstant.END_DATE);
        Calendar calStart = DateUtils.formatDate(startDate);
        Calendar calEnd = DateUtils.formatDate(endDate);
        String tempDates = "";
        if (calStart != null && calEnd != null) {
            tempDates = calStart.get(Calendar.DATE) + " " + DateUtils.parseMonth(calStart.get(Calendar.MONTH)) + " - " +
                    calEnd.get(Calendar.DATE) + " " + DateUtils.parseMonth(calEnd.get(Calendar.MONTH));
            tvDates.setText(tempDates);
        }
    }


    public void hitRoomPriceApi(final BookingInfo info) {
        //   roomsAdapter.setRoomData(bookingInfo, selectedPosition);
        showLoadingDialog();
        RoomPriceRequest roomPriceRequest = new RoomPriceRequest();
        roomPriceRequest.setCheckIn(PreferencesUtils.getString(AppConstant.START_DATE));
        roomPriceRequest.setCheckOut(PreferencesUtils.getString(AppConstant.END_DATE));
        roomPriceRequest.setRoomId(roomId);

        new RoomPriceService(this).execute(roomPriceRequest, new ApiCallback<RoomPriceResponse>() {
            @Override
            public void onSuccess(Call<RoomPriceResponse> call, RoomPriceResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    Log.e("", "onSuccess: ");
                    calculation(response, info);
                } else {
                    Toast.makeText(SelectRoomActivity.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(SelectRoomActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void calculation(RoomPriceResponse response, BookingInfo info) {


        if (response.getResult() != null) {
            for (int i = 0; i < response.getResult().size(); i++) {
                RoomPriceResponse.ResultBean priceRate = response.getResult().get(i);
            }
        }

    }
}
