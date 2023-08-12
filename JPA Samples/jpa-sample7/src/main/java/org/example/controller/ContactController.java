package org.example.controller;

import java.util.List;
import org.example.entity.Contact;
import org.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// To create tables/execute schema in H2 DB, we can mention init.sql file inside main/resources,
// it is not required if using Spring Data JPA and its annotations like Entity and Id,
// however it is required if using Spring Data R2DBC i.e. declaring a init.sql named file under resources for schema creation in H2 DB.

@RestController
@RequestMapping("contacts")
public class ContactController {
	@Autowired
	ContactRepository contactRepository;

	@GetMapping("{id}")
	public ResponseEntity<Contact> getContact(@PathVariable int id) {
		return ResponseEntity.ok(contactRepository.findById(id).orElse(null));
	}

	@PostMapping
	public ResponseEntity<Contact> postContact(@RequestBody Contact contact) {
		// contact.setCreatedBy("Anonymous");
		// contact.setCreatedAt(LocalDateTime.now());
		return ResponseEntity.ok(contactRepository.save(contact));
	}

	@GetMapping
	public ResponseEntity<List<Contact>> getContactsByNameAndEmail(@RequestParam String email, @RequestParam String name) {
		return ResponseEntity.ok(contactRepository.findByEmailAndName(email, name));
	}
}
