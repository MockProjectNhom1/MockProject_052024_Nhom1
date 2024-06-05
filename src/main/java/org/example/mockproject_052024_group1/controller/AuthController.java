package org.example.mockproject_052024_group1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.mockproject_052024_group1.dto.LoginRequest;
import org.example.mockproject_052024_group1.dto.RegisterRequest;
import org.example.mockproject_052024_group1.service.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute LoginRequest loginRequest, HttpServletResponse response) {
        try {
            String jwt = authService.loginUser(loginRequest, response);

            logger.info("Login successful. JWT: " + jwt);

            return "loginSuccess";

        } catch (BadCredentialsException e) {
            logger.warning(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("registerRequest") RegisterRequest registerRequest) {
        try {
            registerRequest.setRoleName("CUSTOMER");

            if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
                model.addAttribute("error", "Passwords do not match");
                return "register";
            }

            logger.info(registerRequest.toString());

            authService.registerUser(registerRequest);

            model.addAttribute("loginRequest", new LoginRequest());

            return "login";

        } catch (Exception e) {
            logger.warning(e.getMessage());
            return e.getMessage();
        }
    }
}
