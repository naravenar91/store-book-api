package cl.aravena.api.aravena.book.infraestructure.adapter.config.security;

import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.RoleEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.UserEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository.JpaRoleRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitialize implements CommandLineRunner {

    private final JpaUserRepository userRepository;
    private final JpaRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitialize(JpaUserRepository userRepository, JpaRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        RoleEntity adminRole = roleRepository.findRoleByName("ROLE_ADMIN")
                .orElseThrow(() -> new NotFoundException("Role", "ROLE_ADMIN"));

        RoleEntity userRole = roleRepository.findRoleByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("Role", "ROLE_USER"));

        // Admin user
        if (!userRepository.existsByEmail("admin_email_test25@test.com")) {
            UserEntity admin = new UserEntity();
            admin.setEmail("admin_email_test25@test.com");
            admin.setPassword(passwordEncoder.encode("test1234"));
            admin.setName("Juan");
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);
        }

        // Normal  user
        if (!userRepository.existsByEmail("normal_email_test@test.com")) {
            UserEntity user = new UserEntity();
            user.setEmail("normal_email_test@test.com");
            user.setPassword(passwordEncoder.encode("test1234"));
            user.setName("Pedro");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }
}