package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.controller;

import cl.aravena.api.aravena.book.infraestructure.adapter.config.security.JwtUtils;
import cl.aravena.api.aravena.book.domain.user.port.in.GetUserByUserNameUseCase;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final GetUserByUserNameUseCase getUserByUserNameUseCase;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(GetUserByUserNameUseCase getUserByUserNameUseCase, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // El AuthenticationManager llamará internamente a tu SpringDataUserRepository
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.userName(), loginRequest.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
