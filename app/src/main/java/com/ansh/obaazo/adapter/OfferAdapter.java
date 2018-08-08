package com.ansh.obaazo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansh.obaazo.R;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private Context mContext;
    private ArrayList<String> mList;

    public OfferAdapter(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfferViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_offer,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        public OfferViewHolder(View itemView) {
            super(itemView);
        }
    }
}
