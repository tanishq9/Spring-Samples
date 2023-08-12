package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity(debug = true)
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// Below is custom security configuration
		/*http.authorizeHttpRequests((requests) -> {
			requests
					.requestMatchers("/myAccount", "/myBalance", "/myLoans", "myCards").authenticated()
					.requestMatchers("/notices", "/contact").permitAll();
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());*/
		// Configuration to deny all request
		/*http.authorizeHttpRequests((requests) -> {
			requests.anyRequest().denyAll();
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());*/

		// Configuration to permit all request
		http.authorizeHttpRequests((requests) -> {
			requests.anyRequest().permitAll();
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
