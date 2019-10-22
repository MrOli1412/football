package com.pl.football.backend;

import com.pl.football.backend.model.Role;
import com.pl.football.backend.service.role.RoleCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
@Autowired
    RoleCommandService roleCommandService;
    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleCommandService.saveRoles();
    }
}
