package org.example.security;

import java.util.ArrayList;
import java.util.List;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		Person person = personRepository.findByName(name);
		if (null != person && passwordEncoder.matches(password, person.getPwd())) {
			return new UsernamePasswordAuthenticationToken(
					name, password, getGrantedAuthority(person.getRole())
			);
		}
		return null;
	}

	private List<GrantedAuthority> getGrantedAuthority(Role role) {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		// Spring security will always maintain your roles with ROLE prefix.
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		return grantedAuthorityList;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// Whenever the authentication object is of type UsernamePasswordAuthenticationToken then only invoke the authenticate() method
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
