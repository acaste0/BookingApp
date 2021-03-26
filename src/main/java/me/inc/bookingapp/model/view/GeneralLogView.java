package me.inc.bookingapp.model.view;

public class GeneralLogView {

    private String account;
    private String action;
    private String createdOn;

    public String getAccount() {
        return account;
    }

    public GeneralLogView setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getAction() {
        return action;
    }

    public GeneralLogView setAction(String action) {
        this.action = action;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public GeneralLogView setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
