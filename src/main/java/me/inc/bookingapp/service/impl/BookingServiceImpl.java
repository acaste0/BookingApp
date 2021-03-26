package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.StayListing;
import me.inc.bookingapp.repository.BookStayRepository;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.BookingService;
import me.inc.bookingapp.service.StayListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final AccountService accountService;
    private final StayListingService stayListingService;
    private final BookStayRepository bookStayRepository;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(AccountService accountService, StayListingService stayListingService, BookStayRepository bookStayRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.stayListingService = stayListingService;
        this.bookStayRepository = bookStayRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void bookStay(BookStay bookStay, String listingId, String accountUsername) {
        bookStay.setListing( stayListingService.getById(listingId));
        bookStay.setAccount(accountService.getAccountEntity(accountUsername));

        bookStayRepository.save(bookStay);
    }
}
