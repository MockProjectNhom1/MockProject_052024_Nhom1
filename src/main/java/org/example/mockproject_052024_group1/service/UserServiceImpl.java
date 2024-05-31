package org.example.mockproject_052024_group1.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.mockproject_052024_group1.entities.Role;
import org.example.mockproject_052024_group1.entities.User;
import org.example.mockproject_052024_group1.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleService roleService;

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return An Optional containing the user with the given username, or empty if not found.
     */
    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(@Valid User newUser) {
        Role role = roleService.createRoleIfNotExists(newUser.getRole().getRoleName());
        newUser.setRole(role);

        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> updateUser(User user) throws UsernameNotFoundException {
        userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + user.getUsername()));

        Role role = roleService.createRoleIfNotExists(user.getRole().getRoleName());
        user.setRole(role);

        return Optional.of(userRepository.save(user));
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


}
