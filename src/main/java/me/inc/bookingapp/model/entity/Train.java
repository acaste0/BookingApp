package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseListing;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trains")
public class Train extends BaseListing {

    @Column(name = "starting_from", nullable = false)
    private String from;
    @Column(name = "going_to", nullable = false)
    private String to;
    @Column(name = "leave_time", nullable = false)
    private LocalDateTime leave;
    @Column(name = "arive_time", nullable = false)
    private LocalDateTime arrive;
    @Column(name = "repeating", nullable = false)
    private boolean repeat;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "ticket_price", nullable = false)
    private BigDecimal ticketPrice;

    @OneToMany(mappedBy = "train")
    private List<TrainBook> bookings;

    public String getFrom() {
        return from;
    }

    public Train setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Train setTo(String to) {
        this.to = to;
        return this;
    }

    public LocalDateTime getLeave() {
        return leave;
    }

    public Train setLeave(LocalDateTime leave) {
        this.leave = leave;
        return this;
    }

    public LocalDateTime getArrive() {
        return arrive;
    }

    public Train setArrive(LocalDateTime arrive) {
        this.arrive = arrive;
        return this;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Train setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public Train setRepeat(boolean repeat) {
        this.repeat = repeat;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Train setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<TrainBook> getBookings() {
        return bookings;
    }

    public Train setBookings(List<TrainBook> bookings) {
        this.bookings = bookings;
        return this;
    }
}
