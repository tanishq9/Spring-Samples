package com.example.config;

import com.example.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// An implementation of the CsrfTokenRequestHandler interface that is capable of making the CsrfToken available.
		CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
		csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

		// Below is custom security configuration
		http
				// CORS restrictions are enforced by web browsers.
				// It's important to note that while CORS is enforced by browsers, it doesn't affect direct server-to-server communication or other types of non-browser interactions.
				// Usually browsers will make a pre-flight request i.e. before making the actual API call (for different origins), the browser will make a pre-flight request to backend server saying that this is the origin communicating with you and are you fine with that? If backend app says its a valid origin THEN the browser is going to send the actual request to the backend.
				// Example: An app hosted at localhost:4200 calling an API hosted at localhost:8080 via browser, browser will first make a preflight request to localhost:8080 before making calling actual API at 8080 port. Basically for any call that a frontend app makes to backend service, that particular backend service will need to allow CORS requests from that particular frontend.
				.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
				corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
				corsConfiguration.setAllowCredentials(true);
				corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
				corsConfiguration.setMaxAge(3600L);
				return corsConfiguration;
			}
		})
				.and().csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
			@Override
			public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
				httpSecurityCsrfConfigurer
						.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler) // Generate Cookie
						.ignoringRequestMatchers("/contact", "/register")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // A CsrfTokenRepository that persists the CSRF token in a cookie named "XSRF-TOKEN" and reads from the header "X-XSRF-TOKEN" following the conventions of AngularJS. When using with AngularJS be sure to use withHttpOnlyFalse().
			}
		}).addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				//.and().csrf().ignoringRequestMatchers("/contact", "/register") // We can disable CSRF for those POST APIs which are publicly accessible i.e. doesn't require user creds. CSRF attack and protection are meant for the secured APIs.
				//.and()
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
