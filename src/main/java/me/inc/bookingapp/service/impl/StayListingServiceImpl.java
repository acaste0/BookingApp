package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.binding.StayPropertiesBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.StayListingService;
import me.inc.bookingapp.repository.StayListingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StayListingServiceImpl implements StayListingService {

    private final AccountService accountService;
    private final StayListingRepository stayListingRepository;
    private final ModelMapper modelMapper;

    public StayListingServiceImpl(AccountService accountService, StayListingRepository stayListingRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.stayListingRepository = stayListingRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public void createListing(StayListingServiceModel listingServiceModel, String user) {
        StayListing listing = modelMapper.map(listingServiceModel, StayListing.class);
        Account account = this.accountService.getAccountEntity(user);

        listing.setAddedFrom(account);
        listing.setAvailable(true);
        listing.setPictures(listing.getPictures()
                .stream().map(p -> p.setListing(listing)).collect(Collectors.toList()));

        this.stayListingRepository.save(listing);

    }

    @Override
    public boolean titleAvailability(String title) {
        return this.stayListingRepository.findByListingTitle(title).isEmpty();
    }

    //    @PostConstruct
    @Override
    public void testInit() {
        StayListing sl = new StayListing();
        sl.setListingTitle("Ivanov Zamak");
        sl.setListingType(ListingType.STAY);
        sl.setStayType(StayType.HOTEL);
        Account ivan = accountService.getAccountEntity("ivan");
        StayProperties stayProperties = new StayProperties().setHasElevator(true).setListing(sl);
        sl.setListingProperties(stayProperties);
        sl.setAddedFrom(ivan);
        stayListingRepository.save(sl);



        // TODO: 3/14/2021 FIX LISTINGS DATABASE
    }

    @Override
    public StayPropertiesBinding getPropertiesByListingId(String id) {

        return this.modelMapper.map(this.stayListingRepository.findById(id)
                        .get().getListingProperties(),
                StayPropertiesBinding.class);

    }

    @Override
    public List<StayListingView> getAllListings() {
        List<StayListingView> allListings = new ArrayList<>();
        for (StayListing stayListing : stayListingRepository.findAll()) {
            allListings.add(modelMapper.map(stayListing, StayListingView.class));
        }

        return allListings;
    }
}
