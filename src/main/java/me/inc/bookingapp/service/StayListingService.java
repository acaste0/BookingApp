package me.inc.bookingapp.service;

import me.inc.bookingapp.model.binding.StayPropertiesBinding;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.model.view.StayListingView;

import java.io.IOException;
import java.util.List;

public interface StayListingService {


    void createListing(StayListingServiceModel listing, String user) throws IOException;
    boolean titleAvailability(String title);
    StayPropertiesBinding getPropertiesByListingId(String id);

    List<StayListingView> getAllListings();

    StayListingView getById(String id);
    List<StayListingView> getAll();

    void deleteById(String id);

    StayListing findEntityById(String listingId);

    StayListing findEntityByTitle(String title);
}
