package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

	@GetMapping("/notices")
	public String getAccountDetails() {
		return "Here are notice details from the DB";
	}
}
