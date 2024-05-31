package org.example.mockproject_052024_group1.service;

import lombok.AllArgsConstructor;
import org.example.mockproject_052024_group1.entities.ERole;
import org.example.mockproject_052024_group1.entities.Role;
import org.example.mockproject_052024_group1.repository.RoleRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role createRoleIfNotExists(ERole roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElse(null);

        if (role == null) {
            return roleRepository.save(Role.builder().roleName(roleName).build());
        }

        return roleRepository.findByRoleName(roleName).orElse(null);
    }
}
