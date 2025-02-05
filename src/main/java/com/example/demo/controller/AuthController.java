package com.example.demo.controller;


import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController() {
    }

//    @PostMapping({"/signup"})
//    public ResponseEntity<String> signup(@RequestBody Users user) {
//        Optional<Users> existingUser = this.userService.findByUsername(user.getUsername());
//        if (existingUser.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists!");
//        } else {
//            String encodedPassword = this.passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword);
//            this.userService.saveUser(user);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
//        }
//    }

//    @PostMapping({"/login"})
//    public ResponseEntity<String> login(@RequestBody Users user) {
//        Optional<Users> existingUser = this.userService.findByUsername(user.getUsername());
//        if (existingUser.isPresent() && this.passwordEncoder.matches(user.getPassword(), ((Users)existingUser.get()).getPassword())) {
//            String token = this.jwtUtil.generateToken(user.getUsername());
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
//        }
//    }



    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Users user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists!");
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        Optional<Users> existingUser = userService.findByEmail(user.getEmail()); // Login using email
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            String token = jwtUtil.generateToken(existingUser.get().getEmail()); // Generate token using email
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
    }

    @GetMapping({"/validate-token"})
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (this.jwtUtil.validateToken(token)) {
                return ResponseEntity.ok("Token is valid!");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token!");
    }
}
