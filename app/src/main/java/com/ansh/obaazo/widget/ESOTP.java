package com.ansh.obaazo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;

import androidx.annotation.NonNull;


public class ESOTP extends Dialog {
    private String msg;
    private ClickListener listener;
    private Context mContext;
    private String mobileNO;
    private EditText etOtp;

    public ESOTP(@NonNull Context context) {
        super(context, R.style.My_Dialog);
        this.mContext = context;

    }

    public ESOTP(@NonNull Context context, String mobileNO, ClickListener listener) {
        super(context, R.style.My_Dialog);
        this.mContext = context;
        this.listener = listener;
        //  this.msg = msg;
        this.mobileNO = mobileNO;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_otp);

        etOtp = findViewById(R.id.et_otp);

        ((TextView) findViewById(R.id.tv_msg)).setText("We have sent OTP to your mobile number " + mobileNO);


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    dismiss();
                    listener.obVerifyOtp(mobileNO, etOtp.getText().toString());
                }

            }
        });

        findViewById(R.id.tv_resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onResendOtp(mobileNO, etOtp.getText().toString());
            }
        });

        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    private boolean validate() {
        if (TextUtils.isEmpty(etOtp.getText()) || etOtp.getText().length() < 6) {
            Toast.makeText(mContext, "Enter Valid OTP", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public interface ClickListener {
        void obVerifyOtp(String mobileNo, String message);

        void onResendOtp(String rating, String message);
    }
}
