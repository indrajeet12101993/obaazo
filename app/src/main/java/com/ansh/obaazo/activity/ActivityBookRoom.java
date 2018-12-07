package com.ansh.obaazo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AdapterCouponCode;
import com.ansh.obaazo.adapter.PriceRoomAdapter;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.payment.AvenuesParams;
import com.ansh.obaazo.payment.PaymentClient;
import com.ansh.obaazo.payment.PaymentWebView;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.CouponListResponse;
import com.ansh.obaazo.resources.service.CouponListService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;

public class ActivityBookRoom extends BaseActivity {
    private ImageView ivRoomImage;
    private TextView tvHotelName;
    private TextView tvAddress;
    private HotelInfo hotelDetails;
    private TextView tvCheckInCheckOutTime;
    private EditText etName;
    private EditText etEmail;
    private EditText etMobile;
    private UserDetails userDetails;
    private CheckBox cbCoprate;
    private EditText etGstNo;
    private EditText etCompanyName;
    private EditText etCompanyAddress;
    private RecyclerView rvCouponCode;
    private CardView cvCouponCode;
    private AdapterCouponCode adapterCouponCode;
    private CardView cvRoomList;
    private RecyclerView rvRoomList;
    private PriceRoomAdapter priceRoomAdapter;
    private ArrayList<BookingInfo> bookingInfos;

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
        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        userDetails = new Gson().fromJson(tempDetails, UserDetails.class);
        hotelDetails = getIntent().getParcelableExtra(AppConstant.HOTEL_DETAILS);
        bookingInfos = getIntent().getParcelableArrayListExtra(AppConstant.PERSON_DETAILS);
        ivRoomImage = findViewById(R.id.iv_hotel_image);
        tvHotelName = findViewById(R.id.tv_hotel_name);
        tvAddress = findViewById(R.id.tv_address);
        tvCheckInCheckOutTime = findViewById(R.id.tv_check_in_check_out_time);

        etName = findViewById(R.id.et_user_name);
        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile_no);
        cbCoprate = findViewById(R.id.cb_corpatate);
        etGstNo = findViewById(R.id.et_gst_no);
        etCompanyName = findViewById(R.id.et_company_name);
        etCompanyAddress = findViewById(R.id.et_company_address);

        cvCouponCode = findViewById(R.id.cv_coupon_code);
        rvCouponCode = findViewById(R.id.rv_coupon_code);
        rvCouponCode.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCouponCode.setNestedScrollingEnabled(false);
        adapterCouponCode = new AdapterCouponCode(this, new ArrayList<CouponListResponse.ResultBean>());
        rvCouponCode.setAdapter(adapterCouponCode);

        cvRoomList = findViewById(R.id.cv_room_list);
        rvRoomList = findViewById(R.id.rv_rooms_list);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        priceRoomAdapter = new PriceRoomAdapter(this, bookingInfos);
        rvRoomList.setAdapter(priceRoomAdapter);
        rvRoomList.setNestedScrollingEnabled(false);
        hitCouponCodeApi();
    }

    private void hitCouponCodeApi() {
        showLoadingDialog();
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setId(hotelDetails.getHotel_id());
        new CouponListService(this).execute(baseRequest, new ApiCallback<CouponListResponse>() {
            @Override
            public void onSuccess(Call<CouponListResponse> call, CouponListResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    if (response.getResult() != null && response.getResult().size() != 0) {
                        cvCouponCode.setVisibility(View.VISIBLE);
                        adapterCouponCode.setData(response.getResult());
                    } else {
                        cvCouponCode.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(ActivityBookRoom.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivityBookRoom.this, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initListener() {

        cbCoprate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                findViewById(R.id.ll_corparate).setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });


        findViewById(R.id.btn_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPayment();
            }
        });

    }

    private void initPayment() {
        PaymentClient client = new PaymentClient();
        Intent intent = new Intent(this, PaymentWebView.class);
        intent.putExtra(AvenuesParams.ACCESS_CODE, client.getAccessCode());
        intent.putExtra(AvenuesParams.MERCHANT_ID, client.getMerchantId());
        intent.putExtra(AvenuesParams.ORDER_ID, client.getOrderId());
        intent.putExtra(AvenuesParams.CURRENCY, client.getCurrencyType());
        intent.putExtra(AvenuesParams.AMOUNT, "10");
        intent.putExtra(AvenuesParams.REDIRECT_URL, client.getRedirectUrl());
        intent.putExtra(AvenuesParams.CANCEL_URL, client.getCancelUrl());
        startActivity(intent);
    }

    @Override
    protected void bindDataWithUi() {
        Picasso.get()
                .load((!(TextUtils.isEmpty(hotelDetails.getImage1()))) ? hotelDetails.getImage1() : null)
                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(200, 200)
                .centerInside()
                .error(R.drawable.ic_hotel_place_holder)
                .placeholder(R.drawable.ic_hotel_place_holder)
                .into(ivRoomImage);
        tvHotelName.setText(hotelDetails.getHotel_name());
        tvAddress.setText(hotelDetails.getAddress());
        tvCheckInCheckOutTime.setText(DateUtils.parseDate(PreferencesUtils.getString(AppConstant.START_DATE)) + " - " + DateUtils.parseDate(PreferencesUtils.getString(AppConstant.END_DATE)));

        //Person Details

        etName.setText(userDetails.getName());
        etEmail.setText(userDetails.getEmail());
        etMobile.setText(userDetails.getMobile());
    }


    public boolean isValidDetails() {
        if (TextUtils.isEmpty(etName.getText().toString()) || etName.getText().length() < 2) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etMobile.getText().toString()) || etMobile.getText().length() < 10) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString()) || etEmail.getText().length() < 2) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cbCoprate.isChecked()) {
            if (TextUtils.isEmpty(etGstNo.getText().toString()) || etGstNo.getText().length() < 2) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etCompanyName.getText().toString()) || etCompanyName.getText().length() < 2) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etCompanyAddress.getText().toString()) || etCompanyAddress.getText().length() < 2) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }

}
