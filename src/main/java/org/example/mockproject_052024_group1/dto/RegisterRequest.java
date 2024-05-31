package org.example.mockproject_052024_group1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role name is required")
    private String roleName;

    private String email;

    private String firstName;

    private String lastName;
}
