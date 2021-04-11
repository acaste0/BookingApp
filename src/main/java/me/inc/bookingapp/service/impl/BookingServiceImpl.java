package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.TrainBook;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.repository.BookStayRepository;
import me.inc.bookingapp.repository.TrainBookRepository;
import me.inc.bookingapp.service.AccountService;
import me.inc.bookingapp.service.BookingService;
import me.inc.bookingapp.service.StayListingService;
import me.inc.bookingapp.service.TrainListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final AccountService accountService;
    private final StayListingService stayListingService;
    private final TrainListingService trainListingService;
    private final BookStayRepository bookStayRepository;
    private final TrainBookRepository trainBookRepository;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(AccountService accountService, StayListingService stayListingService, TrainListingService trainListingService, BookStayRepository bookStayRepository, TrainBookRepository trainBookRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.stayListingService = stayListingService;
        this.trainListingService = trainListingService;
        this.bookStayRepository = bookStayRepository;
        this.trainBookRepository = trainBookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void bookStay(BookStay stayBook, String listingId, String accountUsername) {
        stayBook.setListing( stayListingService.getById(listingId));
        stayBook.setAccount(accountService.getAccountEntity(accountUsername));

        bookStayRepository.save(stayBook);
    }

    @Override
    public void bookTrain(TrainBook trainBook, String listingId, String accountUsername) {
        trainBook.setTrain(trainListingService.getById(listingId));
        trainBook.setAccount(accountService.getAccountEntity(accountUsername));

        trainBookRepository.save(trainBook);
    }

    @Override
    public List<TrainBook> getAllTrainBookingsByUsername(String username) {
        return this.accountService.getAccountEntity(username).getTrainBookings();
    }

    @Override
    public List<BookStay> getAllStayBookingsByUsername(String username) {
        return this.accountService.getAccountEntity(username).getStayBookings();
    }

    @Override
    public List<BookStay> getAllAccountsBookedStayListing(String listingId) {
        return bookStayRepository.findAllByListingId(listingId);

    }

}
