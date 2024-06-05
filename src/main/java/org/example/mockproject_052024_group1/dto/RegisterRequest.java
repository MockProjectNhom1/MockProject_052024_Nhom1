package org.example.mockproject_052024_group1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Confirm Password is required")
    private String confirmPassword;

    @NotNull(message = "Role name is required")
    private String roleName;

    private String firstName;

    private String lastName;
}
