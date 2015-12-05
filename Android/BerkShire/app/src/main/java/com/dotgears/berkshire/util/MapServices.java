package com.dotgears.berkshire.util;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by My PC on 05/12/2015.
 */
public class MapServices {

    public void MarkerHotel(String hotelName,String address,MarkerOptions markerOptions,Marker marker,GoogleMap googleMap,Float latitude,Float longitude){
        LatLng latLng = new LatLng(latitude, longitude);
        markerOptions =new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(hotelName).snippet(address);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.alpha(0.8f);
//        markerOptions.rotation(90);
        marker = googleMap.addMarker(markerOptions);
        marker.showInfoWindow();
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

    }
}
