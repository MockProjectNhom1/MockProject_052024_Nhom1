package org.example.mockproject_052024_group1.controller;

import lombok.AllArgsConstructor;
import org.example.mockproject_052024_group1.entities.User;
import org.example.mockproject_052024_group1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {

    private UserService userService;

    //http://localhost:8080/hello
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello! ❤️");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with id: " + id);
        }
        return ResponseEntity.ok(user);
    }

}
