package com.dotgears.berkshire.fragments;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import com.dotgears.berkshire.R;
import com.dotgears.berkshire.googleMap.GMapV2Direction;
import com.dotgears.berkshire.googleMap.GPSTracker;
import com.dotgears.berkshire.model.Hotel;
import com.dotgears.berkshire.util.MapServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by My PC on 05/12/2015.
 */
public class MapHotel extends Activity implements LocationListener ,GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;
    public ArrayList<Hotel> listHotels;
    public MapServices mapServices;
    public MarkerOptions markerOptions;
    public Marker[] markers;
    public LatLngBounds.Builder builder;
    public LatLngBounds bounds;
    LatLng fromPosition;
    LatLng toPosition;
    double lat, lng;
    GPSTracker gps;
    GMapV2Direction gMapV2Direction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_hotel);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        Intent it = this.getIntent();
        listHotels = it.getParcelableArrayListExtra("listHotel");
        builder = new LatLngBounds.Builder();

        markers = new Marker[listHotels.size()];

        for (int i = 0; i < listHotels.size(); i++) {
            mapServices = new MapServices();
            mapServices.MarkerHotel(listHotels.get(i).getHotelName(), listHotels.get(i).getHotelAddress(), markerOptions, markers[i], googleMap, Float.parseFloat(listHotels.get(i).getHotelLatitude()), Float.parseFloat(listHotels.get(i).getHotelLongitude()));

        }

        for (int i = 0; i < listHotels.size(); i++) {
            LatLng lat = new LatLng(Float.parseFloat(listHotels.get(i).hotelLatitude), Float.parseFloat(listHotels.get(i).hotelLongitude));
            builder.include(lat);
        }

        bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 25, 25, 5);
        googleMap.moveCamera(cu);

        // Maker listener



        // Get Your Location

        gps = new GPSTracker(MapHotel.this);

        gMapV2Direction = new GMapV2Direction();

        // check if GPS enabled
        if(gps.canGetLocation()){

            lat = gps.getLatitude();
            lng = gps.getLongitude();


            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lat + "\nLong: " + lng , Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }




    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        for (int i = 0; i < listHotels.size(); i++) {
//            if (marker.equals(markers[i])){
//                fromPosition = new LatLng(lat,lng);
//                toPosition = new LatLng(Float.parseFloat(listHotels.get(i).hotelLatitude),Float.parseFloat(listHotels.get(i).hotelLongitude));
//                Document doc = gMapV2Direction.getDocument(fromPosition, toPosition,
//                        GMapV2Direction.MODE_DRIVING);
//
//                String distan = gMapV2Direction.getDistanceText(doc);
//                Toast.makeText(MapHotel.this, distan, Toast.LENGTH_SHORT).show();
//            }
//        }
        Toast.makeText(MapHotel.this, "aaaaaaaa", Toast.LENGTH_SHORT).show();

        return false;
    }
}
