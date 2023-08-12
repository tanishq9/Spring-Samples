package com.example.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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

	/*@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		// Approach 1 withDefaultPasswordEncoder is for plaintext password
		*//*UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("123")
				.authorities("admin")
				.build();

		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("123")
				.authorities("read")
				.build();*//*

		// Approach 2 using NoOpPasswordEncoder bean is for plaintext password
		UserDetails admin = User
				.withUsername("admin")
				.password("123")
				.authorities("admin")
				.build();

		UserDetails user = User
				.withUsername("user")
				.password("123")
				.authorities("read")
				.build();

		return new InMemoryUserDetailsManager(admin, user);
	}*/

	/*@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
