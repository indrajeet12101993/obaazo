package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class ActivityBookRoom extends BaseActivity {
    HotelRoomResponse.ResultBean roomDetails;
    private ImageView ivRoomImage;
    private TextView tvHotelName;
    private TextView tvAddress;
    private HotelInfo hotelDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_room;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        roomDetails = getIntent().getParcelableExtra(AppConstant.BOOK_ROOM);
        hotelDetails = getIntent().getParcelableExtra(AppConstant.HOTEL_DETAILS);
        ivRoomImage = findViewById(R.id.iv_hotel_image);
        tvHotelName = findViewById(R.id.tv_hotel_name);
        tvAddress = findViewById(R.id.tv_address);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {
        if (roomDetails != null) {
            Picasso.get()
                    .load((!(TextUtils.isEmpty(roomDetails.getImage()))) ? roomDetails.getImage() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(size, size)
                    .centerInside()
                    .error(R.drawable.ic_place_holer)
                    .placeholder(R.drawable.ic_place_holer)
                    .into(ivRoomImage);



            tvHotelName.setText(hotelDetails.getHotel_name());
            tvAddress.setText(hotelDetails.getAddress());

        }


    }
}
