package me.inc.bookingapp.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrainView {

    private String id;
    private String listingTitle;
    private String country;
    private String from;
    private String to;
    private LocalDateTime leave;
    private LocalDateTime arrive;
    private BigDecimal ticketPrice;

    public String getFrom() {
        return from;
    }

    public TrainView setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TrainView setTo(String to) {
        this.to = to;
        return this;
    }

    public LocalDateTime getLeave() {
        return leave;
    }

    public TrainView setLeave(LocalDateTime leave) {
        this.leave = leave;
        return this;
    }

    public LocalDateTime getArrive() {
        return arrive;
    }

    public TrainView setArrive(LocalDateTime arrive) {
        this.arrive = arrive;
        return this;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public TrainView setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public TrainView setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public String getId() {
        return id;
    }

    public TrainView setId(String id) {
        this.id = id;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public TrainView setCountry(String country) {
        this.country = country;
        return this;
    }
}
