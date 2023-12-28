package com.aptech.spring01.service;

import com.aptech.spring01.models.Role;
import com.aptech.spring01.models.User;
import com.aptech.spring01.repository.RoleRepository;
import com.aptech.spring01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
