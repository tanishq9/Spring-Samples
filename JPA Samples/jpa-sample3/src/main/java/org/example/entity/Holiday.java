package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "holidays")
public class Holiday {

	@Id
	private String day;
	private String reason;
	@Enumerated(EnumType.STRING)
	private Type type;

	public enum Type {
		FESTIVAL, FEDERAL
	}
}
