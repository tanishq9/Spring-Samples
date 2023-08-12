package org.example.controller;

import org.example.entity.Contact;
import org.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// To create tables/execute schema in H2 DB, we can mention schema.sql file inside main/resources,
// it is not required if using Spring Data JPA and its annotations like Entity and Id,
// however it is required if using Spring Data R2DBC i.e. declaring a schema.sql named file under resources for schema creation in H2 DB.

@Controller
@RequestMapping("contacts")
public class DemoController {
	@Autowired
	ContactRepository contactRepository;

	@GetMapping("{id}")
	public ResponseEntity<Contact> getContact(@PathVariable int id) {
		return ResponseEntity.ok(contactRepository.findById(id).orElse(null));
	}

	@PostMapping
	public ResponseEntity<Contact> postContact(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactRepository.save(contact));
	}
}
