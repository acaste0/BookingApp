package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.enums.AccountType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AccountRegistrationBinding {

    private AccountType accountType;
    @NotBlank
    @Email(message = "Enter a valid amail address")
    private String email;
    @NotBlank
    @Length(min = 3, max = 20, message = "Username must be at least 3 symbols")
    private String username;
    @NotBlank
    @Length(min = 4, message = "Password must be at least 4 symbols")
    private String password;
    @Length(min = 4, message = "Password must be at least 4 symbols")
    private String confirmPassword;
    @NotBlank
    @Length(min = 3, max = 20, message = "Enter a valid first name")
    private String firstName;
    @NotBlank
    @Length(min = 3, max = 20, message = "Enter a valid last name")
    private String lastName;

    public AccountRegistrationBinding() {
    }

    public AccountRegistrationBinding setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountType getAccountType() {
        return accountType;
    }


    public String getEmail() {
        return email;
    }

    public AccountRegistrationBinding setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccountRegistrationBinding setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountRegistrationBinding setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public AccountRegistrationBinding setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountRegistrationBinding setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountRegistrationBinding setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
