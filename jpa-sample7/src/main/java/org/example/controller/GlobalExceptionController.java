package org.example.controller;

import javax.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
@ControllerAdvice is a specialization of the @Component annotation which allows to handle
exceptions across the whole application in one global handling component. It can be viewed
as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
* */
@ControllerAdvice
public class GlobalExceptionController {

	// If a method annotated with @ExceptionHandler present inside a @Controller class, then the exception handling logic will be applicable for any exceptions occurred in that specific controller class.
	// If the the @ExceptionHandler annotated method is present inside a @ControllerAdvice class, then the exception handling logic will be applicable for any exceptions occurred across all the controller classes.

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity handleValidationError(Exception exception) {
		System.out.println("In Global Exception Handler: " + exception.getMessage());
		return ResponseEntity.badRequest().build();
	}
}
