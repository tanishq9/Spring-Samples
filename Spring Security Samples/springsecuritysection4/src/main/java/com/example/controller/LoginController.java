package com.example.controller;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		try {
			String hashedPassword = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashedPassword);
			customerRepository.save(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Registered user");
		} catch (Exception exception) {
			return ResponseEntity.internalServerError().body(exception.getMessage());
		}
	}
}
