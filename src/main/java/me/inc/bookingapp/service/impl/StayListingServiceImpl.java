package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.service.StayListingService;
import me.inc.bookingapp.repository.StayListingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StayListingServiceImpl implements StayListingService {

    private final StayListingRepository stayListingRepository;

    public StayListingServiceImpl(StayListingRepository stayListingRepository) {
        this.stayListingRepository = stayListingRepository;
    }

    @PostConstruct
    @Override
    public void testInit() {

    }
}
