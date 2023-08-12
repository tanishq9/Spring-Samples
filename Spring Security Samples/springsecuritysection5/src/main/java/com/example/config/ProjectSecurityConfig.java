package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// Below is custom security configuration
		http
				.csrf().disable() // will be addressed later - By default spring security is going to deny all POST / PUT type requests to avoid CSRF attack.
				.authorizeHttpRequests((requests) -> {
			requests
					.requestMatchers("/myAccount", "/myBalance", "/myLoans", "myCards").authenticated()
					.requestMatchers("/notices", "/contact", "/register").permitAll();
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
