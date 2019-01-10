package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.request.HotelSearchRequest;
import com.ansh.obaazo.utils.AppConstant;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import androidx.appcompat.widget.AppCompatCheckBox;


public class FilterActivity extends BaseActivity {

    private TextView tvRest;
    private ImageView ivClose;
    private RangeSeekBar rbSeekBar;

    private AppCompatCheckBox cb1Star;
    private AppCompatCheckBox cb2Star;
    private AppCompatCheckBox cb3Star;
    private AppCompatCheckBox cb4Star;
    private AppCompatCheckBox cb5Star;

    private AppCompatCheckBox cbFarmHouse;
    private AppCompatCheckBox cbServiceApartment;
    private AppCompatCheckBox cbGuestHouse;
    private AppCompatCheckBox cbHomeStay;
    private AppCompatCheckBox cbHotel;
    private AppCompatCheckBox cbVillas;
    private AppCompatCheckBox cbPentHouse;
    private AppCompatCheckBox cbResort;
    private AppCompatCheckBox cbHostel;
    private AppCompatCheckBox cbPg;
    private AppCompatCheckBox cbBenuest;
    private String TAG = FilterActivity.class.getSimpleName();

    private int min = 0;
    private int max = 0;
    private StringBuilder star = new StringBuilder();
    private StringBuilder hotelType = new StringBuilder();
    private StringBuilder aminity = new StringBuilder();
    private CheckBox cvAllAminity;
    private CheckBox cbCoupleFrendly;
    private CheckBox cbWify;
    private CheckBox cbSpa;
    private CheckBox cbSwimingPool;
    private CheckBox cbFreeParking;
    private CheckBox cbRoomService;
    private CheckBox cbbOutdorPool;

    private HotelSearchRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    protected void initView() {
        request = getIntent().getParcelableExtra(AppConstant.FILTER);

        tvRest = findViewById(R.id.tv_rest);
        ivClose = findViewById(R.id.iv_close);
        rbSeekBar = findViewById(R.id.rb_price);

        cb1Star = findViewById(R.id.cb_1_star);
        cb2Star = findViewById(R.id.cb_2_star);
        cb3Star = findViewById(R.id.cb_3_star);
        cb4Star = findViewById(R.id.cb_4_star);
        cb5Star = findViewById(R.id.cb_5_star);

        cbFarmHouse = findViewById(R.id.cb_farm_house);
        cbServiceApartment = findViewById(R.id.cb_service_apartment);
        cbGuestHouse = findViewById(R.id.cb_guest_house);
        cbHomeStay = findViewById(R.id.cb_homte_stay);
        cbHotel = findViewById(R.id.cb_hotel);
        cbVillas = findViewById(R.id.cb_villas);
        cbPentHouse = findViewById(R.id.cb_pent_house);
        cbResort = findViewById(R.id.cb_resort);
        cbHostel = findViewById(R.id.cb_hostel);
        cbPg = findViewById(R.id.cb_pg);
        cbBenuest = findViewById(R.id.cb_benquet);

        cvAllAminity = findViewById(R.id.cb_all_aminity);


        cbCoupleFrendly = findViewById(R.id.cb_couple_frendly);
        cbWify = findViewById(R.id.cb_wi_fi);
        cbSpa = findViewById(R.id.cb_spa);
        cbSwimingPool = findViewById(R.id.cb_swimming_pool);
        cbFreeParking = findViewById(R.id.cb_free_parking);
        cbRoomService = findViewById(R.id.cb_room_service);
        cbbOutdorPool = findViewById(R.id.cb_outdoor_pool);


    }


    @Override
    protected void initListener() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        cvAllAminity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkAllAminity(b);
            }
        });
        rbSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                min = (int) rbSeekBar.getSelectedMaxValue();
                max = (int) rbSeekBar.getSelectedMinValue();
                Log.e(TAG, "onRangeSeekBarValuesChanged: Max " + rbSeekBar.getSelectedMaxValue());
                Log.e(TAG, "onRangeSeekBarValuesChanged: Max " + rbSeekBar.getSelectedMinValue());
            }
        });

        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempStar = getStar();
                String hotelType = getHotelType();
                String aminity = getAminity();

                request.setAminity(aminity);
                request.setHotelType(hotelType);
                request.setStar(tempStar);
                request.setMin(String.valueOf(max));
                request.setMax(String.valueOf(min));
                Intent intent = new Intent();
                intent.putExtra(AppConstant.FILTER, request);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        tvRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(false);
            }
        });


    }

    private String getHotelType() {
        if (cbFarmHouse.isChecked()) {
            hotelType.append(",5");
        }
        if (cbServiceApartment.isChecked()) {
            hotelType.append(",6");
        }
        if (cbGuestHouse.isChecked()) {
            hotelType.append(",7");
        }
        if (cbHomeStay.isChecked()) {
            hotelType.append(",8");
        }
        if (cbHotel.isChecked()) {
            hotelType.append(",15");
        }
        if (cbVillas.isChecked()) {
            hotelType.append(",10");
        }
        if (cbPentHouse.isChecked()) {
            hotelType.append(",11");
        }
        if (cbResort.isChecked()) {
            hotelType.append(",12");
        }
        if (cbHostel.isChecked()) {
            hotelType.append(",13");
        }
        if (cbPg.isChecked()) {
            hotelType.append(",16");
        }
        if (cbBenuest.isChecked()) {
            hotelType.append(",17");
        }
        if (!TextUtils.isEmpty(hotelType)) {
            return hotelType.substring(1, hotelType.length());
        } else {
            return "";
        }
    }

    private String getStar() {
        if (cb1Star.isChecked()) {
            star.append(",1");
        }
        if (cb2Star.isChecked()) {
            star.append(",2");
        }
        if (cb3Star.isChecked()) {
            star.append(",3");
        }
        if (cb4Star.isChecked()) {
            star.append(",4");
        }
        if (cb5Star.isChecked()) {
            star.append(",5");
        }
        if (!TextUtils.isEmpty(star)) {
            return star.substring(1, star.length());
        } else {
            return "";
        }
    }


    private String getAminity() {
        if (cbCoupleFrendly.isChecked()) {
            aminity.append(",couple friendly");
        }
        if (cbWify.isChecked()) {
            aminity.append(",wiffy");
        }
        if (cbSpa.isChecked()) {
            aminity.append(",spa");
        }
        if (cbSwimingPool.isChecked()) {
            aminity.append(",swiming pool");
        }
        if (cbFreeParking.isChecked()) {
            aminity.append(",free parking");
        }
        if (cbRoomService.isChecked()) {
            aminity.append(",room service");
        }
        if (cbbOutdorPool.isChecked()) {
            aminity.append(",out door poll");
        }
        if (!TextUtils.isEmpty(aminity)) {
            return aminity.substring(1, aminity.length());
        } else {
            return "";
        }
    }

    private void reset(boolean status) {
        cb1Star.setChecked(status);
        cb2Star.setChecked(status);
        cb3Star.setChecked(status);
        cb4Star.setChecked(status);
        cb5Star.setChecked(status);
        cbFarmHouse.setChecked(status);
        cbServiceApartment.setChecked(status);
        cbGuestHouse.setChecked(status);
        cbHomeStay.setChecked(status);
        cbHotel.setChecked(status);
        cbVillas.setChecked(status);
        cbPentHouse.setChecked(status);
        cbResort.setChecked(status);
        cbHostel.setChecked(status);
        cbPg.setChecked(status);
        cbBenuest.setChecked(status);
        cvAllAminity.setChecked(status);
        checkAllAminity(false);
    }

    public void checkAllAminity(boolean status) {
        cbCoupleFrendly.setChecked(status);
        cbWify.setChecked(status);
        cbSpa.setChecked(status);
        cbSwimingPool.setChecked(status);
        cbFreeParking.setChecked(status);
        cbRoomService.setChecked(status);
        cbbOutdorPool.setChecked(status);
    }

    @Override
    protected void bindDataWithUi() {
        if (!TextUtils.isEmpty(request.getAminity())) {
            String[] split = request.getAminity().split(",");
            for (String s : split) {
                bindAminity(s);
            }
        }
        if (!TextUtils.isEmpty(request.getStar())) {
            String[] split = request.getStar().split(",");
            for (String s : split) {
                bindStar(s);
            }
        }
        if (!TextUtils.isEmpty(request.getHotelType())) {
            String[] split = request.getHotelType().split(",");
            for (String s : split) {
                bindHotelType(s);
            }
        }
        if (!TextUtils.isEmpty(request.getMin())) {
            rbSeekBar.setSelectedMinValue(Integer.parseInt(request.getMin()));
        }
        if (!TextUtils.isEmpty(request.getMax())) {
            rbSeekBar.setSelectedMaxValue(Integer.parseInt(request.getMax()));
        }
    }

    private void bindHotelType(String s) {
        if (s.equalsIgnoreCase("5")) {
            cbFarmHouse.setChecked(true);
        }
        if (s.equalsIgnoreCase("6")) {
            cbServiceApartment.setChecked(true);
        }
        if (s.equalsIgnoreCase("7")) {
            cbGuestHouse.setChecked(true);
        }
        if (s.equalsIgnoreCase("8")) {
            cbHomeStay.setChecked(true);
        }
        if (s.equalsIgnoreCase("15")) {
            cbHotel.setChecked(true);
        }
        if (s.equalsIgnoreCase("10")) {
            cbVillas.setChecked(true);
        }
        if (s.equalsIgnoreCase("11")) {
            cbPentHouse.setChecked(true);
        }
        if (s.equalsIgnoreCase("12")) {
            cbResort.setChecked(true);
        }
        if (s.equalsIgnoreCase("13")) {
            cbHostel.setChecked(true);
        }
        if (s.equalsIgnoreCase("16")) {
            cbPg.setChecked(true);
        }
        if (s.equalsIgnoreCase("17")) {
            cbBenuest.setChecked(true);
        }
    }

    public void bindAminity(String aminity) {
        if (aminity.equalsIgnoreCase("couple friendly")) {
            cbCoupleFrendly.setChecked(true);
        }
        if (aminity.equalsIgnoreCase("wiffy")) {
            cbWify.setChecked(true);
        }
        if (aminity.equalsIgnoreCase("spa")) {
            cbSpa.setChecked(true);
        }
        if (aminity.equalsIgnoreCase("swiming pool")) {
            cbSwimingPool.setChecked(true);
        }
        if (aminity.equalsIgnoreCase("free parking")) {
            cbFreeParking.setChecked(true);
        }
        if (aminity.equalsIgnoreCase("room service")) {
            cbRoomService.setChecked(true);
        }
        if (aminity.equalsIgnoreCase("out door poll")) {
            cbbOutdorPool.setChecked(true);
        }
    }

    public void bindStar(String star) {
        if (star.equalsIgnoreCase("1")) {
            cb1Star.setChecked(true);
        }
        if (star.equalsIgnoreCase("2")) {
            cb2Star.setChecked(true);
        }
        if (star.equalsIgnoreCase("3")) {
            cb3Star.setChecked(true);
        }
        if (star.equalsIgnoreCase("4")) {
            cb4Star.setChecked(true);
        }
        if (star.equalsIgnoreCase("5")) {
            cb5Star.setChecked(true);
        }
    }
}
