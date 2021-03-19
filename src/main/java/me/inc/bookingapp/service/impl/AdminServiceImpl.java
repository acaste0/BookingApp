package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.binding.AccountRoleEditBinding;
import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.repository.AccountRoleRepository;
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
    private final AccountRoleRepository accountRoleRepository;
    private final StayListingRepository stayListingRepository;

    public AdminServiceImpl(AccountRepository accountRepository, AccountRoleRepository accountRoleRepository, StayListingRepository stayListingRepository) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.stayListingRepository = stayListingRepository;
    }

    @Override
    public void addRole(AccountRoleEditBinding accountBinding) {
        Optional<Account> account = this.accountRepository.findByUsername(accountBinding.getUsername());
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("Cannot find user with" + accountBinding.getUsername());
        }
        List<AccountRole> roles = account.get().getAccountRoles();
        for (AccountRole role : roles) {
            if (role.getRole() == accountBinding.getRole())
                throw new UnsupportedOperationException();
        }

        roles.add(accountRoleRepository.getByRole(accountBinding.getRole()));
        this.accountRepository.save(account.get());

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

    @Override
    public boolean removeRole(AccountRoleEditBinding accountBinding) {
        AccountRole role = accountRoleRepository.getByRole(accountBinding.getRole());
        Optional<Account> account = this.accountRepository.findByUsername(accountBinding.getUsername());
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("Cannot find user with" + accountBinding.getUsername());
        }
        List<AccountRole> roles = account.get().getAccountRoles();
        if (roles.contains(role)) {
            roles.remove(role);
            account.get().setAccountRoles(roles);
            this.accountRepository.save(account.get());
            return true;
        }

        return false;
    }
}
