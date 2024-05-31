package org.example.mockproject_052024_group1.service;

import org.example.mockproject_052024_group1.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getById(Long id);

    public Optional<User> getByUsername(String username);

    public List<User> getAllUsers();

    public Optional<User> getByEmail(String email);

    public User createUser(User user);

    public Optional<User> updateUser(User user);

    public void deleteUser(User user);

}
