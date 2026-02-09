package cl.aravena.api.aravena.book.infraestructure.adapter.config.security;

import cl.aravena.api.aravena.book.domain.user.models.User;
import cl.aravena.api.aravena.book.domain.user.port.out.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository; // El puerto del dominio

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos el User de dominio
        User userDomain = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // 2. Mapeamos la lista de Strings directamente a GrantedAuthority
        List<SimpleGrantedAuthority> authorities = userDomain.roles().stream()
                .map(SimpleGrantedAuthority::new) // El String ya es "ROLE_ADMIN"
                .toList();

        // 3. Retornamos el User de Spring Security
        return new org.springframework.security.core.userdetails.User(
                userDomain.email(),
                userDomain.password(),
                authorities
        );
    }
}
