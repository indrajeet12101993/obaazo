package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;

import org.florescu.android.rangeseekbar.RangeSeekBar;


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

        findViewById(R.id.cb_all_aminity);

    }

    @Override
    protected void initListener() {

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


            }
        });


    }

    private String getHotelType() {
        if (cbFarmHouse.isChecked()) {
            hotelType.append(",5");
        } else if (cbServiceApartment.isChecked()) {
            hotelType.append(",6");
        } else if (cbGuestHouse.isChecked()) {
            hotelType.append(",7");
        } else if (cbHomeStay.isChecked()) {
            hotelType.append(",8");
        } else if (cbHotel.isChecked()) {
            hotelType.append(",15");
        } else if (cbVillas.isChecked()) {
            hotelType.append(",10");
        } else if (cbPentHouse.isChecked()) {
            hotelType.append(",11");
        } else if (cbResort.isChecked()) {
            hotelType.append(",12");
        } else if (cbHostel.isChecked()) {
            hotelType.append(",13");
        } else if (cbPg.isChecked()) {
            hotelType.append(",16");
        } else if (cbBenuest.isChecked()) {
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
        } else if (cb2Star.isChecked()) {
            star.append(",2");
        } else if (cb3Star.isChecked()) {
            star.append(",3");
        } else if (cb4Star.isChecked()) {
            star.append(",4");
        } else if (cb5Star.isChecked()) {
            star.append(",5");
        }
        if (!TextUtils.isEmpty(star)) {
            return star.substring(1, star.length());
        } else {
            return "";
        }
    }

    @Override
    protected void bindDataWithUi() {

    }
}
