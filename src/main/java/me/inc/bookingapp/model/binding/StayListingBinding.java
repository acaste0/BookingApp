package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.enums.ListingType;
import me.inc.bookingapp.model.entity.enums.StayType;
import me.inc.bookingapp.model.entity.properties.StayProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class StayListingBinding {

    @Length(min = 3, max = 30, message = "Listing title must be at least 3 characters")
    private String listingTitle;
    private ListingType listingType = ListingType.STAY;
    private StayType stayType;
    @Min(value = 1, message = "Availability must be more than 0")
    private int availabilityLeft;
    private MultipartFile[] pictures;
    private StayProperties stayProperties;
    @Min(value = 6,message = "Enter a valid phone number")
    private int contactNumber;
    private String country;
    @Length(min = 3, max = 30, message = "City name must be at least 3 characters")
    private String city;
    @Length(min = 3, max = 30, message = "Address must be at least 3 characters")
    private String address;
    @Length(min = 10, message = "Description must contain at least 10 characters")
    private String description;
    @Min(value = 1, message = "Price cannot be negative number")
    private BigDecimal pricePerNight;

    public String getListingTitle() {
        return listingTitle;
    }

    public StayListingBinding setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public StayListingBinding setListingType(ListingType listingType) {
        this.listingType = listingType;
        return this;
    }

    public StayType getStayType() {
        return stayType;
    }

    public StayListingBinding setStayType(StayType stayType) {
        this.stayType = stayType;
        return this;
    }

    public int getAvailabilityLeft() {
        return availabilityLeft;
    }

    public StayListingBinding setAvailabilityLeft(int availabilityLeft) {
        this.availabilityLeft = availabilityLeft;
        return this;
    }



    public StayProperties getStayProperties() {
        return stayProperties;
    }

    public StayListingBinding setStayProperties(StayProperties stayProperties) {
        this.stayProperties = stayProperties;
        return this;
    }


    public String getCountry() {
        return country;
    }

    public StayListingBinding setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StayListingBinding setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StayListingBinding setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StayListingBinding setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public StayListingBinding setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }

    public MultipartFile[] getPictures() {
        return pictures;
    }

    public StayListingBinding setPictures(MultipartFile[] pictures) {
        this.pictures = pictures;
        return this;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public StayListingBinding setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }
}
