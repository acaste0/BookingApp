package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.binding.StayListingBinding;
import me.inc.bookingapp.model.binding.StayPropertiesBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.repository.PicturesRepository;
import me.inc.bookingapp.repository.StayPropertiesRepository;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.CloudService;
import me.inc.bookingapp.service.StayListingService;
import me.inc.bookingapp.repository.StayListingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StayListingServiceImpl implements StayListingService {

    private final CloudService cloudService;
    private final AccountService accountService;
    private final StayListingRepository stayListingRepository;
    private final PicturesRepository picturesRepository;
    private final ModelMapper modelMapper;

    public StayListingServiceImpl(CloudService cloudService, AccountService accountService, StayListingRepository stayListingRepository, PicturesRepository picturesRepository, ModelMapper modelMapper) {
        this.cloudService = cloudService;
        this.accountService = accountService;
        this.stayListingRepository = stayListingRepository;
        this.picturesRepository = picturesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createListing(StayListingServiceModel listingServiceModel, String user) throws IOException {
        StayListing listing = modelMapper.map(listingServiceModel, StayListing.class);
        Account account = this.accountService.getAccountEntity(user);

        listing.setAddedFrom(account);
        listing.setAvailable(true);
        listing.getStayProperties().setListing(listing);
        listing.setPictures(mapPictures(listingServiceModel.getPictures(), listing));

        this.stayListingRepository.save(listing);

    }


    @Override
    public boolean titleAvailability(String title) {
        return this.stayListingRepository.findByListingTitle(title).isEmpty();
    }


    @Override
    public StayPropertiesBinding getPropertiesByListingId(String id) {

        return this.modelMapper.map(this.stayListingRepository.findById(id)
                        .get().getStayProperties(),
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

    @Override
    public StayListingView getByViewId(String id) {
        Optional<StayListing> sl = stayListingRepository.findById(id);
        if (sl.isEmpty())
            return null;


        StayListingView returnView = modelMapper.map(sl.get(), StayListingView.class);
        returnView.setAddedFrom(sl.get().getAddedFrom().getUsername());
        return returnView;
    }

    @Override
    public List<StayListingView> getAll() {
        return this.stayListingRepository.findAll()
                .stream()
                .map(e -> {
                    StayListingView map = modelMapper.map(e, StayListingView.class);
                    map.setFirstPicture(e.getPictures().get(0).getPictureUrl());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        this.stayListingRepository.deleteById(id);
    }

    @Override
    public StayListing findEntityById(String listingId) {
        return stayListingRepository.findById(listingId).orElse(null);
    }

    @Override
    public StayListing findEntityByTitle(String title) {
        return this.stayListingRepository.findByListingTitle(title).orElse(null);
    }

    @Override
    public void editListing(StayListingBinding binding, String id, String username) throws IOException {
        StayListing listing = stayListingRepository.getOne(id);

        listing.setStayProperties(updateListingProperties(listing.getStayProperties(), binding));
        listing.setListingTitle(binding.getListingTitle());
        listing.setStayType(binding.getStayType());
        listing.setCountry(binding.getCountry());
        listing.setCity(binding.getCity());
        listing.setAddress(binding.getAddress());
        listing.setAvailabilityLeft(binding.getAvailabilityLeft());
        listing.setPricePerNight(binding.getPricePerNight());

        if (binding.getPictures().length > 1) {
            List<Picture> p = picturesRepository.findAllByListing(listing);
            picturesRepository.deleteAll(p);
            listing.setPictures(mapPictures(binding.getPictures(), listing));
        }

        stayListingRepository.save(listing);

    }

    @Override
    public List<StayListingView> getRecentlyAdded() {
        return stayListingRepository.getLastThreeRecentlyAdded().stream()
                .map(e -> modelMapper.map(e, StayListingView.class))
                .collect(Collectors.toList());
    }

    @Override
    public StayListing getById(String id) {
        return this.stayListingRepository.getOne(id);
    }

    private StayProperties updateListingProperties(StayProperties listing, StayListingBinding stayBinding) {
        StayProperties stayPropertiesBinding = stayBinding.getStayProperties();
        listing.setAllInclusive(stayPropertiesBinding.isAllInclusive());
        listing.setHasAirConditioning(stayPropertiesBinding.isHasAirConditioning());
        listing.setHasBathroom(stayPropertiesBinding.isHasBathroom());
        listing.setHasBedroom(stayPropertiesBinding.isHasBedroom());
        listing.setHasCityView(stayPropertiesBinding.isHasCityView());
        listing.setHasElevator(stayPropertiesBinding.isHasElevator());
        listing.setHasKitchen(stayPropertiesBinding.isHasKitchen());
        listing.setHasRestaurant(stayPropertiesBinding.isHasRestaurant());
        listing.setHasTV(stayPropertiesBinding.isHasTV());
        listing.setHasWifi(stayPropertiesBinding.isHasWifi());
        listing.setHasParking(stayPropertiesBinding.isHasParking());
        listing.setHasFreeBreakfast(stayPropertiesBinding.isHasFreeBreakfast());
        return listing;
    }


    private List<Picture> mapPictures(MultipartFile[] pictures, StayListing listing) throws IOException {
        List<Picture> list = new ArrayList<>();
        if (pictures == null || pictures.length == 0 || Objects.equals(pictures[0].getOriginalFilename(), "")) {
            list.add(new Picture().setPictureUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png").setListing(listing));
            return list;
        }
        for (MultipartFile picture : pictures) {
            list.add(new Picture().setPictureUrl(cloudService.upload(picture)).setListing(listing));
        }
        return list;
    }

}
