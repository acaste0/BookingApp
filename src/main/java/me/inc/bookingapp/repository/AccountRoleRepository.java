package me.inc.bookingapp.repository;

import me.inc.bookingapp.model.entity.Account;
import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.model.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, String> {
    AccountRole getByRole(Role role);

}
