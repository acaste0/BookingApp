package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.enums.ListingType;

public class ListingStartBinding {
    private String listingTitle;
    private ListingType listingType;



    public ListingType getListingType() {
        return listingType;
    }

    public ListingStartBinding setListingType(ListingType listingType) {
        this.listingType = listingType;
        return this;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public ListingStartBinding setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }
}
