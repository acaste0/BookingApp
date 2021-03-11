package me.inc.bookingapp.service;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.view.AccountViewModel;

import java.util.List;

public interface AdminService {

    boolean changeRole(String username, AccountRole accountRole);
    boolean changeType(String username, AccountType accountType);
    void deleteStayListing(String id);
    List<Account> getAllUsers();
}
