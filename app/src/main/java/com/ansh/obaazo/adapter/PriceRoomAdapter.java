package com.ansh.obaazo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.listener.IItemClick;
import com.ansh.obaazo.listener.ItemClickNotiffy;
import com.ansh.obaazo.model.MBooking;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Jeevan Gupta on 12/7/2018.
 */
public class PriceRoomAdapter extends RecyclerView.Adapter<PriceRoomAdapter.PriceViewHolder> {
    private Context mContext;
    private ArrayList<MBooking> mData;
    private ItemClickNotiffy listener;

    public PriceRoomAdapter(Context mContext, ArrayList<MBooking> mData, ItemClickNotiffy itemClick) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener = itemClick;

    }

    public void setmData(ArrayList<MBooking> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public ArrayList<MBooking> getmData() {
        return mData;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PriceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_room_price, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PriceViewHolder holder, int position) {
        MBooking booking = mData.get(position);
        holder.tvPerson.setText(booking.getAdultCount() + " Adult " + booking.getChildCount() + " Child ");
        holder.tvPrice.setText(booking.getRoomPriceWithoutGst() + "");

        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                listener.onItemClick(-1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
        //return 3;
    }

    public class PriceViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPrice, tvPerson;
        private ImageView ivRemove;

        public PriceViewHolder(View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPerson = itemView.findViewById(R.id.tv_person);
            ivRemove = itemView.findViewById(R.id.iv_remove);

        }
    }
}
