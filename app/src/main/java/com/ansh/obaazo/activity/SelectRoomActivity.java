package com.ansh.obaazo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.adapter.RoomsAdapter;
import com.ansh.obaazo.resources.request.BaseRequest;
import com.ansh.obaazo.resources.response.HotelRoomResponse;
import com.ansh.obaazo.resources.service.HotelRoomService;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.web.ApiCallback;
import com.ansh.obaazo.web.ApiException;

import retrofit2.Call;

public class SelectRoomActivity extends BaseActivity {
    private RecyclerView rvRooms;
    private RoomsAdapter roomsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_selecte_room;
    }

    @Override
    protected void initView() {
        rvRooms = findViewById(R.id.rv_rooms);
        rvRooms.setLayoutManager(new LinearLayoutManager(this));
        roomsAdapter = new RoomsAdapter(this, new HotelRoomResponse());
        rvRooms.setAdapter(roomsAdapter);
        hitRoomApi();


    }

    private void hitRoomApi() {
        showLoadingDialog();
        BaseRequest request = new BaseRequest();
        String hotelId = getIntent().getStringExtra(AppConstant.HOTEL_ID);
        request.setId(hotelId);
        new HotelRoomService(this).execute(request, new ApiCallback<HotelRoomResponse>() {
            @Override
            public void onSuccess(Call<HotelRoomResponse> call, HotelRoomResponse response) {
                if (response.getResponse_code().equalsIgnoreCase("200"))
                    roomsAdapter.setmData(response);
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();

            }

            @Override
            public void onFailure(ApiException e) {
                Toast.makeText(SelectRoomActivity.this, "Api Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void bindDataWithUi() {

    }
}
