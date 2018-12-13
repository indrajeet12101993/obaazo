package com.ansh.obaazo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansh.obaazo.R;
import com.ansh.obaazo.model.BookingInfo;

import java.util.ArrayList;

/**
 * Created by Jeevan Gupta on 12/7/2018.
 */
public class PriceRoomAdapter extends RecyclerView.Adapter<PriceRoomAdapter.PriceViewHolder> {
    private Context mContext;
    private ArrayList<BookingInfo> mData;


    public PriceRoomAdapter(Context mContext, ArrayList<BookingInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PriceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_room_price, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class PriceViewHolder extends RecyclerView.ViewHolder {
        public PriceViewHolder(View itemView) {
            super(itemView);
        }
    }
}
