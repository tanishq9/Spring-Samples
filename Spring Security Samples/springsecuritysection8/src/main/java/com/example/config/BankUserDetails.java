package com.example.config;

import com.example.entity.Authority;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// Creating custom UserDetailsService for authentication
public class BankUserDetails implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userName, password = null;
		List<GrantedAuthority> authorityList = null;
		List<Customer> customers = customerRepository.findByEmail(username); // username will same as email
		if (customers.size() == 0) {
			throw new UsernameNotFoundException("Not found");
		} else {
			userName = customers.get(0).getEmail();
			password = customers.get(0).getPwd();

			Set<Authority> authorities = customers.get(0).getAuthorities();
			authorityList = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
		}
		// The AuthenticationProvider will compare password in this object with user passed password
		return new User(username, password, authorityList);
	}
}
