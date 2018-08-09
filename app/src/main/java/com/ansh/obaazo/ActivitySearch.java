package com.ansh.obaazo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ansh.obaazo.adapter.AdapterHotelList;

import java.util.ArrayList;

public class ActivitySearch extends AppCompatActivity {

    private RecyclerView rvHotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rvHotelList = findViewById(R.id.rv_hotel_list);
        rvHotelList.setLayoutManager(new LinearLayoutManager(ActivitySearch.this));
        AdapterHotelList hotelList = new AdapterHotelList(this,new ArrayList<String>());
        rvHotelList.setAdapter(hotelList);


    }

}
