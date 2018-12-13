package com.ansh.obaazo.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ansh.obaazo.R;
import com.ansh.obaazo.resources.response.HotelImageResponse;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.ScreenUtils;
import com.squareup.picasso.Picasso;

import static com.ansh.obaazo.utils.AppConstant.size;

public class ZoomImageViewActivity extends BaseActivity {
    private HotelImageResponse mImages;
    private RecyclerView rvPhotoSlder;
    //  private RecyclerView rvImage;
    private ImageView ivImage;
    private String hotelName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zoom_image_view;
    }


    @Override
    protected void initView() {
        mImages = getIntent().getParcelableExtra(AppConstant.IMAGES);
        int position = getIntent().getIntExtra(AppConstant.POSITION, 0);
        hotelName = getIntent().getStringExtra(AppConstant.HOTEL_DETAILS);
        initCustomToolbar();


        rvPhotoSlder = findViewById(R.id.rv_photo_slide);
        rvPhotoSlder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SliderAdapter adapter = new SliderAdapter();
        rvPhotoSlder.setAdapter(adapter);


        ivImage = findViewById(R.id.iv_images);

        if (mImages.getResult() != null && mImages.getResult().size() > 0) {
            setImage(mImages.getResult().get(position).getImage_n());
            rvPhotoSlder.smoothScrollToPosition(position);
        }


       /* rvImage = findViewById(R.id.rv_image);
        rvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ImageAdapter imageAdapter = new ImageAdapter();
        rvImage.setAdapter(imageAdapter);*/

    }

    @Override
    public String setToolbarName() {
        return hotelName;
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
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            Picasso.get()
                    .load(mImages.getResult().get(holder.getAdapterPosition()).getImage_n())
                    .resize(size, size)
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .error(R.drawable.ic_hotel_place_holder)
                    .centerInside()
                    .into(holder.ivSliderImage);
            holder.ivSliderImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setImage(mImages.getResult().get(holder.getAdapterPosition()).getImage_n());
                    //   rvImage.smoothScrollToPosition(holder.getAdapterPosition());
                }
            });


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

    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ImageViewHolder(LayoutInflater.from(ZoomImageViewActivity.this).inflate(R.layout.row_image_view, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

            Picasso.get()
                    .load(mImages.getResult().get(holder.getAdapterPosition()).getImage_n())
                    .placeholder(R.drawable.ic_hotel_place_holder)
                    .error(R.drawable.ic_hotel_place_holder)
                    .into(holder.ivImages);
            holder.cvImageView.getLayoutParams().height = ScreenUtils.getScreenWidth(ZoomImageViewActivity.this);
        }

        @Override
        public int getItemCount() {
            return (mImages != null && mImages.getResult() != null ? mImages.getResult().size() : 0);
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivImages;
            private CardView cvImageView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                ivImages = itemView.findViewById(R.id.iv_images);
                cvImageView = itemView.findViewById(R.id.cv_image_view);
            }
        }
    }


    public void setImage(String url) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_place_holer)
                .error(R.drawable.ic_place_holer)
                .into(ivImage);
    }
}
