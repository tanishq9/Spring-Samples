package org.example.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.example.entity.Holiday;
import org.example.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("holidays")
@Controller
public class HolidayController {
	@Autowired
	HolidaysRepository holidaysRepository;

	@GetMapping
	public ResponseEntity<List<Holiday>> getAll() {
		Iterable<Holiday> holidayIterable = holidaysRepository.findAll();
		List<Holiday> holidayList = StreamSupport.stream(holidayIterable.spliterator(), false).collect(Collectors.toList());
		return ResponseEntity.ok(holidayList);
	}
}
