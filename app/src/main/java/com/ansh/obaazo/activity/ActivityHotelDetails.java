package com.ansh.obaazo.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AmentiesAdapter;
import com.ansh.obaazo.adapter.HotelGallaryAdapter;
import com.ansh.obaazo.adapter.ReviewAdapter;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.resources.response.HotelReviewResponse;
import com.ansh.obaazo.resources.service.HotelImageService;
import com.ansh.obaazo.resources.service.HotelReviewService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.ExpandableTextView;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.ansh.obaazo.widget.AminityDialog;
import com.ansh.obaazo.widget.SimpleDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;

import static android.view.View.INVISIBLE;
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

    //  private TextView tvBookingDates;
    private TextView tvNoOfRoom, tvNoOfAdult, tvNoOfChild;
    private RecyclerView rvAmenties;
    private AmentiesAdapter amentiesAdapter;
    private RecyclerView rvHotelGallery;
    private HotelGallaryAdapter galleryAdapter;
    private ReviewAdapter reviewAdapter;
    private TextView tvDates;
    private String tempDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //review for totlal no of review  120 review
    //Rating for total no of rating   4.5/5

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotel_details;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        bindIntentData();


        tvDates = findViewById(R.id.tv_dates);
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
        reviewAdapter = new ReviewAdapter(this, new ArrayList<HotelReviewResponse.ResultBean>());
        rvReview.setAdapter(reviewAdapter);
        rvReview.setNestedScrollingEnabled(false);

        hitHotelReviewApi();

    }

    private void hitHotelReviewApi() {
        showLoadingDialog();
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setId(hotelDetails.getId());
        //  baseRequest.setId("99");
        new HotelReviewService(this).execute(baseRequest, new ApiCallback<HotelReviewResponse>() {
            @Override
            public void onSuccess(Call<HotelReviewResponse> call, HotelReviewResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    reviewAdapter.setmList(response.getResult());
                    if (response.getResult() != null && response.getResult().size() != 0) {
                        findViewById(R.id.tv_no_review).setVisibility(View.GONE);
                    } else {
                        findViewById(R.id.tv_no_review).setVisibility(View.VISIBLE);
                    }
                } else {
                    // Toast.makeText(ActivityHotelDetails.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivityHotelDetails.this, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
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
                if (hotelDetails.isAvailable()) {
                    startActivity(new Intent(ActivityHotelDetails.this, SelectRoomActivity.class)
                            .putExtra(AppConstant.HOTEL_ID, hotelDetails.getHotel_id())
                            .putExtra(AppConstant.HOTEL_DETAILS, hotelDetails));
                }
            }
        });

        findViewById(R.id.tv_standard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String s = hotelDetails.getCancellation().trim().replaceAll("\\s{2,}", " ");
                new SimpleDialog(ActivityHotelDetails.this, "STANDARD CANCELLATION POLICY", hotelDetails.getCancellation()).show();
            }
        });


        findViewById(R.id.tv_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleDialog(ActivityHotelDetails.this, "TERMS AND CONDITIONS", hotelDetails.getAdmission()).show();
            }
        });

        tvDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   startActivityForResult(new Intent(ActivityHotelDetails.this, ActivityDateSelecte.class), 1003);
            }
        });
    }

    @Override
    protected void bindDataWithUi() {
        if (hotelDetails != null) {
            bindDateData();
            //   tvBookingDates.setText(bookingDetails);
            tvNoOfRoom.setText("" + noOfRoom);
            tvNoOfAdult.setText("" + noOfAdult);
            tvNoOfChild.setText("" + noOfChild);


            Picasso.get()
                    .load((!(TextUtils.isEmpty(hotelDetails.getImage1()))) ? hotelDetails.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(size, size)
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .into(((ImageView) findViewById(R.id.iv_banner_image)));


            ((TextView) findViewById(R.id.tv_hotel_name)).setText(hotelDetails.getHotel_name());
            if (!TextUtils.isEmpty(hotelDetails.getStartFrom()) && !hotelDetails.getStartFrom().equalsIgnoreCase("0")) {
                ((TextView) findViewById(R.id.tv_price)).setText("₹ " + hotelDetails.getStartFrom());
                ((TextView) findViewById(R.id.tv_discount_price)).setText("₹ " + getDisountPrice(hotelDetails.getStartFrom()));
                ((TextView) findViewById(R.id.tv_price)).setPaintFlags(((TextView) findViewById(R.id.tv_price)).getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                findViewById(R.id.tv_not_avi).setVisibility(View.INVISIBLE);
                ((Button) findViewById(R.id.btn_select_room)).setEnabled(true);
                ((Button) findViewById(R.id.btn_select_room)).setText("Select Room");

            } else {
                findViewById(R.id.tv_price).setVisibility(INVISIBLE);
                findViewById(R.id.tv_lbl_start).setVisibility(INVISIBLE);
                findViewById(R.id.tv_not_avi).setVisibility(View.VISIBLE);
                ((Button) findViewById(R.id.btn_select_room)).setEnabled(false);
                ((Button) findViewById(R.id.btn_select_room)).setText("Sold Out");

            }

            ((RatingBar) findViewById(R.id.rb_hotel_rating)).setRating(hotelDetails.getHotelrating());
            (findViewById(R.id.tv_rating)).setVisibility(TextUtils.isEmpty(hotelDetails.getRating()) ? View.GONE : View.VISIBLE);
            ((TextView) findViewById(R.id.tv_rating)).setText(hotelDetails.getRating() + "/5");
            ((ExpandableTextView) findViewById(R.id.tv_hotel_details)).setText(hotelDetails.getTour_policy());
            amentiesAdapter.setmData(hotelDetails.getHotel_amenties1());
            bindReviewData();
        }
        hitHotelGalleryApi();
    }

    private void bindReviewData() {
        ((TextView) findViewById(R.id.tv_box_rating)).setText(hotelDetails.getRating() + "/5");
        ((TextView) findViewById(R.id.tv_box_no_review)).setText(hotelDetails.getReview() + " Review");

        ((ProgressBar) findViewById(R.id.pb_5star)).setProgress(hotelDetails.getRatingStart5());
        ((ProgressBar) findViewById(R.id.pb_4star)).setProgress(hotelDetails.getRatingStart4());
        ((ProgressBar) findViewById(R.id.pb_3star)).setProgress(hotelDetails.getRatingStart3());
        ((ProgressBar) findViewById(R.id.pb_2star)).setProgress(hotelDetails.getRatingStart2());
        ((ProgressBar) findViewById(R.id.pb_1star)).setProgress(hotelDetails.getRatingStart1());

        ((TextView) findViewById(R.id.tv_5star)).setText(hotelDetails.getRatingStart5() + "");
        ((TextView) findViewById(R.id.tv_4star)).setText(hotelDetails.getRatingStart4() + "");
        ((TextView) findViewById(R.id.tv_3star)).setText(hotelDetails.getRatingStart3() + "");
        ((TextView) findViewById(R.id.tv_2star)).setText(hotelDetails.getRatingStart2() + "");
        ((TextView) findViewById(R.id.tv_1star)).setText(hotelDetails.getRatingStart1() + "");

        LinearLayout boxRate = findViewById(R.id.ll_shape_box_rating);
        double v = Double.parseDouble(hotelDetails.getRating());
        switch ((int) v) {
            case 1:
                boxRate.setBackgroundResource(R.drawable.shape_orange);
                break;
            case 2:
                boxRate.setBackgroundResource(R.drawable.shape_light_orange);
                break;
            case 3:
                boxRate.setBackgroundResource(R.drawable.shape_fade_green);
                break;
            case 4:
                boxRate.setBackgroundResource(R.drawable.shape_light_green);
                break;
            case 5:
                boxRate.setBackgroundResource(R.drawable.shape_green);
                break;
            default:
                boxRate.setBackgroundResource(R.drawable.shape_orange);
        }

    }

    private Double getDisountPrice(String startPrice) {
        Double tempPrice = Double.parseDouble(startPrice);
        if (!TextUtils.isEmpty(startPrice)) {
            if (PreferencesUtils.getString(AppConstant.USER_CATEGORY).equalsIgnoreCase(AppConstant.OLD_USER)
                    && PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                tempPrice = tempPrice - (tempPrice * 10 / 100);
            } else {
                tempPrice = tempPrice - (tempPrice * 20 / 100);
            }
        }
        return tempPrice;
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

    private void bindDateData() {
        startDate = PreferencesUtils.getString(AppConstant.START_DATE);
        endDate = PreferencesUtils.getString(AppConstant.END_DATE);
        Calendar calStart = DateUtils.formatDate(startDate);
        Calendar calEnd = DateUtils.formatDate(endDate);
        if (calStart != null && calEnd != null) {
            tempDates = calStart.get(Calendar.DATE) + " " + DateUtils.parseMonth(calStart.get(Calendar.MONTH)) + " - " +
                    calEnd.get(Calendar.DATE) + " " + DateUtils.parseMonth(calEnd.get(Calendar.MONTH));
            tvDates.setText(tempDates);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bindDateData();
        }
    }
}
