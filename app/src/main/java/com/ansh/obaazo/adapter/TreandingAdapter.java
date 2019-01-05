package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ActivityHotelDetails;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class TreandingAdapter extends RecyclerView.Adapter<TreandingAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<HotelInfo> mList;

    public TreandingAdapter(Context mContext, ArrayList<HotelInfo> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setmList(ArrayList<HotelInfo> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_treanindg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.bindData(mList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
        holder.cvTreanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityHotelDetails.class)
                        .putExtra(AppConstant.START_DATE, "")
                        .putExtra(AppConstant.END_DATE, "")
                        .putExtra(AppConstant.NO_OF_ADULT, 0)
                        .putExtra(AppConstant.NO_OF_CHILD, 0)
                        .putExtra(AppConstant.NO_OF_ROOM, 0)
                        .putExtra(AppConstant.BOOKING_DATES, "")
                        .putExtra(AppConstant.HOTEL_DETAILS, mList.get(holder.getAdapterPosition())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvTreanding;
        private ImageView ivHotelImage;
        private TextView tvHotelName;
        private TextView tvAddress;
        private TextView tvRating;
        private TextView tvAmount;
        private RatingBar rbHotelRating;

        public ViewHolder(View itemView) {
            super(itemView);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            cvTreanding = itemView.findViewById(R.id.cv_row_treanding);
            rbHotelRating = itemView.findViewById(R.id.rb_rating);

        }

        private void bindData(HotelInfo bean, int position) {
            Picasso.get()
                    .load((!TextUtils.isEmpty(bean.getImage1()) ? bean.getImage1() : null))
                    .error(R.drawable.ani_loader)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(size, size)
                    .centerInside()
                    .resize(200, 200)
                    .placeholder(R.drawable.ani_loader)
                    .into(ivHotelImage);

            tvHotelName.setText(bean.getHotel_name());
            tvAddress.setText(bean.getAddress());
            tvRating.setText("Review " + bean.getRating() + "/5");
            tvAmount.setVisibility(bean.getStartFrom() != null ? View.VISIBLE : View.INVISIBLE);
            tvAmount.setText("Start from  ₹" + bean.getStartFrom());
            rbHotelRating.setRating(bean.getHotelrating());
            if (bean.getStartFrom() != null) {
                mList.get(position).setStartFrom(bean.getStartFrom());
                mList.get(position).setAvailable(true);
                itemView.findViewById(R.id.tv_not_avi).setVisibility(View.INVISIBLE);
            } else {
                itemView.findViewById(R.id.tv_not_avi).setVisibility(View.VISIBLE);
            }
        }
    }
}
