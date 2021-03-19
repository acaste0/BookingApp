package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.enums.Role;

public class AccountRoleEditBinding {

    private String username;
    private Role role;

    public String getUsername() {
        return username;
    }

    public AccountRoleEditBinding setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public AccountRoleEditBinding setRole(Role role) {
        this.role = role;
        return this;
    }
}
