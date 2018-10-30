package com.ansh.obaazo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.SendOtpResponse;
import com.ansh.obaazo.resources.service.SendOtpService;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends BaseFragment {


    private View mView;
    private EditText etMobile;
    private Button btnContinue;

    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        etMobile = mView.findViewById(R.id.et_mobile_no);
        btnContinue = mView.findViewById(R.id.btn_continue);

    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void bindDataWithUi() {

    }

    public void sendOtp(String mobileNo) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).showLoadingDialog();
        BaseRequest request = new BaseRequest();
        request.setId(mobileNo);
        new SendOtpService(getContext()).execute(request, new ApiCallback<SendOtpResponse>() {
            @Override
            public void onSuccess(Call<SendOtpResponse> call, SendOtpResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {

                } else {
                    Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();
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
