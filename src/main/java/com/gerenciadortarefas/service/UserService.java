package com.gerenciadortarefas.service;

import com.gerenciadortarefas.model.user.User;
import com.gerenciadortarefas.model.user.UserDTO;
import com.gerenciadortarefas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO createUser(UserDTO user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already registered.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = new User(user);
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    public UserDTO updateUser( Long id,  UserDTO userUpdate) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new Exception("User do not exist.");
        }

        User user = existingUser.get();
        if (!user.getEmail().equals(userUpdate.getEmail()) && userRepository.findByEmail(userUpdate.getEmail()).isPresent()) {
            throw new Exception("Email already registered.");
        }
        user.setEmail(userUpdate.getEmail());
        user.setName(userUpdate.getName());
        userRepository.save(user);
        return new UserDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
