package com.example.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenValidationFilter extends OncePerRequestFilter {

	private static String JWT_KEY = "JustAKeyWhichIsSomethingAtleast256buts";
	private static String JWT_HEADER = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String jwt = request.getHeader(JWT_HEADER);
		if (jwt != null) {
			try {
				SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

				Claims claims = Jwts.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(jwt) // will throw exception in case signature mismatch or expired token, cmd+click to know more
						.getBody();

				String authorities = claims.get("authorities").toString();
				String[] authoritiesList = authorities.split(",");
				List<SimpleGrantedAuthority> authoritiesCollection = Arrays.stream(authoritiesList).map(authority -> new SimpleGrantedAuthority(authority)).toList();

				Authentication authentication = new UsernamePasswordAuthenticationToken(
						claims.get("username").toString(),
						null,
						authoritiesCollection
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);

				filterChain.doFilter(request, response);
			} catch (Exception exception) {
				throw new BadCredentialsException("Invalid Token received");
			}
		}
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/user"); // For every path other than /user i.e login operation
	}
}
