package me.inc.bookingapp.model.binding;

public class AccountLoginBinding {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public AccountLoginBinding setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountLoginBinding setPassword(String password) {
        this.password = password;
        return this;
    }
}

