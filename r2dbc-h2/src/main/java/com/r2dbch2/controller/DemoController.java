package com.r2dbch2.controller;

import com.r2dbch2.entity.Contact;
import com.r2dbch2.repository.ReactiveContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

// To create tables/execute schema in H2 DB, we can mention schema.sql file inside main/resources,
// it is not required if using Spring Data JPA and its annotations like Entity and Id,
// however it is required if using Spring Data R2DBC i.e. declaring a schema.sql named file under resources for schema creation in H2 DB.

@Controller
@RequestMapping("contacts")
public class DemoController {

	@Autowired
	ReactiveContactRepository contactRepository;

	@GetMapping("{id}")
	public Mono<ResponseEntity<Contact>> getContact(@PathVariable int id) {
		return contactRepository
				.findById(id)
				.map(mono -> ResponseEntity.ok(new Contact(mono.getContactId(), mono.getName(), mono.getMobileNumber())));
	}

	@PostMapping
	public Mono<ResponseEntity<Contact>> postContact(@RequestBody Contact contact) {
		return contactRepository
				.save(contact)
				.map(mono -> ResponseEntity.ok(new Contact(mono.getContactId(), mono.getName(), mono.getMobileNumber())));
	}
}
