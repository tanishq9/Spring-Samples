package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// We are not using form login or http basic, we would be using oauth2 style of login
		http.authorizeHttpRequests().anyRequest().authenticated().and().oauth2Login();
		return http.build();
	}

	// To tell Spring security where client registration details are stored
	@Bean
	public ClientRegistrationRepository clientRepository() {
		ClientRegistration clientReg = clientRegistration();
		return new InMemoryClientRegistrationRepository(clientReg);
	}

	private ClientRegistration clientRegistration() {
		return CommonOAuth2Provider.GITHUB.getBuilder("github")
				.clientId("123")
				.clientSecret("123").build();
	}
}
