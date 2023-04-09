package com.r2dbch2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */
@Data
@AllArgsConstructor
@Table(name = "contact_msg")
public class Contact {
	@Id
	private int contactId;
	private String name;
	private String mobileNumber;
}
