package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	BankUserDetails bankUserDetails;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String presentedPassword = authentication.getCredentials().toString();
		try {
			UserDetails userDetails = bankUserDetails.loadUserByUsername(username);
			if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
				throw new BadCredentialsException("Invalid password");
			}
			// returning an object of UsernamePasswordAuthenticationToken implies authentication was successful
			// Open UsernamePasswordAuthenticationToken (CMD + Click) class constructor to check same
			return new UsernamePasswordAuthenticationToken(username, presentedPassword, userDetails.getAuthorities());
		} catch (Exception exception) {
			throw new BadCredentialsException("User not registered");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// UsernamePasswordAuthentication is used to authenticate user with username and password
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
