package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "listing_logs")
public class ListingLog extends BaseEntity {

    @Column(name = "account_username")
    private String account;
    @Column(name = "stay_listing_title")
    private String stayListingTitle;
    @Column(name = "action", nullable = false)
    private String action;

    public String getAccount() {
        return account;
    }

    public ListingLog setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getStayListingTitle() {
        return stayListingTitle;
    }

    public ListingLog setStayListingTitle(String stayListingTitle) {
        this.stayListingTitle = stayListingTitle;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ListingLog setAction(String action) {
        this.action = action;
        return this;
    }
}
