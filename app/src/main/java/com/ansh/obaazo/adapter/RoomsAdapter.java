package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ActivityPhotoAminitese;
import com.ansh.obaazo.listener.RItemListener;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.HotelPrice;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder> {
    private Context mContext;
    private HotelRoomResponse mData;
    private RItemListener<HotelRoomResponse.ResultBean> mListener;
    private ArrayList<HotelPrice> roomPrice;

    public RoomsAdapter(Context mContext, HotelRoomResponse mData, RItemListener<HotelRoomResponse.ResultBean> mListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mListener = mListener;
    }


    public void setmData(HotelRoomResponse mData) {
        this.mData = mData;
        for (int i = 0; i < mData.getResult().size(); i++) {
            this.mData.getBookingInfos().add(i, new BookingInfo());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_hotel_room, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomViewHolder holder, int position) {
        holder.bindData(mData.getResult().get(holder.getAdapterPosition()));
        holder.btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.btnRoom.getText().toString().equalsIgnoreCase("Select Room")) {
                    mListener.onItemClick(mData.getResult().get(holder.getAdapterPosition()), holder.getAdapterPosition());
                } else {
                    mData.getResult().get(holder.getAdapterPosition()).setSelected(false);
                    mData.getBookingInfos().set(holder.getAdapterPosition(), new BookingInfo());
                }
                notifyDataSetChanged();
                //  mListener.onItemClick(mData.getResult().get(holder.getAdapterPosition()));
            }
        });
        holder.itemView.findViewById(R.id.rl_room_details).setVisibility(mData.getResult().get(holder.getAdapterPosition()).isSelected() ? View.VISIBLE : View.GONE);
        holder.btnRoom.setText((holder.itemView.findViewById(R.id.rl_room_details).getVisibility() == View.VISIBLE) ? "Remove Room" : "Select Room");

        holder.itemView.findViewById(R.id.rl_room_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mData.getResult().get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });

        if (mData.getBookingInfos() != null && mData.getBookingInfos().get(holder.getAdapterPosition()) != null) {
            int count = 0;
            BookingInfo bookingInfo = mData.getBookingInfos().get(holder.getAdapterPosition());
            if (bookingInfo.getPersonInfos() != null) {
                for (int i = 0; i < bookingInfo.getPersonInfos().size(); i++) {
                    ArrayList<Integer> child = bookingInfo.getPersonInfos().get(i).getChild();
                    int noOfAdult1 = bookingInfo.getPersonInfos().get(i).getNoOfAdult();
                    count = count + child.size() + noOfAdult1;
                }
                ((TextView) holder.itemView.findViewById(R.id.tv_room_adult)).setText(bookingInfo.getPersonInfos().size() + " Room " + count + " Guest");
            }
        }

        holder.itemView.findViewById(R.id.tv_photo_aminites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityPhotoAminitese.class)
                        .putExtra("HOTEL_ID", mData.getResult().get(holder.getAdapterPosition()).getHotel_id())
                        .putExtra("AMIN", mData.getResult().get(holder.getAdapterPosition()).getAmenities()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mData != null && mData.getResult() != null) ? mData.getResult().size() : 0;
    }

    public void setRoomData(BookingInfo bookingInfo, int position) {
        mData.getBookingInfos().set(position, bookingInfo);
        mData.getResult().get(position).setSelected(true);
        notifyItemChanged(position);
    }

    public void setRoomPriceData(ArrayList<HotelPrice> roomPrice) {
        this.roomPrice = roomPrice;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHotelImage;
        private TextView tvRoomSize;
        private TextView tvBedType;
        private Button btnRoom;

        public RoomViewHolder(View itemView) {
            super(itemView);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvRoomSize = itemView.findViewById(R.id.tv_room_size);
            tvBedType = itemView.findViewById(R.id.tv_bed_type);
            btnRoom = itemView.findViewById(R.id.btn_select_room);
        }

        public void bindData(HotelRoomResponse.ResultBean bean) {
            Picasso.get()
                    .load((!(TextUtils.isEmpty(bean.getImage()))) ? bean.getImage() : null)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(size, size)
                    .centerInside()
                    .error(R.drawable.ic_hotel_place_holder)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .into(ivHotelImage);

            tvRoomSize.setText(bean.getRoom_size());
            tvBedType.setText(bean.getBed_type());
            ((TextView) itemView.findViewById(R.id.tv_room_name)).setText(bean.getName());

            String startPrice = getStartPrice(bean.getId());
            /*if (!TextUtils.isEmpty(startPrice)) {
                ((TextView) itemView.findViewById(R.id.tv_start_from)).setText("₹" + startPrice);
            }*/

            if (TextUtils.isEmpty(startPrice)) {
                itemView.findViewById(R.id.ll_start).setVisibility(View.GONE);
            } else {
                itemView.findViewById(R.id.ll_start).setVisibility(View.VISIBLE);
                ((TextView) itemView.findViewById(R.id.tv_start_from)).setText("₹" + startPrice);
            }
        }
    }


    public String getStartPrice(String roomId) {
        if (roomPrice != null) {
            for (int i = 0; i < roomPrice.size(); i++) {
                if (roomPrice.get(i).getRoomId().equalsIgnoreCase(roomId)) {
                    return roomPrice.get(i).getAdultPrice();
                }
            }
        }
        return "";
    }

}
