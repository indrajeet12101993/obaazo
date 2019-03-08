package com.ansh.obaazo.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.activity.ActivityDateSelecte;
import com.ansh.obaazo.activity.ActivitySearch;
import com.ansh.obaazo.activity.ActivitySelect;
import com.ansh.obaazo.activity.BaseActivity;
import com.ansh.obaazo.activity.LocationActivity;
import com.ansh.obaazo.adapter.OfferAdapter;
import com.ansh.obaazo.adapter.TreandingAdapter;
import com.ansh.obaazo.model.BookingInfo;
import com.ansh.obaazo.model.HotelInfo;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.OfferResponse;
import com.ansh.obaazo.resources.response.TrendingHotelResponse;
import com.ansh.obaazo.resources.service.OfferService;
import com.ansh.obaazo.resources.service.TrendingHotelService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.DateUtils;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = FragmentHome.class.getSimpleName();
    private final int REQUEST_CODE_AUTOCOMPLETE = 1002;
    private View mView;
    private RecyclerView rvTreading;
    private RecyclerView rvOffer;

    private TreandingAdapter adapter;

    private ArrayList<HotelInfo> mList = new ArrayList<>();
    private TextView tvStartDate;
    private TextView tvEndDate;

    private Button btnSearch;
    private TextView tvACount;
    private TextView tvCount;
    private int childCount = 0;
    private int adultCount = 1;
    private TextView etPlace;
    private OfferAdapter offerAdapter;
    private boolean isLocationSelected = false;
    private boolean isSDateSelected = false;
    private boolean isEDateSelected = false;
    private boolean isPersonSelected = false;
    private TextView tvRoomAdult;
    private BookingInfo bookingInfo = new BookingInfo();


    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return mView;
    }

    @Override
    protected void initView() {
        Picasso.get()
                .load("https://obaazo.com//assets/img/home_1/wp2003997.jpg")
                .error(R.drawable.ic_hotel_place_holder)
                .placeholder(R.drawable.ic_hotel_place_holder)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(400, 200)
                .into((ImageView) mView.findViewById(R.id.app_banner));

        etPlace = mView.findViewById(R.id.et_place);
        rvTreading = mView.findViewById(R.id.rv_trending);
        rvOffer = mView.findViewById(R.id.rv_offer);

        tvStartDate = mView.findViewById(R.id.tv_start_date);
        tvEndDate = mView.findViewById(R.id.tv_end_date);
        tvACount = mView.findViewById(R.id.tv_a_count);
        tvCount = mView.findViewById(R.id.tv_c_count);

        tvRoomAdult = mView.findViewById(R.id.tv_room_adult);


        offerAdapter = new OfferAdapter(getActivity(), new OfferResponse());
        rvOffer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvOffer.setNestedScrollingEnabled(false);
        rvOffer.setAdapter(offerAdapter);

        adapter = new TreandingAdapter(getActivity(), mList);
        rvTreading.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTreading.setNestedScrollingEnabled(false);
        rvTreading.setAdapter(adapter);

    }

    @Override
    protected void initListener() {
        btnSearch = mView.findViewById(R.id.btn_search);
   /*     NestedScrollView nsView = mView.findViewById(R.id.ns_home);
        nsView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");
                    ((BaseActivity) getActivity()).slideDown();
                }
                if (scrollY < oldScrollY && scrollY == 0) {
                    Log.i(TAG, "Scroll UP");
                    ((BaseActivity) getActivity()).slideUp();
                }

                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i(TAG, "BOTTOM SCROLL");
                }
            }
        });*/

        etPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocationPicker();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    startActivity(new Intent(getActivity(), ActivitySearch.class).putExtra(AppConstant.START_DATE, "")
                            .putExtra(AppConstant.END_DATE, "")
                            .putExtra(AppConstant.NO_OF_ROOM, 1)
                            .putExtra(AppConstant.NO_OF_ADULT, adultCount)
                            .putExtra(AppConstant.NO_OF_CHILD, childCount));
                }

            }
        });


        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), ActivityDateSelecte.class), 1003);
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), ActivityDateSelecte.class), 1003);
            }
        });


        tvRoomAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivitySelect.class);
                if (bookingInfo != null) {
                    intent.putExtra(AppConstant.PERSON_DETAILS, new Gson().toJson(bookingInfo));
                }
                startActivityForResult(intent, 1004);
            }
        });

        mView.findViewById(R.id.iv_current_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), LocationActivity.class), 1001);
            }
        });
    }


    private boolean validate() {
        if (!isLocationSelected) {
            Toast.makeText(mView.getContext(), "Please select location", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isSDateSelected) {
            Toast.makeText(mView.getContext(), "Please select start date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEDateSelected) {
            Toast.makeText(mView.getContext(), "Please select end date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tvRoomAdult.getText().toString().equalsIgnoreCase("Enter Guest Details")) {
            Toast.makeText(mView.getContext(), "Please select Guest Details", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    protected void bindDataWithUi() {
        hitOfferApi();
        hitTrendingApi();
    }

    private void hitOfferApi() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showLoadingDialog();
        new OfferService(getActivity()).execute(new BaseRequest(), new ApiCallback<OfferResponse>() {
            @Override
            public void onSuccess(Call<OfferResponse> call, OfferResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200") && response.getResult() != null) {
                    offerAdapter.setmList(response);
                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                if (getActivity() != null)
                    ((BaseActivity) getActivity()).hideLoadingDialog();


            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(getContext(), "Api Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void hitTrendingApi() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showLoadingDialog();
        new TrendingHotelService(getActivity()).execute(new BaseRequest(), new ApiCallback<TrendingHotelResponse>() {
            @Override
            public void onSuccess(Call<TrendingHotelResponse> call, TrendingHotelResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200")) {
                    adapter.setmList(response.getResult());
                } else {
                    Toast.makeText(getActivity(), response.getResponse_message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {
                if (getActivity() != null)
                    ((BaseActivity) getActivity()).hideLoadingDialog();
            }

            @Override
            public void onFailure(ApiException e) {

            }
        });
    }

    private void openLocationPicker() {
        if (getActivity() == null) return;
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                    .setCountry("IN")
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(typeFilter)
                    .build(getActivity());
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            //    GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(), 0).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == Activity.RESULT_OK) {
                com.google.android.gms.location.places.Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                try {
                    isLocationSelected = true;
                    etPlace.setText((String) place.getAddress());
                    PreferencesUtils.putDouble(AppConstant.B_LATITUDE, place.getLatLng().latitude);
                    PreferencesUtils.putDouble(AppConstant.B_LONGITUDE, place.getLatLng().longitude);
                    PreferencesUtils.putString(AppConstant.B_LOCATION, String.valueOf(place.getAddress()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(getActivity(), "Failed to get Location", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            isLocationSelected = true;
            double latitude = data.getDoubleExtra(AppConstant.B_LATITUDE, 0);
            double longitude = data.getDoubleExtra(AppConstant.B_LONGITUDE, 0);
            String location = data.getStringExtra(AppConstant.B_LOCATION);
            PreferencesUtils.putDouble(AppConstant.B_LATITUDE, latitude);
            PreferencesUtils.putDouble(AppConstant.B_LONGITUDE, longitude);
            PreferencesUtils.putString(AppConstant.B_LOCATION, location);
            etPlace.setText(location);

        }

        if (requestCode == 1003 && resultCode == Activity.RESULT_OK) {
            isSDateSelected = true;
            isEDateSelected = true;
            tvStartDate.setText(DateUtils.parseDate(PreferencesUtils.getString(AppConstant.START_DATE)));
            tvEndDate.setText(DateUtils.parseDate(PreferencesUtils.getString(AppConstant.END_DATE)));
        }

        if (requestCode == 1004 && resultCode == Activity.RESULT_OK) {
            isPersonSelected = true;
            String stringExtra = data.getStringExtra(AppConstant.PERSON_DETAILS);
            PreferencesUtils.putString(AppConstant.BOOKING_DETAILS, stringExtra);
            //  String personDetails = PreferencesUtils.getString(AppConstant.BOOKING_DETAILS);
            if (!TextUtils.isEmpty(stringExtra)) {
                bookingInfo = new Gson().fromJson(stringExtra, BookingInfo.class);
                int count = 0;
                if (bookingInfo.getPersonInfos() != null)
                    for (int i = 0; i < bookingInfo.getPersonInfos().size(); i++) {
                        ArrayList<Integer> child = bookingInfo.getPersonInfos().get(i).getChild();
                        int noOfAdult1 = bookingInfo.getPersonInfos().get(i).getNoOfAdult();
                        count = count + child.size() + noOfAdult1;
                    }
                tvRoomAdult.setText(bookingInfo.getPersonInfos().size() + " Room " + count + " Guest");
            }

        }
    }


    public boolean checkGPS() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
       /* if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }*/
    }


}
