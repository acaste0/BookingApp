package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.TrainBook;
import me.inc.bookingapp.model.view.AccountViewModel;

import java.awt.print.Book;
import java.util.List;

public interface BookingService {
    void bookStay(BookStay stayBook, String listingId, String accountUsername);
    void bookTrain(TrainBook trainBook, String listingId, String accountUsername);

    List<TrainBook> getAllTrainBookingsByUsername(String username);
    List<BookStay> getAllStayBookingsByUsername(String username);
    List<BookStay> getAllAccountsBookedStayListing(String listingId);
}
