package com.himawan.gymstis.service.implementation;

import com.himawan.gymstis.entity.Role;
import com.himawan.gymstis.enums.RoleEnum;
import com.himawan.gymstis.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            for (RoleEnum roleName : RoleEnum.values()) {
                if (roleRepository.findByName(roleName).isEmpty()) {
                    Role role = new Role();
                    role.setName(roleName);
                    roleRepository.save(role);
                }
            }
        };
    }
}
