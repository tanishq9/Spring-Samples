package org.example.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.example.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements
		ConstraintValidator<FieldsValueMatch, Object> {

	private String field;
	private String fieldMatch;

	@Override
	public void initialize(FieldsValueMatch constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

		System.out.println("Object: " + value);
		System.out.println("Object: " + new BeanWrapperImpl(value));
		System.out.println("Field 1: " + field);
		System.out.println("Field 2: " + fieldMatch);
		System.out.println("Value 1: " + fieldValue);
		System.out.println("Value 2: " + fieldMatchValue);

		if (fieldValue != null) {
			return fieldValue.equals(fieldMatchValue);
		} else {
			return fieldMatchValue == null;
		}
	}
}