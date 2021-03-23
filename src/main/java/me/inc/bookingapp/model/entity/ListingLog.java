package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "listing_logs")
public class ListingLog extends BaseEntity {

    @ManyToOne
    private Account account;
    @ManyToOne
    private StayListing stayListing;
    @Column(name = "action", nullable = false)
    private String action;

    public Account getAccount() {
        return account;
    }

    public ListingLog setAccount(Account account) {
        this.account = account;
        return this;
    }

    public StayListing getStayListing() {
        return stayListing;
    }

    public ListingLog setStayListing(StayListing stayListing) {
        this.stayListing = stayListing;
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
