package com.ansh.obaazo.fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansh.obaazo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    public BaseFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //init();
        return inflater.inflate(R.layout.fragment_base, container, false);
    }


    public boolean hasPermission(String[] permission) {
        for (String s : permission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (PackageManager.PERMISSION_GRANTED != getActivity().checkSelfPermission(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void init() {
        initView();
        bindDataWithUi();
        initListener();
    }


    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void bindDataWithUi();


    public void addFragment(Fragment fragment, int containerId) {
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(containerId, fragment)
                    .commitAllowingStateLoss();
        }
    }

    public void replaceFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        if (getActivity() != null) {
            FragmentTransaction replace = getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragment);
            if (addToBackStack) {
                replace.addToBackStack(fragment.getClass().getName());
            }
            replace.commitAllowingStateLoss();
        }
    }
}
