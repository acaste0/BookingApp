package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseListing;
import me.inc.bookingapp.model.entity.enums.OfferType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stay_listings")
public class StayListing extends BaseListing {

    @Column(name = "offer_type")
    @Enumerated(EnumType.STRING)
    private OfferType offerType = OfferType.STAY;

    @Column(name = "stay_type")
    @Enumerated(EnumType.STRING)
    private StayType stayType;

    @Column(name = "availability_left")
    private int availabilityLeft;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private Set<Picture> pictures;


    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private Set<StayProperties> listingProperties;


    public StayListing() {
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public StayListing setOfferType(OfferType offerType) {
        this.offerType = offerType;
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

    public Set<Picture> getPictures() {
        return pictures;
    }

    public StayListing setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public Set<StayProperties> getListingProperties() {
        return listingProperties;
    }

    public StayListing setListingProperties(Set<StayProperties> listingProperties) {
        this.listingProperties = listingProperties;
        return this;
    }
}
