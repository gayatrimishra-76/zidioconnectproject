package com.zidio.auth.controller;

import com.zidio.auth.dto.LoginDTO;
import com.zidio.auth.dto.UserDTO;
import com.zidio.auth.model.User;
import com.zidio.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());  // In real apps, hash it!
        newUser.setRole(userDTO.getRole());

        userService.saveUser(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.getUserByUsername(loginDTO.getUsername());

        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.ok("Login success — Role: " + user.getRole());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
