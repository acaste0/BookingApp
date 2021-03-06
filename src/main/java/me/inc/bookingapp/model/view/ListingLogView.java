package me.inc.bookingapp.model.view;

import java.time.Instant;

public class ListingLogView {

    private String id;
    private String account;
    private String action;
    private String listingTitle;
    private Instant createdOn;

    public String getId() {
        return id;
    }

    public ListingLogView setId(String id) {
        this.id = id;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public ListingLogView setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ListingLogView setAction(String action) {
        this.action = action;
        return this;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public ListingLogView setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public ListingLogView setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
