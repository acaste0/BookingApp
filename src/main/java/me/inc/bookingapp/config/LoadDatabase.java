package me.inc.bookingapp.config;

import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.Role;
import me.inc.bookingapp.repository.AccountRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRoleRepository accountRoleRepository) {

        return args -> {
            if (accountRoleRepository.count() == 0) {
                log.info("Preloading " + accountRoleRepository.save(new AccountRole().setRole(Role.ADMIN)));
                log.info("Preloading " + accountRoleRepository.save(new AccountRole().setRole(Role.USER)));
            }
        };
    }
}