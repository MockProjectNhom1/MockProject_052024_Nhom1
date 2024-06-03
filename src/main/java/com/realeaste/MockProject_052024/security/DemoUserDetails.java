package com.realeaste.MockProject_052024.security;


import com.realeaste.MockProject_052024.entities.Role;
import com.realeaste.MockProject_052024.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class DemoUserDetails implements UserDetails {


    private User user;


    public DemoUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorizes = new ArrayList<>();

        for(Role role : roles) {
            authorizes.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorizes;
    }
    // get password
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    // get  username
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getFullName() {
        return this.user.getFirstName() + " " + this.user.getLastName();
    }
}
