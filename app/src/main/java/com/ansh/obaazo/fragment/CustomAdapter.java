package com.ansh.obaazo.fragment;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ansh.obaazo.R;

class CustomAdapter extends PagerAdapter {
    private Activity activity;
    private Integer[] imageId;
    String[] imagesName;

    public CustomAdapter(FragmentActivity activity, Integer[] imageId, String[] imagesName) {
        this.activity = activity;
        this.imageId = imageId;
        this.imagesName = imagesName;

    }

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
//        return super.instantiateItem(container, position);
        LayoutInflater inflater = LayoutInflater.from(activity);
        LinearLayout bannerLayout = (LinearLayout) inflater.inflate(R.layout.banner_fragment_layout, container, false);
//        bannerLayout.setBackgroundResource(bannerArray.getResourceId(position, 0));
        ImageView bannerImage = bannerLayout.findViewById(R.id.iv_banner_image);
        bannerImage.setImageResource(imageId[position]);
        /*bannerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "swipe clicked" + position, Toast.LENGTH_LONG).show();
            }
        });*/

        container.addView(bannerLayout);
        return bannerLayout;
    }
}
