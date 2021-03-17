package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseListing;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "stay_listings")
public class StayListing extends BaseListing {

    @Column(name = "listing_type")
    @Enumerated(EnumType.STRING)
    private ListingType listingType = ListingType.STAY;

    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String address;

    @Column(length = 1000)
    private String description;

    @Column(name = "stay_type")
    @Enumerated(EnumType.STRING)
    private StayType stayType;

    @Column(name = "availability_left")
    private int availabilityLeft;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Picture> pictures;


    @OneToOne(mappedBy = "listing", cascade = CascadeType.ALL)
    private StayProperties listingProperties;


    public ListingType getListingType() {
        return listingType;
    }

    public StayListing setListingType(ListingType listingType) {
        this.listingType = listingType;
        return this;
    }

    public StayType getStayType() {
        return stayType;
    }

    public StayListing setStayType(StayType stayType) {
        this.stayType = stayType;
        return this;
    }

    public int getAvailabilityLeft() {
        return availabilityLeft;
    }

    public StayListing setAvailabilityLeft(int availabilityLeft) {
        this.availabilityLeft = availabilityLeft;
        return this;
    }

    public StayListing setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public StayProperties getListingProperties() {
        return listingProperties;
    }

    public StayListing setListingProperties(StayProperties listingProperties) {
        this.listingProperties = listingProperties;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public StayListing setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StayListing setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StayListing setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StayListing setDescription(String description) {
        this.description = description;
        return this;
    }
}
