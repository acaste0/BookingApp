package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.repository.StayListingRepository;
import me.inc.bookingapp.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AccountRepository accountRepository;
    private final StayListingRepository stayListingRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(AccountRepository accountRepository, StayListingRepository stayListingRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.stayListingRepository = stayListingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean changeRole(String username, AccountRole accountRole) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isPresent()) {
            List<AccountRole> roles = account.get().getAccountRoles();
            if (roles.contains(accountRole)) {
                throw new UnsupportedOperationException();
            }
            roles.add(accountRole);
            this.accountRepository.save(account.get());
            return true;
        }

        throw new UsernameNotFoundException("Cannot find user with" + username);
    }

    @Override
    public boolean changeType(String username, AccountType accountType) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isPresent()) {
            account.get().setAccountType(accountType);
            this.accountRepository.save(account.get());
            return true;
        }

        throw new UsernameNotFoundException("Cannot find user with" + username);
    }

    @Override
    public void deleteStayListing(String id) {
        this.stayListingRepository.deleteById(id);
    }

    @Override
    public List<Account> getAllUsers() {
        return this.accountRepository.findAll();
    }
}
