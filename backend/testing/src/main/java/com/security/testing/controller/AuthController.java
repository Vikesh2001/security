package com.security.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.testing.entity.User;
import com.security.testing.security.JwtTokenUtil;
import com.security.testing.service.UserService;

@RestController
//@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
    	System.out.println("Signup endpoint hit with username: " + user.getUsername());
        boolean isRegistered = userService.register(user);
        if (isRegistered) {
            System.out.println("User registered successfully: " + user.getUsername());
            return ResponseEntity.ok("User registered successfully");
        } else {
            System.out.println("User already exists: " + user.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword())
                .map(u -> ResponseEntity.ok(jwtTokenUtil.generateToken(u.getUsername())))
                .orElse(ResponseEntity.status(401).body("Invalid username or password."));
    }
}
