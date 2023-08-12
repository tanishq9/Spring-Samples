package com.example.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

		// Below is custom security configuration
		http
				// With SessionCreationPolicy.STATELESS we are telling Spring Security framework to not generate any JSessionIds/Http Sessions
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
				corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
				corsConfiguration.setAllowCredentials(true);
				corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
				corsConfiguration.setMaxAge(3600L);
				// Exposing/Sending response header to client for Authorization
				corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
				return corsConfiguration;
			}
		})
				.and().csrf().disable()
				.authorizeHttpRequests((requests) -> {
					requests
							.requestMatchers("/myAccount").hasRole("USER")
							.requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
							.requestMatchers("/myLoans").authenticated()
							.requestMatchers("myCards").hasRole("USER")
							.requestMatchers("/user").authenticated()
							.requestMatchers("/notices", "/contact", "/register").permitAll();
				})
				.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);

		return http.build();
	}
}
