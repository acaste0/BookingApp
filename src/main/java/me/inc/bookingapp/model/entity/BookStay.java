package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stay_books")
public class BookStay extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL)
    private StayListing listing;

    @Column
    private int guests;

    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    public Account getAccount() {
        return account;
    }

    public BookStay setAccount(Account account) {
        this.account = account;
        return this;
    }

    public StayListing getListing() {
        return listing;
    }

    public BookStay setListing(StayListing listing) {
        this.listing = listing;
        return this;
    }

    public int getGuests() {
        return guests;
    }

    public BookStay setGuests(int guests) {
        this.guests = guests;
        return this;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public BookStay setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public BookStay setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }
}
