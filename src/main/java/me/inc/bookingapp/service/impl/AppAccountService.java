package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppAccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AppAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username
        ).orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " was not found!"));

        return mapToUserDetails(account);
    }

    private UserDetails mapToUserDetails(Account account) {
        List<GrantedAuthority> authorities =
                account.
                        getAccountRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("TYPE_" + account.getAccountType().name()));

        return new User(
                account.getUsername(),
                account.getPassword(),
                authorities);
    }

}