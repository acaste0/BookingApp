package me.inc.bookingapp.model.service;

import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.entity.enums.Role;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceModel {

    private AccountType accountType;
    private List<Role> accountRoles = new ArrayList<>();
    private String username;
    private String email;
    private String phone;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;

    public AccountServiceModel() {
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public AccountServiceModel setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccountServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public AccountServiceModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AccountServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccountServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public AccountServiceModel setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public List<Role> getAccountRoles() {
        return accountRoles;
    }

    public AccountServiceModel setAccountRoles(List<Role> accountRoles) {
        this.accountRoles = accountRoles;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccountServiceModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
