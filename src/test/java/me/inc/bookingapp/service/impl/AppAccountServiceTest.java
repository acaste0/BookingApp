package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.entity.enums.Role;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.service.impl.AppAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class AppAccountServiceTest {

    private AppAccountService service;

    @Mock
    AccountRepository mockAccountRepository;

    @BeforeEach
    public void setUp() {
        service = new AppAccountService(mockAccountRepository);
    }



    public Account getAccount() {
        Account account = new Account().setUsername("ivan").setEmail("ivan@abv.bg").setPassword("12345").
                setFirstName("ivan").setLastName("ivanov").setAccountType(AccountType.PERSONAL);

        AccountRole user = new AccountRole().setRole(Role.USER);
        AccountRole admin = new AccountRole().setRole(Role.ADMIN);

        account.setAccountRoles(List.of(user, admin));
        return account;
    }


    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    service.loadUserByUsername("not_exist@abv.bg");
                }
        );
    }

    @Test
    void testExistingUserAndAuthorities() {
        Account account = getAccount();

        Mockito.when(mockAccountRepository.findByEmail("ivan@abv.bg")).
                thenReturn(Optional.of(account));

        UserDetails userDetails = service.loadUserByUsername("ivan@abv.bg");

        Assertions.assertEquals(account.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(3, userDetails.getAuthorities().size());

        List<String> authorities = userDetails.
                getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
        Assertions.assertTrue(authorities.contains("TYPE_PERSONAL"));
    }

}
