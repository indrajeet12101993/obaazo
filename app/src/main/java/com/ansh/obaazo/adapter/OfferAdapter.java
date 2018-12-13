package com.ansh.obaazo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.OfferResponse;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private Context mContext;
    private OfferResponse mList;

    public OfferAdapter(Context mContext, OfferResponse mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    public void setmList(OfferResponse mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfferViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        OfferResponse.ResultBean bean = mList.getResult().get(holder.getAdapterPosition());
        Picasso.get()
                .load((!(TextUtils.isEmpty(bean.getOffer_image()))) ? bean.getOffer_image() : null)
                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(size, size)
                .centerInside()
                .error(R.drawable.ic_hotel_place_holder)
                .placeholder(R.drawable.ic_hotel_place_holder)
                .into(holder.ivOfferImage);


    }

    @Override
    public int getItemCount() {
        return (mList != null ? (mList.getResult() != null ? mList.getResult().size() : 0) : 0);
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivOfferImage;

        public OfferViewHolder(View itemView) {
            super(itemView);
            ivOfferImage = itemView.findViewById(R.id.iv_offer_image);
        }
    }
}
