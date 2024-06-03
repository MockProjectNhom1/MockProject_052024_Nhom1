package com.realeaste.MockProject_052024.service;


import com.realeaste.MockProject_052024.entities.Role;
import com.realeaste.MockProject_052024.entities.User;
import com.realeaste.MockProject_052024.repository.RoleRepository;
import com.realeaste.MockProject_052024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public static final int USER_PER_PAGE = 4;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public List<Role> listRole(){
        return (List<Role>) roleRepo.findAll();
    }

    public User saveUser(User user) {
        boolean isUpdatingUser = (user.getId() != null);

        if (isUpdatingUser) {
            User existingUser = userRepo.getUserByEmail(user.getEmail());

            if(user.getPassword().isEmpty()){
                user.setPassword(existingUser.getPassword());
            }else {
                encodePassword(user);
            }
        }else {
            encodePassword(user);
        }
        return userRepo.save(user);
    }

    private void encodePassword(User user) {
        String  encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


    public void updateUserEnabledStatus(Integer id, boolean enabled) {
        userRepo.updateEnabledStatus(id,enabled);
    }
}
