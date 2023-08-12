package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native") // db server will generate the sequence number whenever new record is inserted in db
	private int id;

	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	// By above json property we mean that we always want this field in the json request but we won't send this field back in Json response i.e. this field would be available at time of json deserialisation but not at the time of json serialisation.
	private String pwd;

	// Establishing link with Authority table
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER) // mappedBy=fieldName in Authority POJO
	// One Customer can be mapped to Many Authority
	private Set<Authority> authorities;

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
