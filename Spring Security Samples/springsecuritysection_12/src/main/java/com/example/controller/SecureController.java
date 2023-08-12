package com.example.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

	@GetMapping("/")
	public String get(OAuth2AuthenticationToken token) {
		System.out.println(token);
		return "secure.html";
	}
}
