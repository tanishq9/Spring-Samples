package com.example.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

	@GetMapping("/myLoans")
	// @PreAuthorize("hasRole('USER')")
	@PostAuthorize("hasRole('ROOT')")
	public String getAccountDetails() {
		return "Here are loan details from the DB";
	}
}
