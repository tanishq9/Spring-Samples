package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

// If table name is different from class name then we need to mention same in @Table annotation.
// By default Spring Data would remove _ (underscore from table name)
// and would try to match with class name, same is applicable for fields/columns present in the table, case-sensitivity would be ignored.

@Data
@Entity
@Table(name = "contact_msg")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int contactId;
	private String name;
	private String mobileNumber;
	private String email;
	private String subject;
}
