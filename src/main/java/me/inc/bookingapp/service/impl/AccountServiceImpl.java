package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.binding.AccountEditBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.BookStay;
import me.inc.bookingapp.model.entity.enums.Role;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.model.view.AccountViewModel;
import me.inc.bookingapp.model.view.StayListingView;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.repository.AccountRoleRepository;
import me.inc.bookingapp.repository.BookStayRepository;
import me.inc.bookingapp.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AppAccountService appAccountService;
    private final AccountRoleRepository accountRoleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AppAccountService appAccountService, AccountRoleRepository accountRoleRepository,
                              AccountRepository accountRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
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

        loadAndSave(acc);
    }


    private List<AccountRole> setAccountRoles() {
        AccountRole admin = accountRoleRepository.getByRole(Role.ADMIN);
        AccountRole user = accountRoleRepository.getByRole(Role.USER);
        if (this.accountRepository.count() == 0) {
            return List.of(admin, user);
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

    @Override
    public void editAccount(String name, AccountEditBinding map) {
        Account account = accountRepository.getAccountByUsername(name);
        modelMapper.map(map, account);
        this.accountRepository.save(account);
        loadAndSave(account);
    }

    @Override
    public AccountServiceModel getAccount(String id) {
        return this.modelMapper.map(accountRepository.
                findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found")), AccountServiceModel.class);
    }

    @Override
    public Account getAccountEntity(String username) {
        return this.accountRepository.getAccountByUsername(username);
    }

    @Override
    public List<StayListingView> getAllAccountListings(String username) {
        Account account = accountRepository.getAccountByUsername(username);
        return account.getStayListings().stream().
                map(l -> modelMapper.map(l, StayListingView.class)).
                collect(Collectors.toList());

    }

    @Override
    public AccountServiceModel getAccountByUsername(String username) {
        return this.modelMapper.map(accountRepository.
                findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found")), AccountServiceModel.class);
    }


    private void loadAndSave(Account acc) {
        UserDetails userDetails = appAccountService.loadUserByUsername(acc.getEmail());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, acc.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }




}
