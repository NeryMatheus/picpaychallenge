package com.picpaychallenge.module.user.service;

import com.picpaychallenge.module.user.dtos.UserDTO;
import com.picpaychallenge.module.user.entities.User;
import com.picpaychallenge.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void ValidateUser(UserDTO userDto) throws Exception {
        if (this.userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new Exception("Email already exists");
        }
        if (this.userRepository.findByDocument(userDto.document()).isPresent()) {
            throw new Exception("Document already exists");
        }
    }

    public void saveUser(User user) { this.userRepository.save(user); }

    public User createUser(UserDTO userDto) throws Exception {
        ValidateUser(userDto);
        User newUser = new User(userDto);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
