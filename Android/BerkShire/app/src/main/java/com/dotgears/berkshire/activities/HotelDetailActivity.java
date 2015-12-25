package com.dotgears.berkshire.activities;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dotgears.berkshire.R;
import com.dotgears.berkshire.model.Hotel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.util.HashMap;

/**
 * Created by My PC on 24/12/2015.
 */
public class HotelDetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    //    public ArrayList<Hotel> listHotels;
    private static final String FRAGMENT_NORMAL_TAB_DEMO = "normal tab demo";
    private SliderLayout mDemoSlider;
    public Hotel hotel;
    public int idHotel;
    public String[] slideImg;
    private GoogleMap googleMap;
    public TextView hotelDetailName, hotelDetailPrice;
    TabHost tabHost;
    LocalActivityManager mLocalActivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_detail);

        Bundle extras = getIntent().getExtras();
//        listHotels = it.getParcelableArrayListExtra("listHotel");
        idHotel = extras.getInt("idHotel");
        hotel = (Hotel) extras.getSerializable("hotel");
        slideImg = splitStringImg(hotel.getHotelSlideImage());
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        hotelDetailName = (TextView) findViewById(R.id.hotelDetailName);
        hotelDetailName.setText(hotel.getHotelName());
        hotelDetailPrice = (TextView) findViewById(R.id.hotelDetailPrice);
        hotelDetailPrice.setText(hotel.getHotelPrice() + " / per night");

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("ImageOne", slideImg[0]);
        url_maps.put("ImageTwo", slideImg[1]);
        url_maps.put("ImageThree", slideImg[2]);
        url_maps.put("ImageFour", slideImg[3]);
        url_maps.put("ImageFive", slideImg[4]);


        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.hotelDeitalMap);
        googleMap = fm.getMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
    ///////// TAB /////////////////
        initTabs(savedInstanceState);
    }
    private void initTabs(Bundle savedInstanceState) {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);     // The activity TabHost
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);

        TabHost.TabSpec hotel,room,food;                               // Resusable TabSpec for each tab
        Intent intent;                                      // Reusable Intent for each tab

        intent = new Intent().setClass(this, SignIn.class);
        hotel = tabHost.newTabSpec("Hotel").setIndicator("Hotel")
                .setContent(intent);
        tabHost.addTab(hotel);

        intent = new Intent().setClass(this, SignUp.class);
        room = tabHost.newTabSpec("Room").setIndicator("Room")
                .setContent(intent);
        tabHost.addTab(room);


        intent = new Intent().setClass(this, SignIn.class);
        food = tabHost.newTabSpec("Food").setIndicator("Food")
                .setContent(intent);
        tabHost.addTab(food);

        tabHost.setCurrentTab(0);
    }



    public String[] splitStringImg(String root) {

        String[] slideImg = root.split("\\,");

        return slideImg;
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
//        Log.d("Slider", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
