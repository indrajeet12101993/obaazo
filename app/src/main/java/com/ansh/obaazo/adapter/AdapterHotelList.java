package com.ansh.obaazo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.ansh.obaazo.model.HotelPrice;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    private ArrayList<HotelPrice> hotelPrices;

    public AdapterHotelList(Activity mContext, ArrayList<HotelInfo> mList) {
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
        holder.bindData(resultBean, holder.getAdapterPosition());


    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public void setHotelPrice(ArrayList<HotelPrice> hotelPrices) {
        this.hotelPrices = hotelPrices;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvReview;
        private RatingBar rbHotelRating;
        private TextView tvRating;
        private View cbHotel;
        private ImageView ivHotelImage;
        private TextView tvHotelName;
        private TextView tvDistance;
        private TextView tvAddress;
        private TextView tvStartFrom;
        private TextView tvCrossPrice;


        public ViewHolder(View itemView) {
            super(itemView);
            cbHotel = itemView.findViewById(R.id.cb_hotel);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            rbHotelRating = itemView.findViewById(R.id.rb_rating);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvReview = itemView.findViewById(R.id.tv_review);
            tvStartFrom = itemView.findViewById(R.id.tv_start_from);
            tvCrossPrice = itemView.findViewById(R.id.tv_discount_price);
        }

        public void bindData(HotelInfo bean, int adapterPosition) {
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
            rbHotelRating.setRating(bean.getHotelrating());
            tvRating.setText(TextUtils.isEmpty(bean.getRating()) ? "N/A" : bean.getRating() + "/5");
            tvReview.setText(TextUtils.isEmpty(bean.getReview()) ? "" : bean.getReview() + " Reviews");
            tvAddress.setText(bean.getGoogle_map());
            tvDistance.setText(bean.getDistance() != 0 ? format.format(bean.getDistance()) + " KM" : "");
            if (bean.getCouple() != null) {
                itemView.findViewById(R.id.tv_couple_friendly).setVisibility(bean.getCouple().equalsIgnoreCase("0") ? View.GONE : View.VISIBLE);
            }

            if (TextUtils.isEmpty(bean.getFlag())) {
                (itemView.findViewById(R.id.tv_flag_avi)).setVisibility(View.GONE);
            } else {
                (itemView.findViewById(R.id.tv_flag_avi)).setVisibility(View.VISIBLE);
                ((TextView) itemView.findViewById(R.id.tv_flag_avi)).setText(bean.getFlag());
            }

            String startPrice = bean.getStartFrom();
            if (TextUtils.isEmpty(startPrice)||startPrice.equalsIgnoreCase("0")) {
                mList.get(adapterPosition).setAvailable(false);
                itemView.findViewById(R.id.tv_not_avi).setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.ll_start).setVisibility(View.INVISIBLE);

            } else {
                itemView.findViewById(R.id.ll_start).setVisibility(View.VISIBLE);
                tvStartFrom.setText("₹" + getDisountPrice(startPrice));
                tvCrossPrice.setText("₹" + startPrice);
                tvCrossPrice.setPaintFlags(tvCrossPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                mList.get(adapterPosition).setStartFrom(startPrice);
                mList.get(adapterPosition).setAvailable(true);
                itemView.findViewById(R.id.tv_not_avi).setVisibility(View.GONE);
            }
        }
    }

    private Double getDisountPrice(String startPrice) {
        Double tempPrice = Double.parseDouble(startPrice);
        if (!TextUtils.isEmpty(startPrice)) {
            if (PreferencesUtils.getString(AppConstant.USER_CATEGORY).equalsIgnoreCase(AppConstant.OLD_USER)
                    && PreferencesUtils.getBoolean(AppConstant.IS_LOGIN)) {
                tempPrice = tempPrice - (tempPrice * 10 / 100);
            } else {
                tempPrice = tempPrice - (tempPrice * 20 / 100);
            }
        }
        return tempPrice;
    }

    public String getStartPrice(String hotelId) {
        if (hotelPrices != null) {
            for (int i = 0; i < hotelPrices.size(); i++) {
                if (hotelPrices.get(i).getHotelId().equalsIgnoreCase(hotelId)) {
                    return hotelPrices.get(i).getAdultPrice();
                }
            }
        }
        return "";
    }

}
