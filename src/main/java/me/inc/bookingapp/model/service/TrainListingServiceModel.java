package me.inc.bookingapp.model.service;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.base.BaseListing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TrainListingServiceModel extends BaseListing {
    private String from;
    private String to;
    private LocalDateTime leave;
    private LocalDateTime arrive;
    private boolean repeat;
    private String country;
    private BigDecimal ticketPrice;
    private List<Account> passengers;

    public String getFrom() {
        return from;
    }

    public TrainListingServiceModel setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TrainListingServiceModel setTo(String to) {
        this.to = to;
        return this;
    }

    public LocalDateTime getLeave() {
        return leave;
    }

    public TrainListingServiceModel setLeave(LocalDateTime leave) {
        this.leave = leave;
        return this;
    }

    public LocalDateTime getArrive() {
        return arrive;
    }

    public TrainListingServiceModel setArrive(LocalDateTime arrive) {
        this.arrive = arrive;
        return this;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public TrainListingServiceModel setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public List<Account> getPassengers() {
        return passengers;
    }

    public TrainListingServiceModel setPassengers(List<Account> passengers) {
        this.passengers = passengers;
        return this;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public TrainListingServiceModel setRepeat(boolean repeat) {
        this.repeat = repeat;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public TrainListingServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }
}
