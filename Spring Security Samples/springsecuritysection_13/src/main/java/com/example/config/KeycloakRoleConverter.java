package com.example.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

// Will have logic to get access token from keycloak server and extract info like roles from it
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	// Will convert JWT token from KeyCloak server and return a list of granted authorities because spring security can only understand the roles and authorities info in form of GrantedAuthority object
	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

		if (realmAccess == null || realmAccess.isEmpty()) {
			return new ArrayList<>();
		}

		Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("roles"))
				.stream().map(roleName -> "ROLE_" + roleName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return returnValue;
	}
}
