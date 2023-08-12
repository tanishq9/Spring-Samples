package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

	@GetMapping("/myLoans")
	public String getAccountDetails() {
		return "Here are loan details from the DB";
	}
}
