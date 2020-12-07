package com.pl.football.backend;

import com.pl.football.backend.model.Role;
import com.pl.football.backend.service.role.RoleCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@PropertySources({@PropertySource(value = "file:${catalina.base}/conf/resources/application.properties", ignoreResourceNotFound = true)})

public class BackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);
    }
}

