package com.ansh.obaazo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.MyReviewResponse;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;

/**
 * Created by Jeevan Gupta on 11/17/2018.
 */
public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.MyReviewViewHolder> {
    private Context mContext;
    private ArrayList<MyReviewResponse.Result> mData;

    public MyReviewAdapter(Context mContext, ArrayList<MyReviewResponse.Result> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    public void setmData(ArrayList<MyReviewResponse.Result> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyReviewViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_my_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyReviewViewHolder holder, int position) {
        holder.bindData(mData.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public class MyReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHotelName;
        private ImageView ivHotelImage;
        private TextView tvCheckInCheckOutTime;
        private TextView tvReviewDate;
        private RatingBar ratingBar;

        public MyReviewViewHolder(View itemView) {
            super(itemView);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvCheckInCheckOutTime = itemView.findViewById(R.id.tv_check_in_check_out_time);
            tvReviewDate = itemView.findViewById(R.id.tv_review_date);
            ratingBar = itemView.findViewById(R.id.rb_hotel_rating);
        }

        public void bindData(MyReviewResponse.Result been) {

            Picasso.get()
                    .load((!(TextUtils.isEmpty(been.getImage1()))) ? been.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(200, 200)
                    .centerInside()
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .into(ivHotelImage);


            tvHotelName.setText(been.getHotel_name());
            //   ((TextView)itemView.findViewById(R.id.tv_checkin_details)).setText(bean.get);
            //  tvCheckInCheckOutTime.setText(DateUtils.parseDate(been.getCheck_in()) + " - " + DateUtils.parseDate(been.getCheck_out()));
            tvReviewDate.setText(been.getCreated());
            String tempRate = (TextUtils.isEmpty(been.getRating()) ? "0" : been.getRating());
            ratingBar.setRating(Float.parseFloat(tempRate));
            ((TextView) itemView.findViewById(R.id.tv_r_rating)).setText(been.getReview());
        }
    }
}
