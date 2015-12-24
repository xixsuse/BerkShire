package com.dotgears.berkshire.util;

import android.os.Parcel;

import com.dotgears.berkshire.model.Amenities;
import com.dotgears.berkshire.model.Hotel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by My PC on 04/12/2015.
 */
public class BerkShireServices {
    public String URL_DEFAULT = "http://berkshirehathaway.vn/api/v1/bba.php/hotels/";
    public String SEARCH_HOTEL = "searchHotels/";
    public String GET_AMENITIES = "getAmenities/";


    public JSONAsynTask jsonAT;
    public JSONArray jsonA;

    //    SEARCH HOTEL
    public ArrayList<Hotel> searchHotel(String idCategoryRoom,String idLocation) {
        jsonAT = new JSONAsynTask();
        ArrayList<Hotel> listHotels = new ArrayList<Hotel>();
        jsonAT.execute(URL_DEFAULT + SEARCH_HOTEL +idCategoryRoom+"/"+idLocation);
        try {
            jsonA = new JSONArray(jsonAT.get());
            for (int i = 0; i < jsonA.length(); i++) {
                Hotel hotel = new Hotel(Parcel.obtain());
                JSONObject childJSONObject = jsonA.getJSONObject(i);
                hotel.setHotelID(childJSONObject.getInt("ID"));
                hotel.setHotelName(childJSONObject.getString("Name"));
                hotel.setHotelAddress(childJSONObject
                        .getString("Address"));
                hotel.setHotelThumnail(childJSONObject.getString("Thumbnail"));
                hotel.setHotelSlideImage(childJSONObject.getString("SlideImage"));
                hotel.setHotelPhone(childJSONObject
                        .getString("Phone"));
                hotel.setHotelLocation(childJSONObject
                        .getString("Location"));
                hotel.setHotelLatitude(childJSONObject
                        .getString("Latitude"));
                hotel.setHotelLongitude(childJSONObject
                        .getString("Longitude"));
                hotel.setHotelPrice(childJSONObject
                        .getString("Price"));
                listHotels.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHotels;
    }
    //    GET AMENITIES
    public ArrayList<Amenities> getAmenities() {
        jsonAT = new JSONAsynTask();
        ArrayList<Amenities> listAmenities = new ArrayList<Amenities>();
        jsonAT.execute(URL_DEFAULT + GET_AMENITIES);
        try {
            jsonA = new JSONArray(jsonAT.get());
            for (int i = 0; i < jsonA.length(); i++) {
                Amenities amenitie = new Amenities();
                JSONObject childJSONObject = jsonA.getJSONObject(i);
                amenitie.setAmenitiesName(childJSONObject.getString("Name"));
                amenitie.setAmenitiesCategory(childJSONObject
                        .getString("AmenitiesCategory"));
                amenitie.setAmenitiesIcon(childJSONObject.getString("Icon"));
                listAmenities.add(amenitie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAmenities;
    }
    //add User
//    public void addUser(String codePrivate,String gameID) throws UnsupportedEncodingException {
//        jsonAT = new JSONAsynTask();
//        String url = ADDUSER+encodeString(codePrivate)+"/"+gameID;
//        jsonAT.execute(url);
//    }
    // Encode
//    public String encodeString(String str) throws UnsupportedEncodingException {
//        String enco = URLEncoder.encode(str, "UTF-8");
//        return enco;
//    }
}
