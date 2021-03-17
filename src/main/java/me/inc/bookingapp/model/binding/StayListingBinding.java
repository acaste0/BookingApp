package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StayListingBinding {


    private String listingTitle;
    private ListingType listingType = ListingType.STAY.STAY;
    private StayType stayType;
    private int availabilityLeft;
    private List<Picture> pictures = new ArrayList<>();
    private StayProperties stayProperties;
    private String country;
    private String city;
    private String address;

    public String getListingTitle() {
        return listingTitle;
    }

    public StayListingBinding setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public StayListingBinding setListingType(ListingType listingType) {
        this.listingType = listingType;
        return this;
    }

    public StayType getStayType() {
        return stayType;
    }

    public StayListingBinding setStayType(StayType stayType) {
        this.stayType = stayType;
        return this;
    }

    public int getAvailabilityLeft() {
        return availabilityLeft;
    }

    public StayListingBinding setAvailabilityLeft(int availabilityLeft) {
        this.availabilityLeft = availabilityLeft;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public StayListingBinding setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public StayProperties getStayProperties() {
        return stayProperties;
    }

    public StayListingBinding setStayProperties(StayProperties stayProperties) {
        this.stayProperties = stayProperties;
        return this;
    }
    public void addPicture(Picture picture) {
        this.pictures.add(picture);
    }


    public String getCountry() {
        return country;
    }

    public StayListingBinding setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StayListingBinding setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StayListingBinding setAddress(String address) {
        this.address = address;
        return this;
    }
}
