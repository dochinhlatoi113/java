package com.aptech.spring01.controllers.customer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.models.Customer;
import com.aptech.spring01.models.Product;
import com.aptech.spring01.models.Role;
import com.aptech.spring01.service.customer.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService cService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(required = false, name = "keyword") String searchTerm) {
        if (searchTerm == null) {
            List<Customer> customers = cService.getAll();
            return ResponseEntity.ok(customers);
        } else {
            List iteList = cService.findCustomers(searchTerm);
            return ResponseEntity.ok(iteList);
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @ModelAttribute Customer c, BindingResult bindingResult) {
        // Validate the input
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input data");
        }

        if (cService.findExitsEmail(c)) {

            Map<String, String> hashMap = new HashMap<>();

            hashMap.put("status", "409");
            hashMap.put("message", "Email already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(hashMap);
        }

        Customer createdCustomer = cService.create(c);
        return ResponseEntity.ok(createdCustomer);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> edit(@PathVariable Integer id) {
        try {
            final Customer customer = cService.findCustomerById(id);

            if (customer != null) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
            } else {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("status", "404");
                hashMap.put("message", "Customer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(hashMap);
            }

        } catch (Exception ex) {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("status", "500");
            hashMap.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMap);
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, String firstName, String lastName, String password,
            Integer profle) {
        try {
            final Customer customer = cService.findCustomerById(id);

            if (customer != null) {
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setPassword(password);
                customer.setProfile(null);
                cService.create(customer);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
            } else {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("status", "404");
                hashMap.put("message", "Customer not found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(hashMap);
            }

        } catch (Exception ex) {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("status", "500");
            hashMap.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMap);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            final Customer customer = cService.findCustomerById(id);
            if (customer != null) {
                cService.deleteCustomer(id);
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("status", "200");
                hashMap.put("message", "sucesss");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(hashMap);
            } else {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("status", "403");
                hashMap.put("message", "customer not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
            }

        } catch (Exception e) {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("status", "500");
            hashMap.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hashMap);

        }

    }

}
