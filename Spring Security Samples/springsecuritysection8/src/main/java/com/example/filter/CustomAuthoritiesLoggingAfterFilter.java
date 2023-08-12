package com.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomAuthoritiesLoggingAfterFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Get details for current authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			System.out.println("User: " + authentication.getName() + ", authorities: " + authentication.getAuthorities());
		}
		chain.doFilter(request, response);
	}
}
