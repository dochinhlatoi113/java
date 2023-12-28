package com.aptech.spring01.repository.customer;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.models.Customer;
import com.aptech.spring01.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public boolean existsByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Customer> searchCustomer(@Param("keyword") String keyword);
}
