package org.example.controller;

import java.util.List;
import org.example.entity.Course;
import org.example.entity.Person;
import org.example.repository.CoursesRepository;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("course")
public class CourseController {

	@Autowired
	CoursesRepository coursesRepository;

	@Autowired
	PersonRepository personRepository;

	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		return ResponseEntity.ok(coursesRepository.save(course));
	}

	@GetMapping
	public ResponseEntity<List<Course>> getAll() {
		return ResponseEntity.ok(coursesRepository.findAll());
	}

	@PutMapping("{courseId}/{personId}")
	public ResponseEntity<Person> updatePersonCourse(@PathVariable int courseId, @PathVariable int personId) {
		Person person = personRepository.findById(personId).get();
		Course course = coursesRepository.findById(courseId).get();
		if (person != null && course != null) {
			person.getCourses().add(course);
			person.setConfirmEmail(person.getEmail()); // The field value match validation should only be done at time of registration
			personRepository.save(person);
			return ResponseEntity.ok(person);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
