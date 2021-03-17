package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.enums.AccountType;
import org.hibernate.validator.constraints.Length;

public class AccountEditBinding {

    private AccountType accountType;
    @Length(min = 3, max = 20, message = "Required length is minimum 3 characters")
    private String firstName;
    @Length(min = 3, max =20, message = "Required length is minimum 3 characters")
    private String lastName;
    @Length(min =5, max = 30, message = "Address must contain more than 5 characters")
    private String address;
    @Length(min = 5, message = "Phone number must contain more than 5 characters")
    private String phone;
    @Length(min = 3, message = "City name must be more than 3 characters")
    private String city;
    private String zipCode;

    public String getFirstName() {
        return firstName;
    }

    public AccountEditBinding setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountEditBinding setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AccountEditBinding setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccountEditBinding setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccountEditBinding setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public AccountEditBinding setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public AccountEditBinding setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }
}
