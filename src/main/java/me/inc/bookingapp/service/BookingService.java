package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.BookStay;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface BookingService {
    void bookStay(BookStay bookStay, String listingId, String accountUsername);

}
