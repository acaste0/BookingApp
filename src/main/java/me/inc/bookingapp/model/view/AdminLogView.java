package me.inc.bookingapp.model.view;

import java.time.Instant;

public class AdminLogView {

    private String id;
    private String adminAccountUsername;
    private String userAccountUsername;
    private String action;
    private Instant createdOn;

    public Instant getCreatedOn() {
        return createdOn;
    }

    public AdminLogView setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getId() {
        return id;
    }

    public AdminLogView setId(String id) {
        this.id = id;
        return this;
    }

    public String getAdminAccountUsername() {
        return adminAccountUsername;
    }

    public AdminLogView setAdminAccountUsername(String adminAccountUsername) {
        this.adminAccountUsername = adminAccountUsername;
        return this;
    }

    public String getUserAccountUsername() {
        return userAccountUsername;
    }

    public AdminLogView setUserAccountUsername(String userAccountUsername) {
        this.userAccountUsername = userAccountUsername;
        return this;
    }

    public String getAction() {
        return action;
    }

    public AdminLogView setAction(String action) {
        this.action = action;
        return this;
    }
}
