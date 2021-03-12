package me.inc.bookingapp.service.impl;

import me.inc.bookingapp.model.entity.AccountRole;
import me.inc.bookingapp.repository.AccountRoleRepository;
import me.inc.bookingapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final AccountRoleRepository roleRepository;

    public RoleServiceImpl(AccountRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<AccountRole> getAllRoles() {
        return roleRepository.findAll();
    }
}
