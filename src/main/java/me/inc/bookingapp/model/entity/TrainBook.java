package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "train_bookings")
public class TrainBook  extends BaseEntity {

    @ManyToOne
    private Train train;

    @ManyToOne
    private Account account;

    public Train getTrain() {
        return train;
    }

    public TrainBook setTrain(Train train) {
        this.train = train;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public TrainBook setAccount(Account account) {
        this.account = account;
        return this;
    }
}
