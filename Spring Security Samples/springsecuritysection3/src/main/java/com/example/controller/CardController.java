package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

	@GetMapping("/myCards")
	public String getAccountDetails() {
		return "Here are card details from the DB";
	}
}
