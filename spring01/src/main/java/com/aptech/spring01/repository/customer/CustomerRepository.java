package com.aptech.spring01.repository.customer;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.models.Customer;
import com.aptech.spring01.models.User;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public boolean existsByEmail(String email);

}
