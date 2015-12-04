package com.dotgears.berkshire.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dotgears.berkshire.R;
import com.dotgears.berkshire.adapters.HotelAdapter;
import com.dotgears.berkshire.model.Hotel;
import com.dotgears.berkshire.util.BerkShireServices;

import java.util.ArrayList;

/**
 * Created by My PC on 04/12/2015.
 */
public class HotelActivity extends Activity{
    ArrayList<Hotel> listHotels;
    public ListView listView;
    public BerkShireServices bss;
    public static HotelAdapter hotelAdapter;
    public LinearLayout linNameHotel,linPriceHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_hotel);

        linNameHotel = (LinearLayout)findViewById(R.id.linNameHotel);
        linPriceHotel = (LinearLayout)findViewById(R.id.linPriceHotel);
        Intent in = getIntent();
        String idCategoryRoom = in.getStringExtra("idCategoryRoom");
        String idLocation = in.getStringExtra("idLocation");
        bss = new BerkShireServices();
        listHotels = bss.searchHotel(idCategoryRoom,idLocation);
        listView = (ListView) findViewById(R.id.listHotel);
        listView.setDivider(null);

        hotelAdapter = new HotelAdapter(HotelActivity.this, R.layout.list_row_hotel, listHotels);
        listView.setAdapter(hotelAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String content = listHotels.get(position).getContent();
//                Intent it = new Intent(getApplicationContext(),
//                        TwoDetail.class);
//                it.putExtra("Content", content);
//                startActivity(it);
            }
        });


    }
}
