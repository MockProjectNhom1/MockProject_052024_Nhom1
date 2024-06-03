package com.realeaste.MockProject_052024.controller;

import com.realeaste.MockProject_052024.entities.User;
import com.realeaste.MockProject_052024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String listFirstPage(Model model) {
        List<User> listUser = service.getAllUsers();
       
        model.addAttribute("listUser", listUser);
        return "user/user";
    }
}
