package com.ansh.obaazo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.model.UserInfo;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.LoginResponse;
import com.ansh.obaazo.resources.response.SendOtpResponse;
import com.ansh.obaazo.resources.service.SendOtpService;
import com.ansh.obaazo.resources.service.SocialLoginService;
import com.ansh.obaazo.resources.service.VerifyOtpService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.AutoScrollViewPager;
import com.ansh.obaazo.utils.FBHelper;
import com.ansh.obaazo.utils.GoogleHelper;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.ansh.obaazo.widget.ESOTP;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends BaseFragment implements FBHelper.OnFbSignInListener, GoogleHelper.OnGoogleListener {


    private View mView;
    private EditText etMobile;
    private Button btnContinue;
    private View facebook;
    private View google;
    private FBHelper fbHelper;
    private GoogleHelper googleHelper;
    private ESOTP esotp;
    private AutoScrollViewPager viewPager;
    private String userType = AppConstant.NEW_USER;


    public FragmentLogin() {
        // Required empty public constructor
    }

    Integer[] imageId = {R.drawable.ic_otp_icon, R.drawable.ic_reset_otp, R.drawable.ic_otp_icon, R.drawable.ic_reset_otp};
    String[] imagesName = {"image1", "image2", "image3", "image4"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        fbHelper = new FBHelper(getActivity(), this);
        googleHelper = new GoogleHelper(getActivity(), this);
        //  googleHelper.initSDK();
        etMobile = mView.findViewById(R.id.et_mobile_no);
        btnContinue = mView.findViewById(R.id.btn_continue);
        google = mView.findViewById(R.id.ll_google);
        facebook = mView.findViewById(R.id.ll_facebook);

      /*  viewPager= (AutoScrollViewPager) mView.findViewById(R.id.viewpager);
        viewPager.startAutoScroll();
        viewPager.setInterval(3000);
        viewPager.setCycle(true);
        viewPager.setStopScrollWhenTouch(true);

        PagerAdapter adapter = new CustomAdapter(getActivity(),imageId,imagesName);
        viewPager.setAdapter(adapter);*/

    }

    @Override
    protected void initListener() {
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbHelper.connect();
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleHelper.signIn();
                getActivity().startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtp(etMobile.getText().toString());
                esotp.show();
            }
        });

        esotp = new ESOTP(getContext(), etMobile.getText().toString(), new ESOTP.ClickListener() {
            @Override
            public void obVerifyOtp(String rating, String message) {
                verifyOtp(etMobile.getText().toString(), message);

            }

            @Override
            public void onResendOtp(String mobileNO, String message) {
                sendOtp(etMobile.getText().toString());
            }
        });


    }

    @Override
    protected void bindDataWithUi() {

    }

    private void sendOtp(String mobileNo) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).showLoadingDialog();
        BaseRequest request = new BaseRequest();
        request.setId(mobileNo);
        request.setId2("normal");
        new SendOtpService(getContext()).execute(request, new ApiCallback<SendOtpResponse>() {
            @Override
            public void onSuccess(Call<SendOtpResponse> call, SendOtpResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    userType = response.getCustomerType();
                    Toast.makeText(getContext(), "Otp Send", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onComplete() {
                if (getActivity() != null)
                    ((BaseActivity) getActivity()).hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verifyOtp(String mobileNo, String otp) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).showLoadingDialog();
        BaseRequest request = new BaseRequest();
        request.setId(mobileNo);
        request.setId2(otp);
        new VerifyOtpService(getContext()).execute(request, new ApiCallback<LoginResponse>() {
            @Override
            public void onSuccess(Call<LoginResponse> call, LoginResponse response) {
                if (response.getResponseCode().equalsIgnoreCase("200")) {
                    PreferencesUtils.putBoolean(AppConstant.IS_LOGIN, true);
                    PreferencesUtils.putString(AppConstant.USER_CATEGORY, userType);
                    PreferencesUtils.putString(AppConstant.USER_DETAILS, new Gson().toJson(response.getData()));
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), response.getResponseMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onComplete() {
                if (getActivity() != null)
                    ((BaseActivity) getActivity()).hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void OnFbSuccess(UserInfo user) {
      //  Toast.makeText(getContext(), user.toString(), Toast.LENGTH_SHORT).show();
        hitSocialLoginApi(user.getEmail());

    }

    @Override
    public void OnFbError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnGoogleSuccess(UserInfo user) {
       // Toast.makeText(getContext(), user.toString(), Toast.LENGTH_SHORT).show();
        hitSocialLoginApi(user.getEmail());
    }

    @Override
    public void OnGoogleError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            googleHelper.onActivityResult(requestCode, resultCode, data);
        } else {
            fbHelper.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void hitSocialLoginApi(String email) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).showLoadingDialog();
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setId("social");
        baseRequest.setId2(email);
        new SocialLoginService(getContext()).execute(baseRequest, new ApiCallback<LoginResponse>() {
            @Override
            public void onSuccess(Call<LoginResponse> call, LoginResponse response) {
                if (response.getResponseCode().equalsIgnoreCase("200")) {
                    PreferencesUtils.putBoolean(AppConstant.IS_LOGIN, true);
                    PreferencesUtils.putString(AppConstant.USER_CATEGORY, userType);
                    PreferencesUtils.putString(AppConstant.USER_DETAILS, new Gson().toJson(response.getData()));
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), response.getResponseMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onComplete() {
                ((BaseActivity) getActivity()).hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
