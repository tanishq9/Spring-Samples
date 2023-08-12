package com.example.controller;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	CustomerRepository customerRepository;

	// Assume this as the endpoint called when SignIn button is clicked
	@PostMapping("/user")
	private Customer getCustomer(Authentication authentication) {
		String email = authentication.getName();
		try {
			return customerRepository.findByEmail(email).get(0);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
	}
}
