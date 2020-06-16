package com.pl.football.backend.service.role.impl;

import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.Role;
import com.pl.football.backend.model.RoleName;
import com.pl.football.backend.repository.RoleRepository;
import com.pl.football.backend.service.role.RoleCommandService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {

            return roleRepository.findByName(roleName);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in geting role by name" + ex.getMessage());
        }
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
