package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.EazyClass;
import org.example.entity.Person;
import org.example.repository.EazyClassRepository;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("class")
public class ClassController {

	@Autowired
	EazyClassRepository eazyClassRepository;

	@Autowired
	PersonRepository personRepository;

	@PostMapping
	public ResponseEntity<EazyClass> createClass(@RequestBody EazyClass eazyClass) {
		log.info("Log Info Message");
		return ResponseEntity.ok(eazyClassRepository.save(eazyClass));
	}

	@PutMapping("{classId}/{personId}")
	public ResponseEntity<Person> updateClass(@PathVariable int classId, @PathVariable int personId) {
		Person person = personRepository.findById(personId).get();
		EazyClass eazyClass = eazyClassRepository.findById(classId).get();
		if (person != null && eazyClass != null) {
			person.setEazyClass(eazyClass);
			person.setConfirmEmail(person.getEmail()); // The field value match validation should only be done at time of registration
			personRepository.save(person);
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.header("header1", "value1")
					.header("header2", "value2")
					.body(person);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
