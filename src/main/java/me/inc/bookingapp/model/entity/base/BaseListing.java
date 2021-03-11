package me.inc.bookingapp.model.entity.base;

import me.inc.bookingapp.model.entity.Account;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseListing extends BaseEntity {

    @Column(name = "listing_title")
    private String listingTitle;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
    @JoinColumn(name = "added_from")
    private Account addedFrom;
    @Column(name = "added_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant addedOn;
    @Column(name = "is_available")
    private boolean isAvailable;

    public BaseListing() {
    }


    public String getListingTitle() {
        return listingTitle;
    }

    public BaseListing setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public Account getAddedFrom() {
        return addedFrom;
    }

    public BaseListing setAddedFrom(Account addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    public Instant getAddedOn() {
        return addedOn;
    }

    public BaseListing setAddedOn(Instant addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public BaseListing setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }
}
