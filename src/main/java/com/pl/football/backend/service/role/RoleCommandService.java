package com.pl.football.backend.service.role;


import com.pl.football.backend.model.Role;
import com.pl.football.backend.model.RoleName;

import java.util.Optional;


public interface RoleCommandService {

     Optional<Role> findByName(RoleName roleAdmin);
     void saveRoles();
}
