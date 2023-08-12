package org.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Person extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int personId;
	private String name;
	private String email;
	@Transient // Do not consider this field for any DB related operation since that is not a column in DB table.
	private String confirmEmail;
	private String mobileNumber;
	@PasswordValidator
	private String pwd;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
	private Role role;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
	private Address address;
}
