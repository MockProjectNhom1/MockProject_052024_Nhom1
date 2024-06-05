package org.example.mockproject_052024_group1.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.mockproject_052024_group1.dto.LoginRequest;
import org.example.mockproject_052024_group1.dto.RegisterRequest;
import org.example.mockproject_052024_group1.entities.ERole;
import org.example.mockproject_052024_group1.entities.Role;
import org.example.mockproject_052024_group1.entities.User;
import org.example.mockproject_052024_group1.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private JwtUtils jwtUtils;

    public User registerUser(@Valid RegisterRequest registerRequest) {
        ERole roleName = ERole.valueOf(registerRequest.getRoleName());

        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.builder().roleName(roleName).build())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();

        return userService.createUser(newUser);
    }

    public String loginUser(@Valid LoginRequest loginRequest, HttpServletResponse response) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String jwt = jwtUtils.generateAccessToken(userDetails);

        Cookie cookie = new Cookie("Authorization", jwt);
        cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(60));
        cookie.setPath("/");
        cookie.setDomain("localhost");
        response.addCookie(cookie);

        response.addHeader("Authorization", "Bearer " + jwt );

        return jwt;
    }
}
