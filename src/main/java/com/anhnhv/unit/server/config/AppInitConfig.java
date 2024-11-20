package com.anhnhv.unit.server.config;

import com.anhnhv.unit.server.entities.Role;
import com.anhnhv.unit.server.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppInitConfig {

    private final RoleRepository roleRepository;

    @Bean
    ApplicationRunner init() {
        return args -> {
            if(roleRepository.findAll().isEmpty()){
                Role roleUser = new Role();
                roleUser.setRoleName("ROLE_USER");
                roleRepository.save(roleUser);
                Role roleAdmin = new Role();
                roleAdmin.setRoleName("ROLE_ADMIN");
                roleRepository.save(roleAdmin);
                log.info("Init roles");
            }

        };
    }
}
