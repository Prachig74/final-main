package com.example.demo.service;



import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService() {
    }

//    public Users registerUser(String username, String password) {
//        Users user = new Users();
//        user.setUsername(username);
//        user.setPassword(this.passwordEncoder.encode(password));
//        return (Users)this.userRepository.save(user);
//    }

    public Users registerUser(String username, String email, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setEmail(email); // Set email field
        user.setPassword(this.passwordEncoder.encode(password));
        return userRepository.save(user);
    }

        public Optional<Users> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email); // Added method to find user by email
    }

    public void saveUser(Users user) {
        this.userRepository.save(user);
    }
}
