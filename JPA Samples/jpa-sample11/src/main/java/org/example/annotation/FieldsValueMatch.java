package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.example.validations.FieldsValueMatchValidator;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// For content matching between 2 fields
public @interface FieldsValueMatch {
	String field();

	String fieldMatch();

	String message() default "Fields value don't match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	// Required for Repeated annotation
	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		FieldsValueMatch[] value();
	}
}
