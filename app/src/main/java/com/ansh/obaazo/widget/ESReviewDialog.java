package com.ansh.obaazo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.ansh.obaazo.R;


public class ESReviewDialog extends Dialog {
    private String msg;
    private ClickListener listener;
    private Context mContext;
    private String rating;
    private EditText etRating;

    public ESReviewDialog(@NonNull Context context) {
        super(context, R.style.My_DialogC);
        this.mContext = context;

    }

    public ESReviewDialog(@NonNull Context context, String msg, ClickListener listener) {
        super(context, R.style.My_DialogC);
        this.mContext = context;
        this.listener = listener;
        this.msg = msg;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_review);

        etRating = findViewById(R.id.et_rating);

        RatingBar ratingBar = findViewById(R.id.rating_resto);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating = String.valueOf(v);
            }
        });


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    dismiss();
                    listener.onClick(rating, etRating.getText().toString());
                }

            }
        });

    }

    private boolean validate() {
        if (TextUtils.isEmpty(etRating.getText())) {
            Toast.makeText(mContext, "write a review", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(rating)) {
            Toast.makeText(mContext, "Pleas give the rate", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public interface ClickListener {
        void onClick(String rating, String message);
    }
}
