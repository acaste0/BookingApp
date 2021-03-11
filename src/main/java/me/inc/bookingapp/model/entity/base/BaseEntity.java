package me.inc.bookingapp.model.entity.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(name = "created_on")
    private Instant createdOn = Instant.now();
    public BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public BaseEntity setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
