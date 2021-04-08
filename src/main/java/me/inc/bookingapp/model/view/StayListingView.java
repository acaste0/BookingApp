package me.inc.bookingapp.model.view;

import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StayListingView {
    private String id;
    private String firstPicture;
    private String listingTitle;
    private String country;
    private String city;
    private String address;
    private String description;
    private Instant createdOn;
    private String addedFrom;
    private BigDecimal pricePerNight;
    private int contactNumber;
    private ListingType listingType = ListingType.STAY;
    private StayType stayType;
    private int availabilityLeft;
    private List<PictureView> pictures;
    private StayPropertiesView stayProperties;

    public String getListingTitle() {
        return listingTitle;
    }

    public StayListingView setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public String getAddedFrom() {
        return addedFrom;
    }

    public StayListingView setAddedFrom(String addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public StayListingView setListingType(ListingType listingType) {
        this.listingType = listingType;
        return this;
    }

    public StayType getStayType() {
        return stayType;
    }

    public StayListingView setStayType(StayType stayType) {
        this.stayType = stayType;
        return this;
    }

    public int getAvailabilityLeft() {
        return availabilityLeft;
    }

    public StayListingView setAvailabilityLeft(int availabilityLeft) {
        this.availabilityLeft = availabilityLeft;
        return this;
    }

    public List<PictureView> getPictures() {
        return pictures;
    }

    public StayListingView setPictures(List<PictureView> pictures) {
        this.pictures = pictures;
        return this;
    }

    public StayPropertiesView getStayProperties() {
        return stayProperties;
    }

    public StayListingView setStayProperties(StayPropertiesView stayProperties) {
        this.stayProperties = stayProperties;
        return this;
    }


    public String getId() {
        return id;
    }

    public StayListingView setId(String id) {
        this.id = id;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public StayListingView setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public StayListingView setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StayListingView setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StayListingView setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StayListingView setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public StayListingView setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public StayListingView setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public StayListingView setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }
}
