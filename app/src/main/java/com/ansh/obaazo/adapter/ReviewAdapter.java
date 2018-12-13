package com.ansh.obaazo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.HotelReviewResponse;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private Context mContext;
    private ArrayList<HotelReviewResponse.ResultBean> mList;

    public ReviewAdapter(Context mContext, ArrayList<HotelReviewResponse.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setmList(ArrayList<HotelReviewResponse.ResultBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bindData(mList.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        public ReviewViewHolder(View itemView) {
            super(itemView);
        }

        private void bindData(HotelReviewResponse.ResultBean bean) {
            ((TextView) itemView.findViewById(R.id.tv_r_rating)).setText(bean.getHotelrating());
            ((TextView) itemView.findViewById(R.id.tv_r_user_name)).setText(bean.getUser_name());
            ((TextView) itemView.findViewById(R.id.tv_r_date)).setText(bean.getCreated());
           // ((TextView) itemView.findViewById(R.id.tv_r_title)).setText(bean.);
            ((TextView) itemView.findViewById(R.id.tv_r_details)).setText(bean.getReview());
        }
    }
}
