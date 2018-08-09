package com.ansh.obaazo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansh.obaazo.ActivityHotelDetails;
import com.ansh.obaazo.R;

import java.util.ArrayList;

public class AdapterHotelList extends RecyclerView.Adapter<AdapterHotelList.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mList;

    public AdapterHotelList(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_hotel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cbHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ActivityHotelDetails.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View cbHotel;

        public ViewHolder(View itemView) {
            super(itemView);
            cbHotel = itemView.findViewById(R.id.cb_hotel);
        }
    }
}
