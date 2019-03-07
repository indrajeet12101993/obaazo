package com.ansh.obaazo.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
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

import androidx.core.app.NotificationCompat;
import retrofit2.Call;

public class ActivityMyBookingDetails extends BaseActivity implements OnMapReadyCallback {
    private BookingDetailsResponse.ResultBean mBookingDetails;
    private ImageView ivHotelImage;
    private String titleText = "";
    private GoogleMap mMap;
    private String INVOICE_URL = "https://obaazo.com/invoice/";
    private String tempUrl;
    private boolean newBooking = false;

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
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        //  ((TextView) findViewById(R.id.tv_title)).setText("Booking Details");
        MyBookingResponse.ResultBean tempDetails = getIntent().getParcelableExtra(AppConstant.MY_BOOKING);
        newBooking = getIntent().getBooleanExtra("NEW", false);
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
        findViewById(R.id.tv_download_invoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    downloadFile();
                } else {
                    selfPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
                }

            }
        });
    }


    @Override
    protected void bindDataWithUi() {


        if (mBookingDetails != null) {
            tempUrl = INVOICE_URL + mBookingDetails.getBooking_rand_id() + ".pdf";
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
            if (!TextUtils.isEmpty(mBookingDetails.getPayment_option())) {
                String paymentOption = !mBookingDetails.getPayment_option().equalsIgnoreCase("1") ? "Online" : "Pay At Hotel";
                ((TextView) findViewById(R.id.tv_payment_option)).setText(paymentOption);
            }
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


    private void downloadFile() {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(tempUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(true);
        request.setTitle("Obazzo" + "");
        request.setDescription("Downloading...");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/Obazzo/" + mBookingDetails.getBooking_rand_id() + ".pdf");
        if (downloadManager != null) {
            long refid = downloadManager.enqueue(request);
        }
    }


    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            Intent downloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
            Bitmap largeIcon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher);
            PendingIntent pendingIntent = PendingIntent.getActivity(ActivityMyBookingDetails.this, 0, downloadIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //   NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //  Notification.Builder notificationBuilder = new Notification.Builder(ActivityMyBookingDetails.this);


            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = "channel-01";
            String channelName = "Channel Name";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
                if (mNotificationManager != null)
                    mNotificationManager.createNotificationChannel(mChannel);
            }


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctxt, channelId);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
                notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
            } else {
                notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            }

            notificationBuilder
                    .setContentTitle(getResources().getString(R.string.app_name))
                    .setContentText("Download completed")
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(largeIcon)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);
            mNotificationManager.notify(1, notificationBuilder.build());
        }
    };


    @Override
    protected void storagePermissionGrant() {
        downloadFile();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onComplete);

    }

    @Override
    public void onBackPressed() {
        if (newBooking) {
            startActivity(new Intent(ActivityMyBookingDetails.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else {
            super.onBackPressed();
        }

    }
}
