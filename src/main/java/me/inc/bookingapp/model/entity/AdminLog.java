package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "admin_logs")
public class AdminLog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Account adminAccount;
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private Account userAccount;
    @Column(name = "action", nullable = false)
    private String action;

    public Account getAdminAccount() {
        return adminAccount;
    }

    public AdminLog setAdminAccount(Account adminAccount) {
        this.adminAccount = adminAccount;
        return this;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public AdminLog setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
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
