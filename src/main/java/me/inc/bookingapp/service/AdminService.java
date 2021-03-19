package me.inc.bookingapp.service;

import me.inc.bookingapp.model.binding.AccountRoleEditBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.enums.AccountType;

import java.util.List;

public interface AdminService {

    void addRole(AccountRoleEditBinding accountRoleEditBinding);
    boolean changeType(String username, AccountType accountType);
    void deleteStayListing(String id);
    List<Account> getAllUsers();

    boolean removeRole(AccountRoleEditBinding accountRoleEditBinding);
}
