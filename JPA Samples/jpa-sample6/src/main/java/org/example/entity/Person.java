package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;
import org.example.annotation.FieldsValueMatch;
import org.example.annotation.PasswordValidator;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@FieldsValueMatch.List(
		{
				@FieldsValueMatch(
						field = "email",
						fieldMatch = "confirmEmail"
				)
		}
)
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int personId;
	private String name;
	private String email;
	@Transient
	private String confirmEmail;
	@PasswordValidator
	private String pwd;
}
