package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "general_logs")
public class GeneralLog extends BaseEntity {

    @Column(name = "account_username")
    private String account;
    @Column(name = "action", nullable = false)
    private String action;


    public String getAccount() {
        return account;
    }

    public GeneralLog setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getAction() {
        return action;
    }

    public GeneralLog setAction(String action) {
        this.action = action;
        return this;
    }
}
