package com.ansh.obaazo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.TrendingHotelResponse;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.ansh.obaazo.web.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.web.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.web.AppConstant.size;

public class TreandingAdapter extends RecyclerView.Adapter<TreandingAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<TrendingHotelResponse.ResultBean> mList;

    public TreandingAdapter(Context mContext, ArrayList<TrendingHotelResponse.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    public void setmList(ArrayList<TrendingHotelResponse.ResultBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_treanindg, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mList.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHotelImage;
        private TextView tvHotelName;
        private TextView tvAddress;
        private TextView tvRating;
        private TextView tvAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }

        public void bindData(TrendingHotelResponse.ResultBean bean) {
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
            tvRating.setText("Rating " + bean.getRating() + "/5");
            tvAmount.setText("Start from  ₹" + bean.getHotel_actual_price());
        }
    }
}
