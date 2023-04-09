package org.example.controller;

import org.example.entity.Person;
import org.example.entity.Role;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("public")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("register")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		System.out.println("Person Obj: " + person);
		Role role;
		if (person.getName().equals("admin")) {
			role = roleRepository.findByRoleName("ADMIN");
		} else {
			role = roleRepository.findByRoleName("STUDENT");
		}
		person.setRole(role);
		person.setPwd(passwordEncoder.encode(person.getPwd()));
		return ResponseEntity.ok(personRepository.save(person));
	}

	@GetMapping("{id}")
	public ResponseEntity<Person> getPerson(@PathVariable int id) {
		return ResponseEntity.ok(personRepository.findById(id).get());
	}

	int pageSize = 2;

	@GetMapping("list")
	public ResponseEntity<Page<Person>> getAllPerson(@RequestParam int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Person> personPage = personRepository.findAll(pageable);
		return ResponseEntity.ok(personPage);
	}
}
