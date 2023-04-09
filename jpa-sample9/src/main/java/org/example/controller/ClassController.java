package org.example.controller;

import org.example.entity.EazyClass;
import org.example.entity.Person;
import org.example.repository.EazyClassRepository;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("class")
public class ClassController {

	@Autowired
	EazyClassRepository eazyClassRepository;

	@Autowired
	PersonRepository personRepository;

	@PostMapping("create")
	public ResponseEntity<EazyClass> createClass(@RequestBody EazyClass eazyClass) {
		return ResponseEntity.ok(eazyClassRepository.save(eazyClass));
	}

	@PostMapping("update/{classId}/{personId}")
	public ResponseEntity<EazyClass> updateClass(@PathVariable int classId, @PathVariable int personId) {
		Person person = personRepository.findById(personId).get();
		EazyClass eazyClass = eazyClassRepository.findById(classId).get();
		if (person != null && eazyClass != null) {
			person.setEazyClass(eazyClass);
			person.setConfirmEmail(person.getEmail()); // The field value match validation should only be done at time of registration
			personRepository.save(person);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
