package com.nagarro.dockersample.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.dockersample.Entity.User;


@RestController
public class UserController {

	

    private List<User> customers;

    public UserController() {
        // Initialize the customers list and add dummy data
        customers = new ArrayList<>();
        customers.add(new User(1L, "John", "Doe", "john@example.com"));
        customers.add(new User(2L, "Jane", "Smith", "jane@example.com"));
    }

    @GetMapping("/customers")
    public List<User> getAllCustomers() {
        return customers;
    }
//
//    @GetMapping("/customers/{id}")
//    public User getCustomerById(@PathVariable Long id) {
//        // Find customer by id
//        for (User customer : customers) {
//            if (customer.getId().equals(id)) {
//                return customer;
//            }
//        }
//        return null; // Customer not found
//    }
    
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        // Find customer by id
        for (User customer : customers) {
            if (customer.getId().equals(id)) {
                return ResponseEntity.ok(customer);
            }
        }
        // Customer not found
        return ResponseEntity.notFound().build();
    }


}
