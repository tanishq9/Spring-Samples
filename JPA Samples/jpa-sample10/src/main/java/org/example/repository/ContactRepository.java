package org.example.repository;

import java.util.List;
import org.example.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByEmailAndName(String email, String name);
}
