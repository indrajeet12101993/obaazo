package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.MAX_HEIGHT;
import static com.ansh.obaazo.utils.AppConstant.MAX_WIDTH;
import static com.ansh.obaazo.utils.AppConstant.size;

public class ZoomImageViewActivity extends BaseActivity {
    private HotelImageResponse mImages;
    private RecyclerView rvPhotoSlder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zoom_image_view;
    }

    @Override
    protected void initView() {
        mImages = getIntent().getParcelableExtra(AppConstant.IMAGES);

        rvPhotoSlder = findViewById(R.id.rv_photo_slide);
        rvPhotoSlder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }


    private class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ZoomImageViewActivity.this).inflate(R.layout.row_image_slider, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Picasso.get()
                    .load(mImages.getResult().get(holder.getAdapterPosition()).getImage_n())
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(size, size)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .error(R.drawable.ic_hotel_place_holder)
                    .centerInside()
                    .into(holder.ivSliderImage);


        }

        @Override
        public int getItemCount() {
            return (mImages != null && mImages.getResult() != null ? mImages.getResult().size() : 0);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivSliderImage;

            public ViewHolder(View itemView) {
                super(itemView);
                ivSliderImage = itemView.findViewById(R.id.iv_slider_images);
            }
        }
    }
}
