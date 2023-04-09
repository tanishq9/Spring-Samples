package org.example.controller;

import org.example.entity.Person;
import org.example.entity.Role;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("public")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	RoleRepository roleRepository;

	@PostMapping("register")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		System.out.println("Person Obj: " + person);
		Role role = roleRepository.findByRoleName("STUDENT");
		person.setRole(role);
		return ResponseEntity.ok(personRepository.save(person));
	}

	@GetMapping("{id}")
	public ResponseEntity<Person> getPerson(@PathVariable int id) {
		return ResponseEntity.ok(personRepository.findById(id).get());
	}
}
