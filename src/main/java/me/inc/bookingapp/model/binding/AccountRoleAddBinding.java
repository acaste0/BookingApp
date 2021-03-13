package me.inc.bookingapp.model.binding;

import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.Role;

public class AccountRoleAddBinding {

    private String username;
    private Role role;

    public String getUsername() {
        return username;
    }

    public AccountRoleAddBinding setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public AccountRoleAddBinding setRole(Role role) {
        this.role = role;
        return this;
    }
}
