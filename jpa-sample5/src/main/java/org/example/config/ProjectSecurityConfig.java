package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
	public InMemoryUserDetailsManager userDetailsService() {
		// Ideally these user credentials would be fetched from DB

		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles("USER","ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
}
