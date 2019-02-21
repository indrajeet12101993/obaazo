package com.ansh.obaazo.utils;

import android.content.Intent;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.UserInfo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;


/**
 * Created by Jeevan Gupta on 15-09-2017.
 * {@link GoogleHelper}
 */

public class GoogleHelper implements GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = GoogleHelper.class.getSimpleName();
    private FragmentActivity activity;
    private OnGoogleListener onGoogleListener;
    private GoogleSignInClient mGoogleSignInClient;


    public GoogleHelper(FragmentActivity activity, OnGoogleListener onGoogleListener) {
        this.activity = activity;
        this.onGoogleListener = onGoogleListener;
        initSDK();
    }

    private void initSDK() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }

    public Intent signIn() {
        signOut();
        return mGoogleSignInClient.getSignInIntent();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        onGoogleListener.OnGoogleError(connectionResult.getErrorMessage());
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        if (task.isComplete()) {
            handleSignInResult(task);
        } else {
            onGoogleListener.OnGoogleError(task.getException().getMessage());
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                UserInfo userInfo = new UserInfo();
                userInfo.setName(account.getDisplayName());
                userInfo.setEmail(account.getEmail());
                userInfo.setProfileImg(String.valueOf(account.getPhotoUrl()));
                onGoogleListener.OnGoogleSuccess(userInfo);
            } else {
                onGoogleListener.OnGoogleError("unable to login");
            }

        } catch (ApiException e) {
            onGoogleListener.OnGoogleError("Error" + e.getStatusCode());
        }
    }


    private void signOut() {
        if (mGoogleSignInClient != null) {
            mGoogleSignInClient.signOut();
        }
    }


    public interface OnGoogleListener {
        int RC_SIGN_IN = 2002;

        void OnGoogleSuccess(UserInfo user);

        void OnGoogleError(String errorMessage);
    }
}