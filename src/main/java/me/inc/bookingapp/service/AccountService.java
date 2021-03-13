package me.inc.bookingapp.service;

import me.inc.bookingapp.model.binding.AccountEditBinding;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.model.view.AccountViewModel;

public interface AccountService {

    boolean usernameAvailability(String username);

    void registerAccount(AccountServiceModel account);

    boolean emailAvailability(String email);

    AccountViewModel getView(String username);

    void editAccount(String name, AccountEditBinding map);

    AccountServiceModel getAccount(String username);
}
