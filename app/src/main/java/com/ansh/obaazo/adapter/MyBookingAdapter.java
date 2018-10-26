package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ActivityMyBookingDetails;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.widget.ESDialogSimple;
import com.ansh.obaazo.widget.ESReviewDialog;

import java.util.ArrayList;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyBookingViewHolder> {
    private Context mContext;

    private ArrayList<MyBookingResponse.ResultBean> mData;

    public MyBookingAdapter(Context mContext, ArrayList<MyBookingResponse.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyBookingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_my_booking, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingViewHolder holder, int position) {
        holder.itemView.findViewById(R.id.cv_my_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityMyBookingDetails.class));
            }
        });

        holder.itemView.findViewById(R.id.btn_write_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ESReviewDialog(mContext, "", new ESReviewDialog.ClickListener() {
                    @Override
                    public void onClick(String rate, String msg) {
                        //  submitReview(rate, msg);
                        // Toast.makeText(getContext(), msg + " : " + rate, Toast.LENGTH_SHORT).show();

                    }
                }).show();
            }
        });

        holder.itemView.findViewById(R.id.btn_cancel_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ESDialogSimple(mContext, "Are you sure to cancel this Booking ?", new ESDialogSimple.ClickListener() {
                    @Override
                    public void onClick() {

                    }
                }).show();
            }
        });


    }


    public void setmData(ArrayList<MyBookingResponse.ResultBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public class MyBookingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHotelName;

        public MyBookingViewHolder(View itemView) {
            super(itemView);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
        }

        public void bindData(MyBookingResponse.ResultBean been) {
           /* Picasso.get()
                    .load((!(TextUtils.isEmpty(bean.getImage1()))) ? bean.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(size, size)
                    .centerInside()
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .into(ivHotelImage);
*/

            //tvHotelName.setText(been);

        }
    }
}
