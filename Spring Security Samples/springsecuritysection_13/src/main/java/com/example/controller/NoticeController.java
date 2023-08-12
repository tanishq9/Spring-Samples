package com.example.controller;

import java.util.concurrent.TimeUnit;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

	@GetMapping("/notices")
	public ResponseEntity<String> getNoticeDetails() {
		return
				ResponseEntity.ok()
						.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
						.body("Here are notice details from the DB");
	}
}
