package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ActivityHotelDetails;
import com.ansh.obaazo.activity.ActivitySearch;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;


public class AdapterHotelList extends RecyclerView.Adapter<AdapterHotelList.ViewHolder> {
    private Context mContext;
    private ArrayList<HotelInfo> mList;
    private String startDate;
    private String endDate;
    private String bookingDates;
    private int adultCount;
    private int childCount;
    private int roomCount;
    private DecimalFormat format = new DecimalFormat("#.#");

    public AdapterHotelList(ActivitySearch mContext, ArrayList<HotelInfo> mList) {
        this.mContext = mContext;
        this.mList = mList;

    }

    public void setmList(ArrayList<HotelInfo> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setBookingDetails(String startDate, String endDate, int adultCount, int childCount, int roomCount, String bookingDates) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.roomCount = roomCount;
        this.bookingDates = bookingDates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_hotel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HotelInfo resultBean = mList.get(holder.getAdapterPosition());
        holder.cbHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityHotelDetails.class)
                        .putExtra(AppConstant.START_DATE, startDate)
                        .putExtra(AppConstant.END_DATE, endDate)
                        .putExtra(AppConstant.NO_OF_ADULT, adultCount)
                        .putExtra(AppConstant.NO_OF_CHILD, childCount)
                        .putExtra(AppConstant.NO_OF_ROOM, roomCount)
                        .putExtra(AppConstant.BOOKING_DATES, bookingDates)
                        .putExtra(AppConstant.HOTEL_DETAILS, resultBean));
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
        private TextView tvDistance;


        public ViewHolder(View itemView) {
            super(itemView);
            cbHotel = itemView.findViewById(R.id.cb_hotel);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            rbHotelRating = itemView.findViewById(R.id.rb_rating);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvDistance = itemView.findViewById(R.id.tv_distance);
        }

        public void bindData(HotelInfo bean) {
            Picasso.get()
                    .load((!(TextUtils.isEmpty(bean.getImage1()))) ? bean.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(size, size)
                    .centerInside()
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .into(ivHotelImage);

            tvHotelName.setText(bean.getHotel_name());
            rbHotelRating.setRating(Float.parseFloat((TextUtils.isEmpty(bean.getRating()) ? "0.0" : bean.getRating())));
            tvRating.setText(bean.getRating() + "/5");
            if (bean.getDistance() != 0) {
                tvDistance.setText(format.format(bean.getDistance()) + " KM");
            }


        }
    }

}
