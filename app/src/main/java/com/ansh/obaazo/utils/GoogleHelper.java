package com.ansh.obaazo.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.UserInfo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


/**
 * Created by Jeevan Gupta on 15-09-2017.
 * {@link GoogleHelper}
 */

public class GoogleHelper implements GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = GoogleHelper.class.getSimpleName();
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private FragmentActivity activity;
    private OnGoogleListener onGoogleListener;


    public GoogleHelper(FragmentActivity activity, OnGoogleListener onGoogleListener) {
        this.activity = activity;
        this.onGoogleListener = onGoogleListener;
        //  initSDK();
    }

    public void initSDK() {
     /*   GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mAuth = FirebaseAuth.getInstance();*/
    }

    public Intent signIn() {
        signOut();
        return Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        onGoogleListener.OnGoogleError(connectionResult.getErrorMessage());

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            onGoogleListener.OnGoogleError(result.getStatus().getStatusMessage());
            // Google Sign In failed, update UI appropriately
            // ...
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserInfo userInfo = new UserInfo();
                            userInfo.setName(user.getDisplayName());
                            userInfo.setEmail(user.getEmail());
                            userInfo.setProfileImg(String.valueOf(user.getPhotoUrl()));
                            onGoogleListener.OnGoogleSuccess(userInfo);
                        } else {
                            onGoogleListener.OnGoogleError(task.getException().getMessage());
                        }
                    }
                });
    }

    public void onDisconnect() {
        mGoogleApiClient.stopAutoManage(activity);
        mGoogleApiClient.disconnect();
    }

    public void signOut() {
        if (mGoogleApiClient != null) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {

                            // ...
                       /* Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),ActivitySplash.class);
                        startActivity(i);*/
                        }
                    });
        }
    }


    public interface OnGoogleListener {
        int RC_SIGN_IN = 2002;

        void OnGoogleSuccess(UserInfo user);

        void OnGoogleError(String errorMessage);
    }
}