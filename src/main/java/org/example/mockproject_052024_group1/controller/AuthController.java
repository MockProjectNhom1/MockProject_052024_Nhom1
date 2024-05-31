package org.example.mockproject_052024_group1.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.mockproject_052024_group1.dto.LoginRequest;
import org.example.mockproject_052024_group1.dto.RegisterRequest;
import org.example.mockproject_052024_group1.entities.ERole;
import org.example.mockproject_052024_group1.entities.User;
import org.example.mockproject_052024_group1.entities.Role;
import org.example.mockproject_052024_group1.repository.RoleRepository;
import org.example.mockproject_052024_group1.security.JwtUtils;
import org.example.mockproject_052024_group1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private UserService userService;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtUtils jwtUtils;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String jwt = jwtUtils.generateAccessToken(userDetails);

            return ResponseEntity.ok(jwt);

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {

            ERole roleName = ERole.valueOf(registerRequest.getRoleName());

            User newUser = User.builder()
                    .username(registerRequest.getUsername())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .role(Role.builder().roleName(roleName).build())
                    .firstName(registerRequest.getFirstName())
                    .lastName(registerRequest.getLastName())
                    .email(registerRequest.getEmail())
                    .build();

            userService.createUser(newUser);

            return ResponseEntity.ok(userService.getByUsername(registerRequest.getUsername()));

        } catch (Exception e) {
            logger.warning(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
