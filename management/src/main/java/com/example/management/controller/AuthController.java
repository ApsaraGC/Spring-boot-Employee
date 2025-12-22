package com.example.management.controller;

import com.example.management.model.User;
import com.example.management.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService =userService;
    }
    @PostMapping("/admin-login")
    public String adminLogin(@RequestBody User user){
    Optional<User>loggedUser=userService.login(user.getUsername(), user.getPassword());

    if(loggedUser.isPresent()) {
        if ("ROLE_ADMIN".equalsIgnoreCase(loggedUser.get().getRole())) {
            return "Admin login Successful";
        } else {
            return "Access Denied:Not an Admin";
        }
    }else {
        return "Invalid Credentails";
    }
    }
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        Optional<User> loggedUser =
                userService.login(user.getUsername(), user.getPassword());
        return loggedUser.isPresent() ? "Login Successful": "Invalid Credentials";
    }
}
