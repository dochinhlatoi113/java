package com.aptech.spring01.service.customer;

import com.aptech.spring01.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.models.Customer;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository cRepository;

    public List<Customer> getAll() {
        return cRepository.findAll();
    }

    public Customer create(Customer c) {
        try {
            cRepository.save(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public boolean findExitsEmail(Customer customer) {
        String email = customer.getEmail();
        return cRepository.existsByEmail(email);
    }

    public Customer findCustomerById(Integer id) {
        return cRepository.findById(id).orElse(null);
    }

    public boolean deleteCustomer(Integer id) {
        try {
            cRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
