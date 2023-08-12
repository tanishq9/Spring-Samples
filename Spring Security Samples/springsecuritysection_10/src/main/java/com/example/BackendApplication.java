package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity(debug = true)
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication. run(BackendApplication.class, args);
	}

}
