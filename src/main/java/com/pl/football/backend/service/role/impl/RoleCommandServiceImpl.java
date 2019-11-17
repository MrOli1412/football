package com.pl.football.backend.service.role.impl;

import com.pl.football.backend.model.Role;
import com.pl.football.backend.model.RoleName;
import com.pl.football.backend.repository.RoleRepository;
import com.pl.football.backend.service.role.RoleCommandService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    final private RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public void saveRoles() {
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role(RoleName.ROLE_ADMIN));
//        roles.add(new Role(RoleName.ROLE_USER));
//        roles.add(new Role(RoleName.ROLE_PM));
//
//        roleRepository.saveAll(roles);
    }
}
