package me.inc.bookingapp.model.entity;

import me.inc.bookingapp.model.entity.base.BaseEntity;
import me.inc.bookingapp.model.entity.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class AccountRole extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountRole setRole(Role role) {
        this.role = role;
        return this;
    }

    public Role getRole() {
        return role;
    }
}
