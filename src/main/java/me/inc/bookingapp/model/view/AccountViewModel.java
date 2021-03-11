package me.inc.bookingapp.model.view;

import me.inc.bookingapp.model.entity.enums.AccountType;

public class AccountViewModel {

    private String accountType;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String address;

    public String getFirstName() {
        return firstName;
    }

    public AccountViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccountViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AccountViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccountViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public AccountViewModel setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }
}
