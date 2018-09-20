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
import com.ansh.obaazo.activity.ActivityBookRoom;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder> {
    private Context mContext;
    private HotelRoomResponse mData;


    public RoomsAdapter(Context mContext, HotelRoomResponse mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    public void setmData(HotelRoomResponse mData) {
        this.mData = mData;
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
                mContext.startActivity(new Intent(mContext, ActivityBookRoom.class).putExtra(AppConstant.BOOK_ROOM, mData.getResult().get(holder.getAdapterPosition())));
            }
        });


    }

    @Override
    public int getItemCount() {
        return (mData != null && mData.getResult() != null) ? mData.getResult().size() : 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHotelImage;
        private TextView tvRoomSize;
        private TextView tvBedType;
        private TextView tvRoomType;
        private Button btnRoom;

        public RoomViewHolder(View itemView) {
            super(itemView);
            ivHotelImage = itemView.findViewById(R.id.iv_hotel_image);
            tvRoomSize = itemView.findViewById(R.id.tv_room_size);
            tvBedType = itemView.findViewById(R.id.tv_bed_type);
            tvRoomType = itemView.findViewById(R.id.tv_room_type);
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
            tvRoomType.setText(bean.getRoom_type());
        }
    }
}
