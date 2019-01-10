package com.ansh.obaazo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.PersistableBundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;

import com.ansh.obaazo.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 110;
    private static final String TAG = BaseActivity.class.getSimpleName();
    public static final int REQUEST_CODE_LOCATION_SETTING = 103;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 120000;
    //  private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 60 * 2 * 1000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    //   private FirebaseRemoteConfig mFirebaseRemoteConfig;
    long cacheExpiration = 3600;
    private ProgressDialog mProgressDialog;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;


   /* @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

    public static void updateCurrentLocation(Location location) {
        if (location == null) return;
        Log.e(TAG, "updateCurrentLocation: " + "Lat : " + location.getLatitude() + "  Lng : " + location.getLongitude());
        //  PreferenceUtils.putString(KEY_LATITUDE, String.valueOf(location.getLatitude()));
        //PreferenceUtils.putString(KEY_LONGITUDE, String.valueOf(location.getLongitude()));
    }

  /*  public static String getLatitude() {
        return PreferenceUtils.getString(KEY_LATITUDE, "0");
    }*/

   /* public static Double getLat() {
        return Double.parseDouble(PreferenceUtils.getString(KEY_LATITUDE, "0"));
    }*/

   /* public static Double getLong() {
        return Double.parseDouble(PreferencesUtils(this).getString(KEY_LONGITUDE, "0"));
    }*/

   /* public static String getLongitude() {
        return PreferenceUtils.getString(KEY_LONGITUDE, "0");
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    protected void initToolbar(boolean status) {
        if (status && getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            View actionView = getCustomActionBar();
            if (actionView == null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(setToolbarName());
            } else {
                ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.MATCH_PARENT);
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setCustomView(actionView, layout);
            }
        }
    }

    protected View getCustomActionBar() {
        return null;
    }

    public boolean showToolbar() {
        return false;
    }

    public void init() {
        initToolbar(showToolbar());
        initView();
        initListener();
        bindDataWithUi();
    }

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void bindDataWithUi();

    public void initCustomToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(setToolbarName());
            toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back));
        }
    }

    /**
     * show Progress Dialog
     */
    public void showLoadingDialog() {
        if (isDestroyingActivity()) return;
        if (mProgressDialog == null) {
            /*  mProgressDialog = ProgressDialog.show(this, "", changeTitleColor("Please Wait..."), false, false);
             *//* mProgressDialog = new ProgressDialog(this, R.style.DialogTheme);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setContentView(R.layout.dialog_progress);*/
            mProgressDialog = new ProgressDialog(this, R.style.DialogTheme);
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.dialog_lollipop_progress);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * @return boolean value
     * return true if Progress Dialog show else return false
     */
    public boolean isShowingProgressDialog() {
        return !(mProgressDialog == null) && mProgressDialog.isShowing();
    }

    /***
     * hide the Progress Dialog
     */
    public void hideLoadingDialog() {
        if (isDestroyingActivity())
            return;
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * check the base Activity is destroy or not
     * if activity destroy return true else return false
     *
     * @return
     */
    public boolean isDestroyingActivity() {
        return isFinishing() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isDestroyed();
    }

    /**
     * hide the keyboard
     */
    public void hideSoftInputKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * this function check the internet connection and return the boolean value
     *
     * @return true and false value
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void addFragment(Fragment fragment, int containerId) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment)
                .commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment);
        if (addToBackStack) {
            replace.addToBackStack(fragment.getClass().getName());
        }
        replace.commitAllowingStateLoss();
    }

    public String setToolbarName() {
        return /*getResources().getString(R.string.app_name);*/"";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isCallPermission() {
        return checkPermission(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
    }

    public boolean hasPermission(@NonNull String permission) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PackageManager.PERMISSION_GRANTED == checkSelfPermission(permission);
    }

    public boolean hasPermission(String[] permission) {
        for (String s : permission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(s)) {
                    return false;
                }
            }
        }
        return true;
    }



    protected void storagePermissionGrant() {
    }

    public boolean storagePermission(String[] permission) {
        for (String s : permission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(s)) {
                    requestPermissions(permission, 100);
                    return false;
                }
            }
        }
        return true;
    }



   /*  @Override
    public void onLocationFound(Location location) {

    }*/

    public boolean checkPermission(String[] permission, int permissionCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission, permissionCode);
            hasPermission(permission);
        }
        return true;
    }


    public void startLocationService() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        startLocationUpdates();
    }

    public void checkLocationSettings() {
        Intent intent = new Intent(this, LocationSettingActivity.class);
        startActivityForResult(intent, REQUEST_CODE_LOCATION_SETTING);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (requestCode == REQUEST_CODE_LOCATION_SETTING) {
            if (resultCode == Activity.RESULT_OK) {
                startLocationService();
            }
        }*/
    }

    protected void stopLocationService() {
        Log.e(TAG, "stopLocationService: ");
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
        }
    }

    public void selfPermission(String[] permission, int permissionCode) {
        if (!hasPermission(permission)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permission, permissionCode);
            }
        }
    }

    public SpannableStringBuilder changeTitleColor(String title) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(title);
        ssBuilder.setSpan(foregroundColorSpan, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssBuilder;
    }

    @SuppressLint("RestrictedApi")
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Creates a callback for receiving location events.
     */
    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();
                onLocationFound(mCurrentLocation);
                updateCurrentLocation(mCurrentLocation);
            }
        };
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        if (ActivityCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        //  updateUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                //  Toast.makeText(BaseActivity.this, "Location settings are not satisfied. Attempting to upgrade " + "location settings ", Toast.LENGTH_SHORT).show();
                                try {
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(BaseActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    ///       Toast.makeText(BaseActivity.this, "PendingIntent unable to execute request.", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                //  String errorMessage = "Location settings are inadequate, and cannot be " + "fixed here. Fix in Settings.";
                                // Toast.makeText(BaseActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    protected void onLocationFound(Location location) {
    }


    public void slideUp() {
        View view = findViewById(R.id.toolbar);
        if (view.getVisibility() == View.INVISIBLE) {
            view.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0, // fromXDelta
                    0, // toXDelta
                    -view.getHeight(), // fromYDelta
                    0); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
        }
    }

    // slide the view from its current position to below itself
    public void slideDown() {
        View view = findViewById(R.id.toolbar);
        if (view.getVisibility() == View.VISIBLE) {
            TranslateAnimation animate = new TranslateAnimation(
                    0, // fromXDelta
                    0, // toXDelta
                    0, // fromYDelta
                    -view.getHeight()); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
            view.setVisibility(View.INVISIBLE);
        }
    }
}