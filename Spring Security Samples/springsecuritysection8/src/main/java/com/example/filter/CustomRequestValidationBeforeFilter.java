package com.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CustomRequestValidationBeforeFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Pre-processing logic here, for example, authentication or logging
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String header = httpServletRequest.getHeader("Authorization");
		if (header != null) {
			try {
				header = header.trim();
				byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
				byte[] decoded;
				decoded = Base64.getDecoder().decode(base64Token);
				String token = new String(decoded, Charset.defaultCharset());
				int delim = token.indexOf(":");
				if (token.substring(0, delim).contains("test")) {
					return;
				}
			} catch (Exception exception) {
				throw exception;
			}
		}
		chain.doFilter(request, response);
		// Post-processing logic here, modify response or add headers
		System.out.println("Post-Processing");
	}
}
