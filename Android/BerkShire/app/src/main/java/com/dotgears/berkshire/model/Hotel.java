package com.dotgears.berkshire.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by My PC on 04/12/2015.
 */
public class Hotel implements Parcelable ,Serializable{
    public int hotelID;



    public String hotelName;
    public String hotelAddress;
    public String hotelThumnail;
    public String hotelSlideImage;
    public String hotelPhone;
    public String hotelLocation;
    public String hotelLatitude;
    public String hotelLongitude;
    public String hotelPrice;

    public Hotel(Parcel in) {
        this.hotelID = in.readInt();
        this.hotelName = in.readString();
        this.hotelAddress = in.readString();
        this.hotelThumnail = in.readString();
        this.hotelSlideImage = in.readString();
        this.hotelPhone = in.readString();
        this.hotelLocation = in.readString();
        this.hotelLatitude = in.readString();
        this.hotelLongitude = in.readString();
        this.hotelPrice = in.readString();
    }

    public String getHotelSlideImage() {
        return hotelSlideImage;
    }

    public void setHotelSlideImage(String hotelSlideImage) {
        this.hotelSlideImage = hotelSlideImage;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getHotelID() {

        return hotelID;
    }
    public String getHotelPrice() {
        return hotelPrice;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelThumnail() {
        return hotelThumnail;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public String getHotelLatitude() {
        return hotelLatitude;
    }

    public String getHotelLongitude() {
        return hotelLongitude;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public void setHotelThumnail(String hotelThumnail) {
        this.hotelThumnail = hotelThumnail;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public void setHotelLatitude(String hotelLatitude) {
        this.hotelLatitude = hotelLatitude;
    }

    public void setHotelLongitude(String hotelLongitude) {
        this.hotelLongitude = hotelLongitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hotelID);
        dest.writeString(hotelName);
        dest.writeString(hotelAddress);
        dest.writeString(hotelPhone);
        dest.writeString(hotelThumnail);
        dest.writeString(hotelSlideImage);
        dest.writeString(hotelLocation);
        dest.writeString(hotelLatitude);
        dest.writeString(hotelLongitude);
        dest.writeString(hotelPrice);
    }
    private void readFromParcel(Parcel in) {
        hotelID = in.readInt();
        hotelName = in.readString();
        hotelAddress = in.readString();
        hotelPhone = in.readString();
        hotelThumnail = in.readString();
        hotelSlideImage = in.readString();
        hotelLocation = in.readString();
        hotelLatitude = in.readString();
        hotelLongitude = in.readString();
        hotelPrice = in.readString();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
}
