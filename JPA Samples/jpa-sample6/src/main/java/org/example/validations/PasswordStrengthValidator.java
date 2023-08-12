package org.example.validations;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.example.annotation.PasswordValidator;

public class PasswordStrengthValidator implements
		ConstraintValidator<PasswordValidator, String> {

	// ConstraintValidator<PasswordValidator, String> - Annotation name and field on which this custom validation/annotation can be applied

	List<String> weakPasswords;

	@Override
	public void initialize(PasswordValidator constraintAnnotation) {
		weakPasswords = Arrays.asList("12345", "qwerty");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && !weakPasswords.contains(value);
	}
}
