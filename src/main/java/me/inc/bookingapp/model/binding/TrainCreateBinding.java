package me.inc.bookingapp.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrainCreateBinding {

    @NotBlank(message = "Please enter a valid listing title ")
    @Length(min = 3, max = 30, message = "Listing title must be 3 or more characters")
    private String listingTitle;

    @NotBlank(message = "Please enter a valid location ")
    @Length(min = 3, max = 30, message = "Starting point must be 3 or more characters")
    private String from;

    @NotBlank(message = "Please enter a valid country ")
    private String country;

    @NotBlank(message = "Please enter a valid location ")
    @Length(min = 3, max = 30, message = "Destination point must be 3 or more characters")
    private String to;

    private boolean repeat;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime leave;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arrive;

    @Min(value = 1,message = "Price must be above $1")
    private BigDecimal ticketPrice;

    public String getFrom() {
        return from;
    }

    public TrainCreateBinding setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TrainCreateBinding setTo(String to) {
        this.to = to;
        return this;
    }

    public LocalDateTime getLeave() {
        return leave;
    }

    public TrainCreateBinding setLeave(LocalDateTime leave) {
        this.leave = leave;
        return this;
    }

    public LocalDateTime getArrive() {
        return arrive;
    }

    public TrainCreateBinding setArrive(LocalDateTime arrive) {
        this.arrive = arrive;
        return this;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public TrainCreateBinding setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public TrainCreateBinding setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public TrainCreateBinding setRepeat(boolean repeat) {
        this.repeat = repeat;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public TrainCreateBinding setCountry(String country) {
        this.country = country;
        return this;
    }
}
