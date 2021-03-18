package me.inc.bookingapp.model.service;

import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.base.BaseListing;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

public class StayListingServiceModel extends BaseListing {

    private ListingType listingType = ListingType.STAY;
    private StayType stayType;
    private String country;
    private String city;
    private String address;
    private BigDecimal pricePerNight;
    private String description;
    private int availabilityLeft;
    private MultipartFile[] pictures;
    private StayProperties stayProperties;


    public ListingType getListingType() {
        return listingType;
    }

    public StayListingServiceModel setListingType(ListingType listingType) {
        this.listingType = listingType;
        return this;
    }

    public StayType getStayType() {
        return stayType;
    }

    public StayListingServiceModel setStayType(StayType stayType) {
        this.stayType = stayType;
        return this;
    }

    public int getAvailabilityLeft() {
        return availabilityLeft;
    }

    public StayListingServiceModel setAvailabilityLeft(int availabilityLeft) {
        this.availabilityLeft = availabilityLeft;
        return this;
    }




    public String getCountry() {
        return country;
    }

    public StayListingServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StayListingServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StayListingServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StayListingServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public StayListingServiceModel setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }

    public StayProperties getStayProperties() {
        return stayProperties;
    }

    public StayListingServiceModel setStayProperties(StayProperties stayProperties) {
        this.stayProperties = stayProperties;
        return this;
    }

    public MultipartFile[] getPictures() {
        return pictures;
    }

    public StayListingServiceModel setPictures(MultipartFile[] pictures) {
        this.pictures = pictures;
        return this;
    }
}
