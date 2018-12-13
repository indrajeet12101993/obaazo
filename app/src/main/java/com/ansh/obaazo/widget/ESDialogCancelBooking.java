package com.ansh.obaazo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;

import com.ansh.obaazo.R;


public class ESDialogCancelBooking extends Dialog {
    private String msg;
    private ClickListener listener;

    public ESDialogCancelBooking(@NonNull Context context) {
        super(context, R.style.My_Dialog);

    }

    public ESDialogCancelBooking(@NonNull Context context, String msg, ClickListener listener) {
        super(context, R.style.My_Dialog);
        this.listener = listener;
        this.msg = msg;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_simple);
        findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                listener.onClick();
            }
        });

        findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


    public interface ClickListener {
        void onClick();
    }
}
