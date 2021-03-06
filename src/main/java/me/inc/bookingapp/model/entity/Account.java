package me.inc.bookingapp.model.entity;


import me.inc.bookingapp.model.entity.base.BaseEntity;
import me.inc.bookingapp.model.entity.enums.AccountType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AccountRole> accountRoles = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String phone;
    @Column(name = "zip_code")
    private String zipCode;

    @OneToMany(mappedBy = "addedFrom", cascade = CascadeType.ALL)
    private List<StayListing> stayListings;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<BookStay> stayBookings;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TrainBook> trainBookings;


    public Account() {
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Account setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Account setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Account setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Account setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Account setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public Account setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Account setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public List<StayListing> getStayListings() {
        return stayListings;
    }

    public Account setStayListings(List<StayListing> stayListings) {
        this.stayListings = stayListings;
        return this;
    }

    public List<BookStay> getStayBookings() {
        return stayBookings;
    }

    public Account setStayBookings(List<BookStay> stayBookings) {
        this.stayBookings = stayBookings;
        return this;
    }

    public List<TrainBook> getTrainBookings() {
        return trainBookings;
    }

    public Account setTrainBookings(List<TrainBook> trainBookings) {
        this.trainBookings = trainBookings;
        return this;
    }
}
