package org.example.controller;

import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("public")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@PostMapping("register")
	public ResponseEntity<Person> createUser(@RequestBody Person person) {
		System.out.println("Person Obj: " + person);
		return ResponseEntity.ok(personRepository.save(person));
	}
}
