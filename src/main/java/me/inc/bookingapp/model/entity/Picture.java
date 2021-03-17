package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;
import me.inc.bookingapp.model.entity.base.BaseListing;

import javax.persistence.*;

@Entity
@Table(name = "listing_pictures")
public class Picture extends BaseEntity {

    @Column(name = "picture_url")
    private String pictureUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private StayListing listing;

    public Picture() {
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Picture setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public StayListing getListing() {
        return listing;
    }

    public Picture setListing(StayListing listing) {
        this.listing = listing;
        return this;
    }
}
