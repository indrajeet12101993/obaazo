package com.ansh.obaazo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansh.obaazo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCash extends BaseFragment {

    private View mView;

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

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }

}
