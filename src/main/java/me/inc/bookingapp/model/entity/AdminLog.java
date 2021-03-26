package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "admin_logs")
public class AdminLog extends BaseEntity {
    @Column(name = "admin_account_username", nullable = false)
    private String adminAccountUsername;
    @Column(name = "user_account_username", nullable = false)
    private String userAccountUsername;
    @Column(name = "action", nullable = false)
    private String action;

    public String getAdminAccountUsername() {
        return adminAccountUsername;
    }

    public AdminLog setAdminAccountUsername(String adminAccountUsername) {
        this.adminAccountUsername = adminAccountUsername;
        return this;
    }

    public String getUserAccountUsername() {
        return userAccountUsername;
    }

    public AdminLog setUserAccountUsername(String userAccountUsername) {
        this.userAccountUsername = userAccountUsername;
        return this;
    }

    public String getAction() {
        return action;
    }

    public AdminLog setAction(String action) {
        this.action = action;
        return this;
    }
}
