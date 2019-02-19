package com.getepay.getepay;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author by AKHILESH on 19/02/18.
 */

public class ProgressDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        setCancelable(false);
        View view = inflater.inflate(R.layout.dialog_progress, container, false);
        return view;
    }

    /*//public void showProgressBar(FragmentManager p_manager) {
        show(p_manager, "dialog");
    }
*/
    @Override
    public void show(FragmentManager manager, String title) {
        try {
            super.show(manager, title);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (NullPointerException | IllegalStateException np) {
            np.printStackTrace();
        }
    }
}
