package com.ansh.obaazo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.ansh.obaazo.R;

public class SimpleDialog extends Dialog {
    private String msg;
    private String title;
    private ClickListener listener;
    private Context mContext;


    public SimpleDialog(@NonNull Context context) {
        super(context, R.style.My_DialogC);
        this.mContext = context;

    }

    public SimpleDialog(@NonNull Context context, String msg, ClickListener listener) {
        super(context, R.style.My_DialogC);
        this.mContext = context;
        this.listener = listener;
        this.msg = msg;

    }


    public SimpleDialog(@NonNull Context context, String title, String msg) {
        super(context, R.style.My_DialogC);
        this.mContext = context;
        this.title = title;
        this.msg = msg;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_take_away);


        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_msg);
        tvTitle.setText(title);
        tvMessage.setText(msg);
     //   tvMessage.setMovementMethod(new ScrollingMovementMethod());


    }


    public interface ClickListener {
        void onClick(String date, String time);
    }
}
