package com.dotgears.berkshire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dotgears.berkshire.R;
import com.dotgears.berkshire.adapters.HotelAdapter;
import com.dotgears.berkshire.fragments.MapHotel;
import com.dotgears.berkshire.model.Hotel;
import com.dotgears.berkshire.util.BerkShireServices;

import java.util.ArrayList;

/**
 * Created by My PC on 04/12/2015.
 */
public class HotelActivity extends AppCompatActivity {
    ArrayList<Hotel> listHotels;
    public ListView listView;
    public BerkShireServices bss;
    public static HotelAdapter hotelAdapter;
    public LinearLayout linNameHotel, linPriceHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_hotel);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BerkShire");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        linNameHotel = (LinearLayout) findViewById(R.id.linNameHotel);
        linPriceHotel = (LinearLayout) findViewById(R.id.linPriceHotel);
        Intent in = getIntent();
        String idCategoryRoom = in.getStringExtra("idCategoryRoom");
        String idLocation = in.getStringExtra("idLocation");
        bss = new BerkShireServices();
        listHotels = bss.searchHotel(idCategoryRoom, idLocation);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
//        menu.findItem(R.id.action_settings).setIcon(GoogleMaterial.Icon.gmd_plus);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mapHotel:
                Intent intent = new Intent(getApplicationContext(), MapHotel.class);
                intent.putParcelableArrayListExtra("listHotel", listHotels);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
