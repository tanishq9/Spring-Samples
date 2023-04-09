package org.example.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.example.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByEmailAndName(String email, String name);

	// @Query("SELECT c FROM Contact c WHERE c.subject = :subject")
	@Query(value = "SELECT * FROM contact_msg c WHERE c.subject = :subject", nativeQuery = true)
	Page<Contact> findBySubject(String subject);

	@Transactional // Ensures atomicity
	@Modifying // Required for update queries
	@Query("UPDATE Contact c SET c.subject = :status WHERE c.contactId = :id")
	int updateSubjectById(int id, String subject);
}
