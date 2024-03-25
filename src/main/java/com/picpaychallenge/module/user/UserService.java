package com.picpaychallenge.module.user;

import com.picpaychallenge.module.user.dtos.UserDTO;
import com.picpaychallenge.module.user.entities.User;
import com.picpaychallenge.module.user.enums.UserType;
import com.picpaychallenge.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void ValidateTransaction(User sender, User receiver, BigDecimal value) throws Exception {
        if (sender.getId().equals(receiver.getId())) {
            throw new Exception("Sender and receiver cannot be the same");
        }

        if (sender.getType() == UserType.MERCHANT) {
            throw new Exception("Merchants cannot make transactions");
        }

        if (sender.getBalance().compareTo(value) < 0) {
            throw new Exception("Insufficient balance");
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

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow();
    }
}
