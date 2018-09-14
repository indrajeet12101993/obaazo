package com.ansh.obaazo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansh.obaazo.R;

public class AmentiesAdapter extends RecyclerView.Adapter<AmentiesAdapter.ViewHolder> {
    private Context mContext;
    private String[] mData;

    public AmentiesAdapter(Context mContext, String[] mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setmData(String tempData) {
        if (!TextUtils.isEmpty(tempData)) {
            this.mData = tempData.split(",");
            notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_amenties, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mData[holder.getAdapterPosition()];
        holder.tvName.setText(name);
        switch (name) {
            case "non-smoking":
                holder.ivAmintes.setImageResource(R.drawable.ic_coffie);
                break;
            case "Swimming pool":
                holder.ivAmintes.setImageResource(R.drawable.ic_swiming);
                break;
            default:
                holder.ivAmintes.setImageResource(R.drawable.ic_place_holer);
        }


    }

    @Override
    public int getItemCount() {
        return (mData != null) ? (mData.length < 6 ? mData.length : 6) : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAmintes;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAmintes = itemView.findViewById(R.id.iv_aminites);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
