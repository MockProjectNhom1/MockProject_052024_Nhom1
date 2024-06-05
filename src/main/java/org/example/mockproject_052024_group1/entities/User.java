package org.example.mockproject_052024_group1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * This class ...
 *
 * @author Chien Ma
 * @version 1.0
 */


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Role is required")
    @ManyToOne()
    private Role role;

    @Column(columnDefinition = "nvarchar(255)")
    private String firstName;

    @Column(columnDefinition = "nvarchar(255)")
    private String lastName;

}
