package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AmentiesAdapter;
import com.ansh.obaazo.adapter.HotelGallaryAdapter;
import com.ansh.obaazo.adapter.ReviewAdapter;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.resources.service.HotelImageService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.ansh.obaazo.widget.AminityDialog;
import com.ansh.obaazo.widget.SimpleDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class ActivityHotelDetails extends BaseActivity {
    private HotelInfo hotelDetails;
    private String startDate = "";
    private String endDate = "";
    private String bookingDetails = "";
    private int noOfChild = 0;
    private int noOfAdult = 0;
    private int noOfRoom = 0;

    private TextView tvBookingDates, tvNoOfRoom, tvNoOfAdult, tvNoOfChild;
    private RecyclerView rvAmenties;
    private AmentiesAdapter amentiesAdapter;
    private RecyclerView rvHotelGallery;
    private HotelGallaryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotel_details;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        bindIntentData();


        tvBookingDates = findViewById(R.id.tv_dates);
        tvNoOfRoom = findViewById(R.id.tv_no_of_rooms);
        tvNoOfAdult = findViewById(R.id.tv_no_of_adult);
        tvNoOfChild = findViewById(R.id.tv_no_of_child);
        rvAmenties = findViewById(R.id.rv_amenties);
        rvAmenties.setLayoutManager(new GridLayoutManager(this, 3));
        amentiesAdapter = new AmentiesAdapter(this, new String[]{});
        rvAmenties.setAdapter(amentiesAdapter);
        rvAmenties.setNestedScrollingEnabled(false);

        rvHotelGallery = findViewById(R.id.rv_hotel_gallery);
        rvHotelGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHotelGallery.setNestedScrollingEnabled(false);
        galleryAdapter = new HotelGallaryAdapter(this, new HotelImageResponse(), hotelDetails.getHotel_name());
        rvHotelGallery.setAdapter(galleryAdapter);

        RecyclerView rvReview = findViewById(R.id.rv_review);
        rvReview.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, new ArrayList<String>());
        rvReview.setAdapter(reviewAdapter);
        rvReview.setNestedScrollingEnabled(false);

    }

    private void bindIntentData() {
        hotelDetails = getIntent().getParcelableExtra(AppConstant.HOTEL_DETAILS);
        startDate = getIntent().getStringExtra(AppConstant.START_DATE);
        endDate = getIntent().getStringExtra(AppConstant.END_DATE);
        bookingDetails = getIntent().getStringExtra(AppConstant.BOOKING_DATES);
        noOfAdult = getIntent().getIntExtra(AppConstant.NO_OF_ADULT, 0);
        noOfChild = getIntent().getIntExtra(AppConstant.NO_OF_CHILD, 0);
        noOfRoom = getIntent().getIntExtra(AppConstant.NO_OF_ROOM, 0);

    }

    @Override
    protected void initListener() {

        findViewById(R.id.tv_view_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AminityDialog(ActivityHotelDetails.this, "Amenities", hotelDetails.getHotel_amenties1().split(",")).show();
            }
        });


        findViewById(R.id.btn_select_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityHotelDetails.this, SelectRoomActivity.class)
                        .putExtra(AppConstant.HOTEL_ID, hotelDetails.getHotel_id())
                        .putExtra(AppConstant.HOTEL_DETAILS, hotelDetails));
            }
        });

        findViewById(R.id.tv_standard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleDialog(ActivityHotelDetails.this, "STANDARD CANCELLATION POLICY", hotelDetails.getCancellation()).show();
            }
        });

        /*findViewById(R.id.tv_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleDialog(ActivityHotelDetails.this, "Question", hotelDetails.getTour_policy()).show();
            }
        });*/

        findViewById(R.id.tv_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleDialog(ActivityHotelDetails.this, "TERMS AND CONDITIONS", hotelDetails.getAdmission()).show();
            }
        });

        tvBookingDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  TopSheetDialog dialog = new TopSheetDialog(ActivityHotelDetails.this);
                dialog.setContentView(R.layout.sheet_content);
                dialog.show();*/
            }
        });

    }

    @Override
    protected void bindDataWithUi() {
        if (hotelDetails != null) {
            tvBookingDates.setText(bookingDetails);
            tvNoOfRoom.setText("" + noOfRoom);
            tvNoOfAdult.setText("" + noOfAdult);
            tvNoOfChild.setText("" + noOfChild);

            Picasso.get()
                    .load((!(TextUtils.isEmpty(hotelDetails.getImage1()))) ? hotelDetails.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(size, size)
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    /* .placeholder(R.drawable.ani_loader)
                     .error(R.drawable.ani_loader)*/
                    .into(((ImageView) findViewById(R.id.iv_banner_image)));


            ((TextView) findViewById(R.id.tv_hotel_name)).setText(hotelDetails.getHotel_name());
            ((TextView) findViewById(R.id.tv_price)).setText("₹ " + hotelDetails.getHotel_actual_price());
            ((RatingBar) findViewById(R.id.rb_hotel_rating)).setRating(Float.parseFloat((TextUtils.isEmpty(hotelDetails.getRating()) ? "0.0" : hotelDetails.getRating())));
            ((TextView) findViewById(R.id.tv_rating)).setText(hotelDetails.getRating() + "/5");
            ((TextView) findViewById(R.id.tv_hotel_details)).setText(hotelDetails.getTour_policy());


            amentiesAdapter.setmData(hotelDetails.getHotel_amenties1());
        }
        hitHotelGalleryApi();
        //  hitAmenityApi();

    }


    private void hitHotelGalleryApi() {
        showLoadingDialog();
        BaseRequest request = new BaseRequest();
        request.setId(hotelDetails.getHotel_id());
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
