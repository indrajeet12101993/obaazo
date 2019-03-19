package com.ansh.obaazo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.BuildConfig;
import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.AdapterCouponCode;
import com.ansh.obaazo.adapter.PriceRoomAdapter;
import com.ansh.obaazo.listener.ItemClickNotiffy;
import com.ansh.obaazo.listener.RItemListener;
import com.ansh.obaazo.model.AmountRequest;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.DiscountRequest;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.model.MBooking;
import com.ansh.obaazo.model.RoomDetailRequest;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.model.UserRequest;
import com.ansh.obaazo.payment.ServiceUtility;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.request.BookingRequest;
import com.ansh.obaazo.resources.response.CouponListResponse;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.resources.response.ObazoMoneyResponse;
import com.ansh.obaazo.resources.service.BookingService;
import com.ansh.obaazo.resources.service.CouponListService;
import com.ansh.obaazo.resources.service.ObaazoMoneyService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.getepay.getepay.PaymentResult;
import com.getepay.getepay.PaymentSDKActivity;
import com.getepay.getepay.Request;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;

public class ActivityBookRoom extends BaseActivity implements ItemClickNotiffy {
    private ImageView ivRoomImage;
    private TextView tvHotelName, tvRoomPriceWithoutGst, tvPayableAmount, tvRoomGstAmt, tvTotalSaving, tvCouponDiscount;
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
    private ArrayList<MBooking> mBookingsPriceList = new ArrayList<>();
    private AppCompatCheckBox cbObaazoMoney;
    Spinner spExpChekinTime;
    private Double tempObaazoMoney = 0.0;
    private Double obaazoMoney = 0.0;
    private int couponDiscountPer;
    private double maxDisAmt = 0.0;
    String couponName;
    ArrayList<RoomDetailRequest> roomDetails = new ArrayList<>();
    private DecimalFormat format = new DecimalFormat("#.##");
    private String mGstAmount;
    private String mFinalAmout = "";
    private String mRoomPriceWithoutGst = "";
    private String mCouponDiscount = "";
    private int mAdultCount = 0, mChildCount = 0;

    private String orderId = String.valueOf(ServiceUtility.randInt(0, 9999999));
    private String mTransactionNo = "";
    private PaymentResult paymentDetails;

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
        mBookingsPriceList = getIntent().getParcelableArrayListExtra(AppConstant.ROOM_INFO);
        ivRoomImage = findViewById(R.id.iv_hotel_image);
        tvHotelName = findViewById(R.id.tv_hotel_name);
        tvAddress = findViewById(R.id.tv_address);
        tvCheckInCheckOutTime = findViewById(R.id.tv_check_in_check_out_time);
        tvRoomPriceWithoutGst = findViewById(R.id.tv_room_price_without_gst);
        tvPayableAmount = findViewById(R.id.tv_total_amount);
        tvTotalSaving = findViewById(R.id.tv_total_saving);
        tvRoomGstAmt = findViewById(R.id.tv_room_gst_amt);
        etName = findViewById(R.id.et_user_name);
        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile_no);
        cbCoprate = findViewById(R.id.cb_corpatate);
        etGstNo = findViewById(R.id.et_gst_no);
        etCompanyName = findViewById(R.id.et_company_name);
        etCompanyAddress = findViewById(R.id.et_company_address);
        cbObaazoMoney = findViewById(R.id.cb_obazzo_money);
        tvCouponDiscount = findViewById(R.id.tv_coupon_discount);

        cvCouponCode = findViewById(R.id.cv_coupon_code);
        rvCouponCode = findViewById(R.id.rv_coupon_code);
        spExpChekinTime = findViewById(R.id.sp_exp_chekin_time);
        rvCouponCode.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCouponCode.setNestedScrollingEnabled(false);
        adapterCouponCode = new AdapterCouponCode(this, new ArrayList<CouponListResponse.ResultBean>(), new RItemListener<CouponListResponse.ResultBean>() {
            @Override
            public void onItemClick(CouponListResponse.ResultBean item, int position) {
                if (item.isSelected()) {
                    couponName = item.getCoupon_name();
                    if (!TextUtils.isEmpty(item.getCoupon_percent()) && TextUtils.isDigitsOnly(item.getCoupon_percent()))
                        couponDiscountPer = Integer.parseInt(item.getCoupon_percent());
                    else
                        Toast.makeText(ActivityBookRoom.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    maxDisAmt = Double.parseDouble(item.getMax_discount());
                    calculateAmmount();
                } else {
                    couponDiscountPer = 0;
                    maxDisAmt = 0.0;
                    couponName = "";
                    calculateAmmount();
                }
            }
        });
        rvCouponCode.setAdapter(adapterCouponCode);

        cvRoomList = findViewById(R.id.cv_room_list);
        rvRoomList = findViewById(R.id.rv_rooms_list);
        rvRoomList.setLayoutManager(new LinearLayoutManager(this));
        priceRoomAdapter = new PriceRoomAdapter(this, mBookingsPriceList, this);
        rvRoomList.setAdapter(priceRoomAdapter);
        rvRoomList.setNestedScrollingEnabled(false);
        hitCouponCodeApi();
        hitObaazoMoneyApi(userDetails.getId());


        for (int i = 0; i < bookingInfos.size(); i++) {
            for (int j = 0; j < bookingInfos.get(i).getPersonInfos().size(); j++) {
                mAdultCount = mAdultCount + bookingInfos.get(i).getPersonInfos().get(j).getNoOfAdult();
                mChildCount = mChildCount + bookingInfos.get(i).getPersonInfos().get(j).getChild().size();
            }
        }
        bindPersonData(priceRoomAdapter.getItemCount(), mAdultCount, mChildCount);
    }


    public void bindPersonData(int roomCount, int adultCount, int childCount) {
        ((TextView) findViewById(R.id.tv_room_count)).setText("" + roomCount);
        ((TextView) findViewById(R.id.tv_adult_count)).setText("" + adultCount);
        ((TextView) findViewById(R.id.tv_child_count)).setText("" + childCount);

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
                if (isValidDetails()) {
                    initOnlinePayment();
                }

            }
        });

        findViewById(R.id.btn_pay_at_hotel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidDetails()) {
                    initPayment("1");
                }

            }
        });


        cbObaazoMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    obaazoMoney = tempObaazoMoney /** 5 / 100*/;
                } else {
                    obaazoMoney = 0.0;
                }
                calculateAmmount();
            }
        });

    }

    private void initOnlinePayment() {
        // final String agentId = "1";
        final String agentId = "5";
        final String merchantOrderNo = orderId;

        final String txnAmount = BuildConfig.DEBUG ? "1" : mFinalAmout;
        final String agentName = "Obaazo";
        final String udf1 = etMobile.getText().toString();
        final String udf2 = etEmail.getText().toString();
        final String udf3 = etName.getText().toString();
        final String udf4 = "udf4";
        final String udf5 = "udf5";
        //Obaazo@2019
        final String password = "f403b6e381524cfcc0d9cc62fa747e5f";
        // final String password = "f925916e2754e5e03f75dd58a5733251";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tft = sdf.format(new Date());
        final String txndatetime = tft;


        Intent intent = new Intent(getApplicationContext(), PaymentSDKActivity.class);
        Request request = new Request(agentId, merchantOrderNo, txnAmount, agentName, udf1, udf2, udf3, udf4, udf5, txndatetime, password);
        intent.putExtra("request", request);
        startActivityForResult(intent, 1999);
    }


    private void initPayment(String mode) {
        showLoadingDialog();
        BookingRequest request = new BookingRequest();
        UserRequest user = new UserRequest();
        AmountRequest amountRequest = new AmountRequest();
        DiscountRequest discountRequest = new DiscountRequest();
        user.setUserId(userDetails.getId());
        user.setName(etName.getText().toString());
        user.setEmail(etEmail.getText().toString());
        user.setMobile(etMobile.getText().toString());
        user.setGuestTime(spExpChekinTime.getSelectedItem().toString());

        amountRequest.setCheckIndate(PreferencesUtils.getString(AppConstant.START_DATE));
        amountRequest.setCheckOutDate(PreferencesUtils.getString(AppConstant.END_DATE));
        amountRequest.setOverAllGst(mGstAmount);
        amountRequest.setFinalAmount(mFinalAmout);
        amountRequest.setBookingAmount(mRoomPriceWithoutGst);
        amountRequest.setPaymentOption(mode);


        discountRequest.setCouponDiscount(mCouponDiscount);
        discountRequest.setCouponName(couponName);
        discountRequest.setObaazoUsed(obaazoMoney + "");
        discountRequest.setReward(tempObaazoMoney + "");
        request.setPaymentResult(paymentDetails);

        for (MBooking booking : mBookingsPriceList) {
            RoomDetailRequest roomDetailRequest = new RoomDetailRequest();
            roomDetailRequest.setHotelId(booking.getHotelId() + "");
            roomDetailRequest.setRoomId(booking.getRoomId() + "");
            // roomDetailRequest.setHotelId("1065");
            //  roomDetailRequest.setRoomId("198");
            roomDetailRequest.setAdult(booking.getAdultCount() + "");
            roomDetailRequest.setChild(booking.getChildCount() + "");
            roomDetailRequest.setAdultPrice(booking.getAdultPrice() + "");
            roomDetailRequest.setChildPrice(booking.getChildPrice() + "");
            roomDetailRequest.setTotalPrice(booking.getRoomPriceWithoutGst() + "");
            roomDetailRequest.setRoomNo("1");
            roomDetails.add(roomDetailRequest);
        }

        request.setRequest(user);
        request.setAmountRequest(amountRequest);
        request.setDiscountRequest(discountRequest);
        request.setRoomDetails(roomDetails);
        Log.e("", "initPayment: ");
        new BookingService(this).execute(request, new ApiCallback<MyBookingResponse>() {
            @Override
            public void onSuccess(Call<MyBookingResponse> call, MyBookingResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    startActivity(new Intent(ActivityBookRoom.this, ActivityMyBookingDetails.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("NEW", true)
                            .putExtra(AppConstant.MY_BOOKING, response.getResult().get(0)));
                }
                Toast.makeText(ActivityBookRoom.this, response.getResponse_message(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(ActivityBookRoom.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



       /* PaymentClient client = new PaymentClient();

    }

/*    private void initPayment() {
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


        Intent newPayIntent = new Intent(this, PayActivity.class);

        newPayIntent.putExtra("merchantId", "197");
        newPayIntent.putExtra("txnscamt", "0"); //Fixed. Must be 0
        newPayIntent.putExtra("loginid", "197");
        newPayIntent.putExtra("password", "Test@123");
        newPayIntent.putExtra("prodid", "NSE");
        newPayIntent.putExtra("txncurr", "INR"); //Fixed. Must be ?INR?
        newPayIntent.putExtra("clientcode", "001");
        newPayIntent.putExtra("custacc", "100000036600");
         newPayIntent.putExtra("amt", "100");//Should be 3 decimal number i.e51.000
        newPayIntent.putExtra("txnid", "013");
        newPayIntent.putExtra("date", "25/08/2015 18:31:00");//Should be in same format
        newPayIntent.putExtra("bankid", "2001"); //Should be valid bank id // Optional
        newPayIntent.putExtra("discriminator", "IMPS"); //NB or IMPS or All ONLY (value should be same as commented)
        newPayIntent.putExtra("signature_request","KEY123657234");
        newPayIntent.putExtra("signature_response", "KEYRESP123657234");


        //use below Production url only with Production "Library-MobilePaymentSDK", Located inside PROD folder
        //newPayIntent.putExtra("ru","https://payment.atomtech.in/mobilesdk/param"); //ru FOR Production

        //use below UAT url only with UAT "Library-MobilePaymentSDK", Located inside UAT folder
        newPayIntent.putExtra("ru", "https://paynetzuat.atomtech.in/mobilesdk/param"); // FOR UAT (Testing)

        //Optinal Parameters
        newPayIntent.putExtra("customerName", "JKL PQR"); //Only for Name
        newPayIntent.putExtra("customerEmailID", "jkl.pqr@atomtech.in");//Only for Email ID
        newPayIntent.putExtra("customerMobileNo", "9876543210");//Only for Mobile Number
        newPayIntent.putExtra("billingAddress", "Mumbai");//Only for Address
        newPayIntent.putExtra("optionalUdf9", "OPTIONAL DATA 1");// Can pass any data
        //   newPayIntent.putExtra("mprod", mprod); // Pass data in XML format, only for Multi product

        startActivityForResult(newPayIntent, 1);

    }
        startActivity(intent);*/
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
        calculateAmmount();
    }

    public void calculateAmmount() {
        Double roomAmt = 0.0;
        Double gstAmt = 0.0;
        Double discountCoupon = 0.0;
        //   Double couponDiscount = 0.0;
        for (int i = 0; i < mBookingsPriceList.size(); i++) {
            roomAmt += mBookingsPriceList.get(i).getRoomPriceWithoutGst();
            gstAmt += mBookingsPriceList.get(i).getRoomGstPrice();
        }
        if (mBookingsPriceList.size() == 0) {
            maxDisAmt = 0.0;
            obaazoMoney = 0.0;
            cbObaazoMoney.setChecked(false);
            adapterCouponCode.resetData();
            Toast.makeText(this, "Please select at least one room", Toast.LENGTH_SHORT).show();
        }

        discountCoupon = roomAmt * couponDiscountPer / 100;
        if (discountCoupon > maxDisAmt) {
            discountCoupon = maxDisAmt;
        }

        tvRoomPriceWithoutGst.setText("₹ " + roomAmt);
        ((TextView) findViewById(R.id.tv_obaazo_money_receive)).setText("₹ " + (roomAmt - getDisountPrice(roomAmt)));
        mRoomPriceWithoutGst = "" + roomAmt;
        tvRoomGstAmt.setText("₹ " + gstAmt);
        mGstAmount = "" + gstAmt;
        ((TextView) findViewById(R.id.tv_use_obaazo)).setText(format.format(obaazoMoney) + "");
        mFinalAmout = format.format((roomAmt + gstAmt - (obaazoMoney + discountCoupon)));
        tvPayableAmount.setText(" ₹ " + format.format((roomAmt + gstAmt - (obaazoMoney + discountCoupon))));
        tvTotalSaving.setText("₹ " + format.format(obaazoMoney + discountCoupon));
        mCouponDiscount = format.format(discountCoupon);
        tvCouponDiscount.setText("₹ " + format.format(discountCoupon));
    }


    public boolean isValidDetails() {
        if (TextUtils.isEmpty(etName.getText().toString()) || etName.getText().length() < 2) {
            Toast.makeText(this, "Enter valid  Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etMobile.getText().toString()) || etMobile.getText().length() < 10) {
            Toast.makeText(this, "Enter valid Mobile No", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches()) {
            Toast.makeText(this, "enter valid Email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cbCoprate.isChecked()) {
            if (TextUtils.isEmpty(etGstNo.getText().toString()) || etGstNo.getText().length() < 2) {
                Toast.makeText(this, "Enter GST No", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etCompanyName.getText().toString()) || etCompanyName.getText().length() < 2) {
                Toast.makeText(this, "Enter Company Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etCompanyAddress.getText().toString()) || etCompanyAddress.getText().length() < 2) {
                Toast.makeText(this, "Enter Company address", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (spExpChekinTime.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please selected expected Check In Time", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void hitObaazoMoneyApi(String id) {
        showLoadingDialog();

        BaseRequest baseRequest = new BaseRequest();
        //  baseRequest.setId(PreferencesUtils.getString(AppConstant.USER_ID));
        baseRequest.setId(id);
        new ObaazoMoneyService(this).execute(baseRequest, new ApiCallback<ObazoMoneyResponse>() {
            @Override
            public void onSuccess(Call<ObazoMoneyResponse> call, ObazoMoneyResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    if (response.getResult() != null) {
                        ObazoMoneyResponse.ResultBean resultBean = response.getResult();
                        tempObaazoMoney = resultBean.getMoney() * 5 / 100;
                        ((TextView) findViewById(R.id.tv_obaazo_money)).setText("₹ " + format.format(tempObaazoMoney));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data != null) {
                String message = data.getStringExtra("status");
                String[] resKey = data.getStringArrayExtra("responseKeyArray");
                String[] resValue = data.getStringArrayExtra("responseValueArray");

                if (resKey != null && resValue != null) {
                    for (int i = 0; i < resKey.length; i++)
                        System.out.println("  " + i + " resKey : " + resKey[i] + " resValue : " + resValue[i]);
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            }
        } else if (requestCode == 1999) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentResult response = (PaymentResult) data.getSerializableExtra("response");
                Log.e("Payment Response", "onActivityResult: " + new Gson().toJson(response));
                if (response.getStatus() != null && response.getStatus().equalsIgnoreCase("true")
                        && response.getMessage() != null && response.getMessage().equalsIgnoreCase("Success")) {
                    paymentDetails = response;
                    initPayment("2");
                } else {
                    Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onItemClick(int position) {
        calculateAmmount();
        //bindDataWithUi();
    }

    private Double getDisountPrice(Double startPrice) {
        Double tempPrice = startPrice;
        if (startPrice != 0) {
            if (PreferencesUtils.getString(AppConstant.USER_CATEGORY).equalsIgnoreCase(AppConstant.OLD_USER)
                    && PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                tempPrice = tempPrice - (tempPrice * 10 / 100);
            } else {
                tempPrice = tempPrice - (tempPrice * 20 / 100);
            }
        }
        return tempPrice;
    }
}
