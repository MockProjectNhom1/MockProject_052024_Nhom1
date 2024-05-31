package org.example.mockproject_052024_group1.service;

import org.example.mockproject_052024_group1.entities.ERole;
import org.example.mockproject_052024_group1.entities.Role;

public interface RoleService {
    public Role createRoleIfNotExists(ERole roleName);
}
