package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.api.ReturnApiDTO;
import com.gerenciadortarefas.model.user.UserDTO;
import com.gerenciadortarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ReturnApiDTO> createUser(@RequestBody UserDTO user) {
        try {
            UserDTO newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ReturnApiDTO(201, newUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnApiDTO.exception(500, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnApiDTO> updateUser(@PathVariable Long id,  @RequestBody UserDTO user) {
        try {
            UserDTO updatedUser = userService.updateUser(id, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ReturnApiDTO(201, updatedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnApiDTO.exception(500, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnApiDTO> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ReturnApiDTO.delete());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ReturnApiDTO.exception(500, e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(user.getUsername());
    }

}
