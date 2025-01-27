package com.security.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.dto.LoginResponseDTO;
import com.security.testing.entity.User;
import com.security.testing.security.JwtTokenUtil;
import com.security.testing.security.Status;
import com.security.testing.service.UserService;

@RestController
//@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        boolean isRegistered = userService.register(user);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/auth/login")
    public LoginResponseDTO login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword())
                .map(u -> {
                    // Success response
                    Status successStatus = new Status("SUCCESS", "Seccessfully Login");
                    return new LoginResponseDTO(successStatus, 
                            jwtTokenUtil.generateToken(u.getUsername()), 
                            u.getUsername());
                })
                .orElseGet(() -> {
                    // Failure response
                    Status failureStatus = new Status("FAILURE", "Invalid login please try again");
                    return new LoginResponseDTO(failureStatus, null, null);
                });
    }





}
