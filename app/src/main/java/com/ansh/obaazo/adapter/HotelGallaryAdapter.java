package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ZoomImageViewActivity;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class HotelGallaryAdapter extends RecyclerView.Adapter<HotelGallaryAdapter.ViewHolder> {
    private String hotelName;
    private Context mContext;
    private HotelImageResponse mHotelImage;

    public HotelGallaryAdapter(Context mContext, HotelImageResponse mHotelImage, String hotelName) {
        this.mContext = mContext;
        this.mHotelImage = mHotelImage;
        this.hotelName = hotelName;
    }


    public void setmHotelImage(HotelImageResponse mHotelImage) {
        this.mHotelImage = mHotelImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        HotelImageResponse.ResultBean images = mHotelImage.getResult().get(holder.getAdapterPosition());
        Picasso.get()
                .load(images.getImage_n())
                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .resize(size, size)
                .placeholder(R.drawable.ic_hotel_place_holder)
                .error(R.drawable.ic_hotel_place_holder)
                .centerInside()
                .into(holder.ivImage);

        holder.itemView.findViewById(R.id.ll_hotel_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ZoomImageViewActivity.class)
                        .putExtra(AppConstant.IMAGES, mHotelImage)
                        .putExtra(AppConstant.HOTEL_DETAILS, hotelName)
                        .putExtra(AppConstant.POSITION, holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return (mHotelImage != null && mHotelImage.getResult() != null ? mHotelImage.getResult().size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_hotel_image);
        }
    }
}
