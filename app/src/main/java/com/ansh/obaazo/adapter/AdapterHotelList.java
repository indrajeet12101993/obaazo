package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activit.ActivityHotelDetails;
import com.ansh.obaazo.activit.ActivitySearch;
import com.ansh.obaazo.resources.response.HotelSearchResponse;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.ansh.obaazo.web.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.web.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.web.AppConstant.size;


public class AdapterHotelList extends RecyclerView.Adapter<AdapterHotelList.ViewHolder> {
    private Context mContext;
    private ArrayList<HotelSearchResponse.ResultBean> mList;


    public AdapterHotelList(ActivitySearch mContext, ArrayList<HotelSearchResponse.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setmList(ArrayList<HotelSearchResponse.ResultBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_hotel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotelSearchResponse.ResultBean resultBean = mList.get(holder.getAdapterPosition());
        holder.cbHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityHotelDetails.class));
            }
        });
        holder.bindData(resultBean);


    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RatingBar rbHotelRating;
        private TextView tvRating;
        private View cbHotel;
        private ImageView ivHotelImage;
        private TextView tvHotelName;


        public ViewHolder(View itemView) {
            super(itemView);
            cbHotel = itemView.findViewById(R.id.cb_hotel);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            rbHotelRating = itemView.findViewById(R.id.rb_rating);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }

        public void bindData(HotelSearchResponse.ResultBean bean) {
            Picasso.get()
                    .load((!(TextUtils.isEmpty(bean.getImage1()))) ? bean.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(size, size)
                    .centerInside()
                    .placeholder(R.drawable.ani_loader)
                    .error(R.drawable.ani_loader)
                    .into(ivHotelImage);

            tvHotelName.setText(bean.getHotel_name());
            rbHotelRating.setRating(Float.parseFloat(bean.getRating()));
            tvRating.setText(bean.getRating() + "/5");


        }
    }
}
