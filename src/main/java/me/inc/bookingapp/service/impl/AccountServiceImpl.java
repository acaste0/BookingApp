package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.Role;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.repository.AccountRoleRepository;
import me.inc.bookingapp.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AppAccountService appAccountService;
    private final AccountRoleRepository accountRoleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AppAccountService appAccountService, AccountRoleRepository accountRoleRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.appAccountService = appAccountService;
        this.accountRoleRepository = accountRoleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean usernameAvailability(String username) {
        return this.accountRepository.findByUsername(username).isEmpty();
    }

    @Override
    public void registerAccount(AccountServiceModel account) {
        Account acc = this.modelMapper.map(account, Account.class);
        acc.setAccountRoles(setAccountRoles());
        acc.setPassword(passwordEncoder.encode(acc.getPassword()));

        acc = this.accountRepository.save(acc);

        UserDetails userDetails = appAccountService.loadUserByUsername(acc.getUsername());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, acc.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


    }

    private List<AccountRole> setAccountRoles() {

        AccountRole admin = accountRoleRepository.getByRole(Role.ADMIN);
        AccountRole user = accountRoleRepository.getByRole(Role.USER);
        if (this.accountRepository.count() == 0) {
            return List.of(admin,user);
        }
        return List.of(user);
    }

    @Override
    public boolean emailAvailability(String email) {
        return this.accountRepository.findByEmail(email).isEmpty();
    }

    @Override
    public AccountViewModel getView(String id) {
        Optional<Account> acc = accountRepository.findByUsername(id);
        if (acc.isPresent()) {
            AccountViewModel view = this.modelMapper.map(acc.get(), AccountViewModel.class);
            view.setAccountType(acc.get().getAccountType().name());
            return view;
        }
        return null;
    }
}
