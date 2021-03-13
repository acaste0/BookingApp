package me.inc.bookingapp.service;

import me.inc.bookingapp.model.binding.AccountRoleAddBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.view.AccountViewModel;

import java.util.List;

public interface AdminService {

    void addRole(AccountRoleAddBinding accountRoleAddBinding);
    boolean changeType(String username, AccountType accountType);
    void deleteStayListing(String id);
    List<Account> getAllUsers();
}
