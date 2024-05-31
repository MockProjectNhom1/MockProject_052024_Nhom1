package org.example.mockproject_052024_group1.repository;

import org.example.mockproject_052024_group1.entities.ERole;
import org.example.mockproject_052024_group1.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByRoleName(ERole roleName);
}
