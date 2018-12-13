package com.ansh.obaazo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        switch (holder.getAdapterPosition()) {
            case 0:
                holder.ivAmintes.setImageResource(R.drawable.ic_coffie);
                holder.tvName.setText("Breakfast");
                break;
            case 1:
                holder.ivAmintes.setImageResource(R.drawable.ic_condiction);
                holder.tvName.setText("Air Condition");

                break;
            case 2:
                holder.ivAmintes.setImageResource(R.drawable.ic_customer_service);
                holder.tvName.setText("24X7 Security");

                break;
            case 3:
                holder.ivAmintes.setImageResource(R.drawable.ic_enerfy);
                holder.tvName.setText("100% \nPower Backup");

                break;
            case 4:
                holder.ivAmintes.setImageResource(R.drawable.ic_news_paper);
                holder.tvName.setText("News Paper");

                break;
            case 5:
                holder.ivAmintes.setImageResource(R.drawable.ic_wifiy);
                holder.tvName.setText("Free Wifi");

                break;
           /* case "non-smoking":
                holder.ivAmintes.setImageResource(R.drawable.ic_coffie);
                break;
            case "Swimming pool":
                holder.ivAmintes.setImageResource(R.drawable.ic_swiming);
                break;*/
            default:
                holder.ivAmintes.setImageResource(R.drawable.ic_place_holer);
        }


    }

    @Override
    public int getItemCount() {
        return /*(mData != null) ? (mData.length < 6 ? mData.length : 6) : 0;*/
                6;
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
