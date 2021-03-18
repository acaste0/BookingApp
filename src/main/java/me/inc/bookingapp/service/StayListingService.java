package me.inc.bookingapp.service;

import me.inc.bookingapp.model.binding.StayPropertiesBinding;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.model.view.StayListingView;

import java.util.List;

public interface StayListingService {


    void createListing(StayListingServiceModel listing, String user);
    boolean titleAvailability(String title);
    StayPropertiesBinding getPropertiesByListingId(String id);

    List<StayListingView> getAllListings();

    StayListingView getById(String id);
}
