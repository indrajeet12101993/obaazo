package com.ansh.obaazo.fragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.resources.request.UpdateProfileRequest;
import com.ansh.obaazo.resources.response.UpdateProfileResponse;
import com.ansh.obaazo.resources.service.UpdateProfileService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.gson.Gson;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends BaseFragment {

    private View mView;
    private Button btnUpdate;
    private EditText etUserName;
    private EditText etEmail;
    private EditText etMobileNo;
    private UserDetails results;


    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        btnUpdate = mView.findViewById(R.id.btn_search);
        etUserName = mView.findViewById(R.id.et_user_name);
        etEmail = mView.findViewById(R.id.et_email);
        etMobileNo = mView.findViewById(R.id.et_mobile_no);

    }

    @Override
    protected void initListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnUpdate.getText().toString().equalsIgnoreCase("Update") && validate()) {
                    hitUpdateApi();
                } else {
                    fieldValidate(true);
                }
            }
        });

        mView.findViewById(R.id.tv_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesUtils.clearSharedPrefs();
                replaceFragment(new FragmentHome(), R.id.fm_main, false);
            }
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            Toast.makeText(getContext(), "Enter valid name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            Toast.makeText(getContext(), "Enter valid email Id", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etMobileNo.getText().toString()) || etMobileNo.getText().length() < 10) {
            Toast.makeText(getContext(), "Enter valid mobile no", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void bindDataWithUi() {
        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        if (!TextUtils.isEmpty(tempDetails)) {
            results = new Gson().fromJson(tempDetails, UserDetails.class);
            etUserName.setText(results.getName());
            etEmail.setText(results.getEmail());
            etMobileNo.setText(results.getMobile());
            fieldValidate(false);
        } else {
            Toast.makeText(getActivity(), "Somethings Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }


    public void fieldValidate(boolean isEnable) {
        etUserName.setEnabled(isEnable);
        etEmail.setEnabled(isEnable);
        etMobileNo.setEnabled(isEnable);
        btnUpdate.setText((isEnable) ? "Update" : "Edit");
    }


    public void hitUpdateApi() {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).showLoadingDialog();
        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setUserId(results.getId());
        request.setName(etUserName.getText().toString());
        request.setEmail(etEmail.getText().toString());
        request.setMobileNo(etMobileNo.getText().toString());

        new UpdateProfileService(getContext()).execute(request, new ApiCallback<UpdateProfileResponse>() {
            @Override
            public void onSuccess(Call<UpdateProfileResponse> call, UpdateProfileResponse response) {
                if (response.getResponseCode().equalsIgnoreCase("200")) {
                    PreferencesUtils.putString(AppConstant.USER_DETAILS, new Gson().toJson(response.getData()));
                    bindDataWithUi();

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

}
