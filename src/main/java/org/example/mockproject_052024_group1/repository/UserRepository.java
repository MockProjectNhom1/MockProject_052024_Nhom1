package org.example.mockproject_052024_group1.repository;

import org.example.mockproject_052024_group1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for accessing user data in the database.
 *
 * <p>
 * This repository interface provides methods for accessing user data in the database,
 * such as finding a user by username.
 * </p>
 *
 * @author Chien Ma
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the user with the given email, or empty if not found.
     */
    Optional<User> findByEmail(String email);
}
