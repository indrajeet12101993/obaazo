package com.ansh.obaazo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.ansh.obaazo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by Jeevan Gupta on 07-09-2017.
 * {@link LocationSettingActivity}
 */

public class LocationSettingActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks {

    public static final int REQUEST_CODE_CHECK_SETTINGS = 1002;
    public static final String KEY_LOCATION_FOUND_FAIL_REASON = "location_found_fail_reason";
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5 * 60 * 1000 * 4;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final int REQUEST_CODE_UPDATE_PLAY_SERVICES = 1000;
    private static final int REQUEST_CODE_PERMISSION_CHECK = 1001;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    @Override
    protected int getLayoutId() {
        return R.layout.transparent_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isGooglePlayServicesAvailable()) {
            checkForSdkVersion();
        } else {
            promptToUpdatePlayServices();
        }
    }

    private void buildGoogleApiClient() {
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(this);
        builder.addApi(LocationServices.API);
        mGoogleApiClient = builder.build();
        mGoogleApiClient.connect();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkLocationSetting();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void notifyLocationSettingFail(FailReason reason) {
        Intent intent = new Intent();
        intent.putExtra(KEY_LOCATION_FOUND_FAIL_REASON, reason.value);
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    private void notifyLocationSettingSuccess() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void promptToUpdatePlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (errorCode != ConnectionResult.SUCCESS && googleApiAvailability.isUserResolvableError(errorCode)) {
            googleApiAvailability.getErrorDialog(this, errorCode, REQUEST_CODE_UPDATE_PLAY_SERVICES, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    notifyLocationSettingFail(FailReason.GOOGLE_PLAY_SERVICES_UPDATE);
                }
            }).show();
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        return errorCode == ConnectionResult.SUCCESS;
    }

    private void checkForSdkVersion() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            buildGoogleApiClient();
        } else {
            String[] requestedPermission = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            if (hasPermission(requestedPermission)) {
                buildGoogleApiClient();
            } else {
                checkPermission(requestedPermission, REQUEST_CODE_PERMISSION_CHECK);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                notifyLocationSettingSuccess();
            } else {
                notifyLocationSettingFail(FailReason.LOCATION_SETTING);
            }
        } else if (requestCode == REQUEST_CODE_UPDATE_PLAY_SERVICES) {
            if (isGooglePlayServicesAvailable()) {
                checkForSdkVersion();
            } else {
                notifyLocationSettingFail(FailReason.GOOGLE_PLAY_SERVICES_UPDATE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION_CHECK) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean showPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) LocationSettingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

//                    boolean showPermissionRationale = shouldShowRequestPermissionRationale(permissions[0]);
//                    notifyLocationPermissionDenied(showPermissionRationale ? PERMISSION_DENY_OPTION_DENY : PERMISSION_DENY_OPTION_NEVER_ASK_AGAIN);
                    notifyLocationSettingFail(FailReason.LOCATION_PERMISSION);
                }
            }
        }
    }

    protected void checkLocationSetting() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        LocationSettingsRequest mLocationSettingsRequest = builder.build();

        PendingResult<LocationSettingsResult> result = LocationServices
                .SettingsApi.checkLocationSettings(mGoogleApiClient, mLocationSettingsRequest);
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS: {
                        notifyLocationSettingSuccess();
                        break;
                    }
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED: {
                        try {
                            status.startResolutionForResult(LocationSettingActivity.this, REQUEST_CODE_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE: {

                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    public enum FailReason {
        GOOGLE_PLAY_SERVICES_UPDATE(1),
        LOCATION_PERMISSION(2),
        LOCATION_SETTING(3);

        private int value;

        FailReason(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}