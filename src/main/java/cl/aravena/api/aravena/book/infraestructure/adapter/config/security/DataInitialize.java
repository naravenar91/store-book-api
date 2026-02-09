package cl.aravena.api.aravena.book.infraestructure.adapter.config.security;

import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.RoleEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.UserEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository.JpaRoleRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

//Este componente se encarga de que, al levantar la app, ya existan usuarios para probar.
@Component
public class DataInitialize implements CommandLineRunner {

    private final JpaUserRepository userRepository; // Tu repo de persistencia
    private final JpaRoleRepository roleRepository; // Tu repo de roles
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

        // 2. Crear Usuario Admin
        if (!userRepository.existsByEmail("nicolas.aravena25@gmail.com")) {
            UserEntity admin = new UserEntity();
            admin.setEmail("nicolas.aravena25@gmail.com");
            admin.setPassword(passwordEncoder.encode("nicolas1234"));
            admin.setName("Nicolás Aravena Riquelme");
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);
        }

        // 3. Crear Usuario Normal
        if (!userRepository.existsByEmail("naravenar@outlook.com")) {
            UserEntity user = new UserEntity();
            user.setEmail("naravenar@outlook.com");
            user.setPassword(passwordEncoder.encode("nicolas"));
            user.setName("José Octavio Aravena Riquelme");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }
}