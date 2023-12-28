package com.aptech.spring01.repository;

import com.aptech.spring01.models.Role;
import com.aptech.spring01.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}

