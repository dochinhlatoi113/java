package com.aptech.spring01.repository;

import com.aptech.spring01.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    public User deleteUserById(Integer id);
}

