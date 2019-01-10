package com.ansh.obaazo.fragment;


import android.os.Bundle;
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
import com.ansh.obaazo.resources.response.ObazoMoneyResponse;
import com.ansh.obaazo.resources.service.ObaazoMoneyService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCash extends BaseFragment {

    private View mView;
    private TextView tvMoney;
    private TextView tvExpier;
    private DecimalFormat format = new DecimalFormat("#.##");

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
            UserDetails results = new Gson().fromJson(tempDetails, UserDetails.class);
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
                    if (response.getResult() != null) {
                        ObazoMoneyResponse.ResultBean resultBean = response.getResult();
                        String tempData = FragmentCash.this.format.format(resultBean.getMoney());
                        tvMoney.setText("₹" + tempData);
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
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }

}
