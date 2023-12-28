package com.aptech.spring01.service;

import com.aptech.spring01.models.Profile;
import com.aptech.spring01.models.User;
import com.aptech.spring01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean delete(Integer userId) {
        try {
            userRepository.deleteById(userId);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public User findUserById(Integer userId) {
        User user = userRepository.findById(userId).get();
        if (user.getProfile() == null || user.getProfile().getId() == 0) {
            user.setProfile(new Profile());
        }

        return user;
    }

    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

}
