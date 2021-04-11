package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.BookStay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookStayRepository extends JpaRepository<BookStay, String> {

    List<BookStay> findAllByListingId(String listingId);

}
