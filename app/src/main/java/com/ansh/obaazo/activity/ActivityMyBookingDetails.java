package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.BookingDetailsResponse;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.resources.service.BookingDetailsService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class ActivityMyBookingDetails extends BaseActivity implements OnMapReadyCallback {
    private BookingDetailsResponse.ResultBean mBookingDetails;
    private ImageView ivHotelImage;
    private String titleText = "";
    private GoogleMap mMap;

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

        //  ((TextView) findViewById(R.id.tv_title)).setText("Booking Details");
        MyBookingResponse.ResultBean tempDetails = getIntent().getParcelableExtra(AppConstant.MY_BOOKING);
        titleText = tempDetails.getHotel_name();
        ivHotelImage = findViewById(R.id.iv_hotel_image);
        initCustomToolbar();
        hitBookingDetailsApi(tempDetails.getBooking_id());
    }

    private void hitBookingDetailsApi(String bookingId) {
        showLoadingDialog();
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setId(bookingId);
        new BookingDetailsService(this).execute(baseRequest, new ApiCallback<BookingDetailsResponse>() {
            @Override
            public void onSuccess(Call<BookingDetailsResponse> call, BookingDetailsResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    if (response.getResult() != null) {
                        mBookingDetails = response.getResult();
                        bindDataWithUi();
                    } else {
                        onBackPressed();
                        Toast.makeText(ActivityMyBookingDetails.this, "Somethings went wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityMyBookingDetails.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivityMyBookingDetails.this, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public String setToolbarName() {
        return titleText;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {


        if (mBookingDetails != null) {
            Picasso.get()
                    .load(mBookingDetails.getImage1())
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .error(R.drawable.ic_hotel_place_holder)
                    .resize(200, 200)
                    .into(ivHotelImage);

            ((TextView) findViewById(R.id.tv_booking_id)).setText(mBookingDetails.getBooking_id());
            ((TextView) findViewById(R.id.tv_person_details)).setText(mBookingDetails.getAdult() + " Adult | " + mBookingDetails.getChild()
                    + " Child | " + mBookingDetails.getRoom_no() + " " + mBookingDetails.getRoomname());
            ((TextView) findViewById(R.id.tv_check_in_check_out_time)).setText(DateUtils.parseDate(mBookingDetails.getCheckin()) + " - " + DateUtils.parseDate(mBookingDetails.getCheckout()));
            ((TextView) findViewById(R.id.tv_hotel_name)).setText(mBookingDetails.getHotel_name());
            ((TextView) findViewById(R.id.tv_hotel_address)).setText(mBookingDetails.getAddress());

            ((TextView) findViewById(R.id.tv_guest_name)).setText(mBookingDetails.getUser_name());
            ((TextView) findViewById(R.id.tv_mobile)).setText(mBookingDetails.getUser_mobile());
            ((TextView) findViewById(R.id.tv_email)).setText(mBookingDetails.getUser_email());
            ((TextView) findViewById(R.id.tv_booking_amount)).setText(" ₹" + mBookingDetails.getBooking_amount());
            ((TextView) findViewById(R.id.tv_payment_option)).setText(mBookingDetails.getPayment_option());
            ((TextView) findViewById(R.id.tv_gst)).setText(" ₹" + mBookingDetails.getGst_value());
            ((TextView) findViewById(R.id.tv_total_amount)).setText(" ₹" + mBookingDetails.getFinal_amount());

            SupportMapFragment mGoogleMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mGoogleMap.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setAllGesturesEnabled(false);
        LatLng temp = new LatLng(Double.parseDouble(mBookingDetails.getLat()), Double.parseDouble(mBookingDetails.getLongg()));
        MarkerOptions markerOptions = new MarkerOptions()
                .position(temp);
        mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(temp, 10.0f));
    }
}
