package com.realeaste.MockProject_052024.security;


import com.realeaste.MockProject_052024.entities.User;
import com.realeaste.MockProject_052024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class DemoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.getUserByEmail(email);

        if(user != null) {
            return new DemoUserDetails(user);
        }
        throw new UsernameNotFoundException("Could not find user with email" + email);
    }
}
