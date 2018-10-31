package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.utils.AppConstant;
import com.squareup.picasso.Picasso;

public class ActivityMyBookingDetails extends BaseActivity {
    private MyBookingResponse.ResultBean mBookingDetails;
    private ImageView ivHotelImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_booking_details;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        ((TextView) findViewById(R.id.tv_title)).setText("Booking Details");
        mBookingDetails = getIntent().getParcelableExtra(AppConstant.MY_BOOKING);
        ivHotelImage = findViewById(R.id.iv_hotel_image);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {
        if (mBookingDetails != null) {
            Picasso.get()
                    .load(mBookingDetails.getImage1())
                    .into(ivHotelImage);
        } else {
            Toast.makeText(this, "Somethings wen wrong", Toast.LENGTH_SHORT).show();
        }

    }
}
