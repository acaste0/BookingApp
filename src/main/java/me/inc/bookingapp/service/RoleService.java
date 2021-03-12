package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.Role;

import java.util.List;

public interface RoleService {

    List<AccountRole> getAllRoles();
}
