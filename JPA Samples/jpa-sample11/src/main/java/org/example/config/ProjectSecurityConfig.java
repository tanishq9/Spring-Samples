package org.example.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	/**
	 * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
	 * to move towards a component-based security configuration. It is recommended to create a bean
	 * of type SecurityFilterChain for security related configurations.
	 *
	 * @param httpSecurity
	 * @return SecurityFilterChain
	 * @throws Exception
	 */

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

		// This is to disable CSRF protection for all webpages
		httpSecurity.csrf().disable();

		// This is to permit all requests without any authentication/login
		/*httpSecurity.authorizeHttpRequests()
				.anyRequest()
				.permitAll();*/

		// Path/Route based security configuration
		httpSecurity.authorizeHttpRequests()
				.mvcMatchers(POST, "/public/**").permitAll()
				//.mvcMatchers(GET, "/public/**").hasRole("STUDENT") // hasRole() implies user is authenticated, now we will check if the authenticated user has required role
				.mvcMatchers(GET, "/public/**").permitAll()
				.mvcMatchers("/class/**").hasRole("ADMIN")
				.mvcMatchers("/course/**").hasRole("ADMIN")
				.mvcMatchers("/holidays").authenticated() // The client sends HTTP requests with the Authorization header that contains the word Basic word followed by a space and a base64-encoded string username:password
				.mvcMatchers("/contacts").permitAll();

		// This is to support html form logic and http basic i.e. http headers login
		httpSecurity.formLogin().and().httpBasic();

		// This is the default security config enforced by spring security
		/*httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		httpSecurity.formLogin();
		httpSecurity.httpBasic();*/
		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
