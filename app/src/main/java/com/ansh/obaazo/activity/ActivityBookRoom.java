package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.ansh.obaazo.R;
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
        roomDetails = getIntent().getParcelableExtra(AppConstant.BOOK_ROOM);
        ivRoomImage = findViewById(R.id.iv_hotel_image);

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

        }


    }
}
