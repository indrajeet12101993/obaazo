package com.ansh.obaazo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.LoginResponse;
import com.ansh.obaazo.resources.response.ObazoMoneyResponse;
import com.ansh.obaazo.resources.service.ObaazoMoneyService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.gson.Gson;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCash extends BaseFragment {

    private View mView;
    private TextView tvMoney;
    private TextView tvExpier;

    public FragmentCash() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cash, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        tvMoney = mView.findViewById(R.id.tv_money);
        tvExpier = mView.findViewById(R.id.tv_expire);
        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        if (!TextUtils.isEmpty(tempDetails)) {
            UserDetails results = new Gson().fromJson(tempDetails,UserDetails.class);
            String id = results.getId();
            hitObaazoMoneyApi(id);
        } else {
            Toast.makeText(getActivity(), "Somethings Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void hitObaazoMoneyApi(String id) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).showLoadingDialog();

        BaseRequest baseRequest = new BaseRequest();
        //  baseRequest.setId(PreferencesUtils.getString(AppConstant.USER_ID));
        baseRequest.setId(id);
        new ObaazoMoneyService(getContext()).execute(baseRequest, new ApiCallback<ObazoMoneyResponse>() {
            @Override
            public void onSuccess(Call<ObazoMoneyResponse> call, ObazoMoneyResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    if (response.getResult() != null && response.getResult().size() != 0) {
                        ObazoMoneyResponse.ResultBean resultBean = response.getResult().get(0);
                        tvMoney.setText("₹" + resultBean.getMoney());
                        tvExpier.setText("Expires On " + resultBean.getExpiry_date());
                    } else {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.getResponse_message(), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }

}
