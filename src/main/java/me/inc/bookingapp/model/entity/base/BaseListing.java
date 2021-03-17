package me.inc.bookingapp.model.entity.base;

import me.inc.bookingapp.model.entity.Account;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseListing extends BaseEntity {

    @Column(name = "listing_title", unique = true)
    private String listingTitle;
    @ManyToOne
    @JoinColumn(name = "added_from")
    private Account addedFrom;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public BaseListing setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }
}
