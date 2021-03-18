package me.inc.bookingapp.service.impl;

import com.cloudinary.Cloudinary;
import me.inc.bookingapp.model.binding.StayPropertiesBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.Picture;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.model.service.StayListingServiceModel;
import me.inc.bookingapp.model.view.StayListingView;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StayListingServiceImpl implements StayListingService {

    private final CloudService cloudService;
    private final AccountService accountService;
    private final StayListingRepository stayListingRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;

    public StayListingServiceImpl(CloudService cloudService, AccountService accountService, StayListingRepository stayListingRepository, ModelMapper modelMapper, Cloudinary cloudinary) {
        this.cloudService = cloudService;
        this.accountService = accountService;
        this.stayListingRepository = stayListingRepository;
        this.modelMapper = modelMapper;

        this.cloudinary = cloudinary;
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

    private List<Picture> mapPictures(MultipartFile[] pictures, StayListing listing) throws IOException {
        List<Picture> list = new ArrayList<>();
        for (MultipartFile picture : pictures) {
            list.add(new Picture().setPictureUrl(cloudService.upload(picture)).setListing(listing));
        }
        return list;
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
    public StayListingView getById(String id) {
        Optional<StayListing> sl = stayListingRepository.findById(id);
        if (sl.isEmpty())
            return null;

        return modelMapper.map(sl.get(), StayListingView.class);
    }
}
