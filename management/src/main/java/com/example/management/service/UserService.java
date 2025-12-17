package com.example.management.service;

import com.example.management.model.User;
import com.example.management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User register(User user){
        return userRepository.save(user);

    }
    public Optional<User>login(String username, String password){
        Optional<User> user =userRepository.findByUsername(username);

        if(user.isPresent() && user.get().getPassword().equals(password)){
            return user;
        }
        return Optional.empty();
    }
}
