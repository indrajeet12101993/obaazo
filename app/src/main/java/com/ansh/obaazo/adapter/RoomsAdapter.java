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
import com.ansh.obaazo.activity.ActivitySelect;
import com.ansh.obaazo.listener.IItemClick;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder> {
    private Context mContext;
    private HotelRoomResponse mData;
    private IItemClick<HotelRoomResponse.ResultBean> mListener;


    public RoomsAdapter(Context mContext, HotelRoomResponse mData, IItemClick<HotelRoomResponse.ResultBean> mListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mListener = mListener;
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
                if (holder.btnRoom.getText().toString().equalsIgnoreCase("Select Room")) {
                    //    mContext.startActivity(new Intent(mContext, ActivitySelect.class));
                    mData.getResult().get(holder.getAdapterPosition()).setSelected(true);
                } else {
                    mData.getResult().get(holder.getAdapterPosition()).setSelected(false);
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
                mContext.startActivity(new Intent(mContext, ActivitySelect.class));
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
        }
    }


}
