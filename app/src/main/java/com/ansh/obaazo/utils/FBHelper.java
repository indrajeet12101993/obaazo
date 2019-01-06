package com.ansh.obaazo.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ansh.obaazo.model.UserInfo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;

import androidx.fragment.app.Fragment;

import static com.facebook.login.widget.ProfilePictureView.TAG;

/**
 * Created by Jeevan Gupta on 15-09-2017.
 * {@link FBHelper}
 */

public class FBHelper {
    private FirebaseAuth mAuth;
    private Collection<String> permissions = Arrays.asList("public_profile ", "email", "user_birthday", "user_location");
    private CallbackManager callbackManager;

    private Activity activity;
    private Fragment fragment;
    private OnFbSignInListener fbSignInListener;


    public FBHelper(Activity activity, OnFbSignInListener fbSignInListener) {
        this.activity = activity;
        this.fbSignInListener = fbSignInListener;

    }

    public FBHelper(Fragment fragment, OnFbSignInListener fbSignInListener) {
        this.fragment = fragment;
        this.fbSignInListener = fbSignInListener;
        mAuth = FirebaseAuth.getInstance();
    }


    public void connect() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logOut();
        if (activity != null) {
            loginManager.logInWithReadPermissions(activity, permissions);
        } else
            loginManager.logInWithReadPermissions(fragment, permissions);
        loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        if (loginResult != null) {
                            // handleFacebookAccessToken(loginResult.getAccessToken());
                            callGraphAPI(loginResult.getAccessToken());
                        }
                    }

                    @Override
                    public void onCancel() {
                        fbSignInListener.OnFbError("User cancelled.");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        if (exception instanceof FacebookAuthorizationException) {
                            if (AccessToken.getCurrentAccessToken() != null) {
                                LoginManager.getInstance().logOut();
                            }
                        }
                        fbSignInListener.OnFbError(exception.getMessage());
                    }
                });

    }

    private void callGraphAPI(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        UserInfo userInfo = getUserModelFromGraphResponse(response);
                        fbSignInListener.OnFbSuccess(userInfo);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,gender,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private UserInfo getUserModelFromGraphResponse(GraphResponse graphResponse) {
        UserInfo userModel = new UserInfo();
        try {
            JSONObject jsonObject = graphResponse.getJSONObject();
            userModel.setName(jsonObject.getString("name"));
            userModel.setEmail(jsonObject.getString("email"));
            String id = jsonObject.getString("id");
            String profileImg = "https://graph.facebook.com/" + id + "/picture?type=large";
            userModel.setProfileImg(profileImg);
            Log.i(TAG, profileImg);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userModel;
    }

   /* private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(fragment.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            fbSignInListener.OnFbSuccess(user);
                        } else {
                            *//*mAuth.getCurrentUser().linkWithCredential(credential);*//*
                            fbSignInListener.OnFbError(task.getException().getMessage());
                        }
                    }
                });
    }*/

    public interface OnFbSignInListener {
        void OnFbSuccess(UserInfo user);

        void OnFbError(String errorMessage);
    }


}
