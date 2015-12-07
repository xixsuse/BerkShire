package com.dotgears.berkshire.model;

/**
 * Created by My PC on 04/12/2015.
 */
public class Amenities {
    public int amenitiesID;
    public String amenitiesName;
    public String amenitiesCategory;
    public String amenitiesIcon;

    public String getAmenitiesName() {
        return amenitiesName;
    }

    public String getAmenitiesCategory() {
        return amenitiesCategory;
    }

    public void setAmenitiesID(int amenitiesID) {
        this.amenitiesID = amenitiesID;
    }

    public int getAmenitiesID() {

        return amenitiesID;
    }

    public String getAmenitiesIcon() {
        return amenitiesIcon;
    }

    public void setAmenitiesName(String amenitiesName) {
        this.amenitiesName = amenitiesName;
    }

    public void setAmenitiesCategory(String amenitiesCategory) {
        this.amenitiesCategory = amenitiesCategory;
    }

    public void setAmenitiesIcon(String amenitiesIcon) {
        this.amenitiesIcon = amenitiesIcon;
    }

}
