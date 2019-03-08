package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ActivityMyBookingDetails;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.listener.ItemClickNotiffy;
import com.ansh.obaazo.model.UserDetails;
import com.ansh.obaazo.resources.request.AddReviewRequest;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.AddReviewResponse;
import com.ansh.obaazo.resources.response.BaseResponse;
import com.ansh.obaazo.resources.response.MyBookingResponse;
import com.ansh.obaazo.resources.service.AddReviewService;
import com.ansh.obaazo.resources.service.CancelBookingOtpService;
import com.ansh.obaazo.resources.service.CancelBookingService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.ansh.obaazo.widget.ESDialogCancelBooking;
import com.ansh.obaazo.widget.ESOTP;
import com.ansh.obaazo.widget.ESReviewDialog;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyBookingViewHolder> {
    private Context mContext;

    private ArrayList<MyBookingResponse.ResultBean> mData;
    private String type = "1";
    private ESOTP esotp;
    private ItemClickNotiffy notiffy;

    public MyBookingAdapter(Context mContext, ArrayList<MyBookingResponse.ResultBean> mData, ItemClickNotiffy notiffy) {
        this.mContext = mContext;
        this.mData = mData;
        this.notiffy = notiffy;

    }


    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public MyBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyBookingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_my_booking, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyBookingViewHolder holder, int position) {
        holder.bindData(mData.get(holder.getAdapterPosition()));
        holder.itemView.findViewById(R.id.cv_my_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityMyBookingDetails.class).putExtra(AppConstant.MY_BOOKING, mData.get(holder.getAdapterPosition())));
            }
        });

        holder.itemView.findViewById(R.id.btn_write_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ESReviewDialog(mContext, "", new ESReviewDialog.ClickListener() {
                    @Override
                    public void onClick(String rate, String msg) {
                        hitAddReviewApi(rate, msg, holder.getAdapterPosition());
                        // submitReview(rate, msg);
                        // Toast.makeText(getContext(), msg + " : " + rate, Toast.LENGTH_SHORT).show();

                    }
                }).show();
            }
        });

        holder.itemView.findViewById(R.id.btn_cancel_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ESDialogCancelBooking(mContext, "Are you sure to cancel this Booking ?", new ESDialogCancelBooking.ClickListener() {
                    @Override
                    public void onClick() {
                        sendOtpDialog(mData.get(holder.getAdapterPosition()).getId(),
                                mData.get(holder.getAdapterPosition()).getUser_mobile());

                    }
                }).show();
            }
        });


    }

    public void sendOtp(String bookingId, String mobileNo) {
        BaseRequest request = new BaseRequest();
        request.setId(bookingId);
        request.setId2(mobileNo);
        new CancelBookingOtpService(mContext).execute(request, new ApiCallback<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, BaseResponse response) {
                Toast.makeText(mContext, response.getResponse_message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(mContext, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hitAddReviewApi(String rate, String msg, int adapterPosition) {
        ((BaseActivity) mContext).showLoadingDialog();
        AddReviewRequest request = new AddReviewRequest();
        String tempDetails = PreferencesUtils.getString(AppConstant.USER_DETAILS);
        if (!TextUtils.isEmpty(tempDetails)) {
            UserDetails results = new Gson().fromJson(tempDetails, UserDetails.class);
            request.setUserId(results.getId());
            request.setUserName(results.getName());
            request.setHotelId(mData.get(adapterPosition).getHotel_id());
            request.setHotelName(mData.get(adapterPosition).getHotel_name());
            request.setHotelRating(rate);
            request.setComment(msg);
            new AddReviewService(mContext).execute(request, new ApiCallback<AddReviewResponse>() {
                @Override
                public void onSuccess(Call<AddReviewResponse> call, AddReviewResponse response) {
                    if (response.getResponseCode().equalsIgnoreCase("200")) {
                        notiffy.onItemClick(0);
                    }

                    Toast.makeText(mContext, response.getResponseMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {
                    ((BaseActivity) mContext).hideLoadingDialog();

                }

                @Override
                public void onFailure(ApiException e) {
                    Toast.makeText(mContext, "Api Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            ((BaseActivity) mContext).hideLoadingDialog();
        }
    }


    public void setmData(ArrayList<MyBookingResponse.ResultBean> mData, String type) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public class MyBookingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHotelName;
        private ImageView ivHotelImage;
        private TextView tvCheckInCheckOutTime;
        private TextView tvBookingAmount;

        public MyBookingViewHolder(View itemView) {
            super(itemView);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvCheckInCheckOutTime = itemView.findViewById(R.id.tv_check_in_check_out_time);
            tvBookingAmount = itemView.findViewById(R.id.tv_booking_amount);
        }

        public void bindData(MyBookingResponse.ResultBean bean) {
            Picasso.get()
                    .load((!(TextUtils.isEmpty(bean.getImage1()))) ? bean.getImage1() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(200, 200)
                    .centerInside()
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .into(ivHotelImage);


            tvHotelName.setText("Obaazo " + bean.getHotel_id() + " " + bean.getHotel_name());
            //   ((TextView)itemView.findViewById(R.id.tv_checkin_details)).setText(bean.get);
            tvCheckInCheckOutTime.setText(DateUtils.parseDate(bean.getCheckin()) + " - " + DateUtils.parseDate(bean.getCheckout()));
            tvBookingAmount.setText(" ₹" + bean.getBooking_amount());

            itemView.findViewById(R.id.ll_book_action).setVisibility(type.equalsIgnoreCase("1") ? View.VISIBLE : View.GONE);
        }
    }

    public void sendOtpDialog(final String bookingId, String mobileNo) {
        esotp = new ESOTP(mContext, mobileNo, new ESOTP.ClickListener() {
            @Override
            public void obVerifyOtp(String rating, String message) {
                CancelBooking(bookingId, message);
            }

            @Override
            public void onResendOtp(String mobileNO, String message) {
                sendOtp(bookingId, mobileNO);
            }
        });
        esotp.show();
        sendOtp(bookingId, mobileNo);
    }

    public void CancelBooking(String bookingId, String otp) {
        BaseRequest request = new BaseRequest();
        request.setId(bookingId);
        request.setId2(otp);
        new CancelBookingService(mContext).execute(request, new ApiCallback<BaseResponse>() {
            @Override
            public void onSuccess(Call<BaseResponse> call, BaseResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    notiffy.onItemClick(0);
                }
                Toast.makeText(mContext, response.getResponse_message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(mContext, "Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
