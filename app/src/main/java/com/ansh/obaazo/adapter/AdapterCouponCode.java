package com.ansh.obaazo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.CouponListResponse;

import java.util.ArrayList;

public class AdapterCouponCode extends RecyclerView.Adapter<AdapterCouponCode.AdapterCouponCodeViewHolder> {
    private Context mContext;
    private ArrayList<CouponListResponse.ResultBean> mData;

    public AdapterCouponCode(Context mContext, ArrayList<CouponListResponse.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public AdapterCouponCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCouponCodeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_coupon_code, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterCouponCodeViewHolder holder, int position) {
        holder.bindData(mData.get(holder.getAdapterPosition()));
        holder.llShapeCoupon.setBackgroundResource(mData.get(holder.getAdapterPosition()).isSelected() ?
                R.drawable.shape_rect_green_selected : R.drawable.shape_rect);

        holder.llShapeCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.get(holder.getAdapterPosition()).setSelected(!mData.get(holder.getAdapterPosition()).isSelected());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public void setData(ArrayList<CouponListResponse.ResultBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public class AdapterCouponCodeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDiscountPer;
        private TextView tvDiscountCode;
        private LinearLayout llShapeCoupon;

        public AdapterCouponCodeViewHolder(View itemView) {
            super(itemView);
            tvDiscountPer = itemView.findViewById(R.id.tv_discount_per);
            tvDiscountCode = itemView.findViewById(R.id.tv_coupon_code);
            llShapeCoupon = itemView.findViewById(R.id.ll_shape_coupon);
        }

        public void bindData(CouponListResponse.ResultBean bean) {
            tvDiscountPer.setText(bean.getCoupon_percent() + "% Discount on booking");
            tvDiscountCode.setText(bean.getCoupon_name());
        }
    }
}
