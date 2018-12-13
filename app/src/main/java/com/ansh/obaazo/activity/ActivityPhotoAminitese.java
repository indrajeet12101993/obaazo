package com.ansh.obaazo.activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AdapterRoomImage;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.resources.service.HotelImageService;
import com.ansh.obaazo.utils.FlowLayout;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import retrofit2.Call;

public class ActivityPhotoAminitese extends BaseActivity {

    private String hotelId;
    private RecyclerView rvRoomImages;
    private AdapterRoomImage galleryAdapter;
    private String amin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_aminitese;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        hotelId = getIntent().getStringExtra("HOTEL_ID");
        amin = getIntent().getStringExtra("AMIN");

        rvRoomImages = findViewById(R.id.rv_hotel_gallery);
        rvRoomImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRoomImages.setNestedScrollingEnabled(false);
        galleryAdapter = new AdapterRoomImage(this, new HotelImageResponse());
        rvRoomImages.setAdapter(galleryAdapter);
        hitHotelGalleryApi();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

        FlowLayout flowLayout = findViewById(R.id.fl_amin);
        if (!TextUtils.isEmpty(amin)) {
            String[] split = amin.split(",");
            for (String aSplit : split) {
                TextView textView = new TextView(this);
                textView.setText(aSplit);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                textView.setLayoutParams(params);
                textView.setBackgroundResource(R.drawable.shape_button_cyan);
                textView.setTextColor(Color.WHITE);
                textView.setPadding(20, 10, 20, 10);
                flowLayout.addView(textView);
            }
        }
        //((TextView) findViewById(R.id.tv_aminity)).setText(amin);
    }

    private void hitHotelGalleryApi() {
        showLoadingDialog();
        BaseRequest request = new BaseRequest();
        request.setId(hotelId);
        new HotelImageService(this).execute(request, new ApiCallback<HotelImageResponse>() {
            @Override
            public void onSuccess(Call<HotelImageResponse> call, HotelImageResponse response) {
                if (response != null && response.getResult() != null) {
                    galleryAdapter.setmHotelImage(response);
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

}
