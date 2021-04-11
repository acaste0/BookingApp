package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.AccountType;
import me.inc.bookingapp.model.entity.enums.Role;
import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.repository.AccountRepository;
import me.inc.bookingapp.repository.AccountRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AppAccountService appAccountService;

    @Mock
    private AccountRoleRepository accountRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    private Account ivan, misho;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        accountService = new AccountServiceImpl(appAccountService, accountRoleRepository, accountRepository,
                passwordEncoder, modelMapper);

        ivan = new Account();
        misho = new Account();

        ivan.setFirstName("Ivan").setLastName("Ivanov").setPhone("0888111111")
                .setAddress("test address").setCity("Sofia")
                .setPassword(passwordEncoder.encode("1234"))
                .setUsername("ivan");
        misho.setFirstName("Misho").setLastName("Penev").setPhone("0888111111")
                .setAddress("test address").setCity("Sofia")
                .setPassword(passwordEncoder.encode("1234"))
                .setUsername("misho");

        accountRoleRepository.saveAll(List.of(new AccountRole().setRole(Role.USER),
                new AccountRole().setRole(Role.ADMIN)));

    }


    @Test
    public void testAccountCreationIsCompleted() {
        when(accountRepository.findAll()).thenReturn(List.of(ivan, misho));
        assertEquals(accountRepository.findAll().size(), 2);
    }

    @Test
    public void testGetAccountByUsername() {
        when(this.accountRepository.getAccountByUsername(contains("ivan"))).thenReturn(ivan);
        Account ivan = this.accountService.getAccountEntity("ivan");
        assertEquals(ivan, this.ivan);

    }

    @Test
    public void testUsernameAvailability() {
        assertTrue(this.accountService.usernameAvailability("not_found"));
    }

    @Test
    public void testEmailAvailability() {
        assertTrue(this.accountService.emailAvailability("not_found@abv.bg"));
    }

}
