package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.TrainBook;

import java.util.List;

public interface BookingService {
    void bookStay(BookStay stayBook, String listingId, String accountUsername);
    void bookTrain(TrainBook trainBook, String listingId, String accountUsername);

    List<TrainBook> getAllTrainBookingsByUsername(String username);
    List<BookStay> getAllStayBookingsByUsername(String username);

}
