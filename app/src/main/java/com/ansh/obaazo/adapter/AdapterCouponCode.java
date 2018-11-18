package com.ansh.obaazo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansh.obaazo.R;

import java.util.ArrayList;

public class AdapterCouponCode extends RecyclerView.Adapter<AdapterCouponCode.AdapterCouponCodeViewHolder> {
    private Context mContext;
    private ArrayList<String> mData;

    public AdapterCouponCode(Context mContext, ArrayList<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public AdapterCouponCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCouponCodeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_coupon_code, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCouponCodeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class AdapterCouponCodeViewHolder extends RecyclerView.ViewHolder {
        public AdapterCouponCodeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
