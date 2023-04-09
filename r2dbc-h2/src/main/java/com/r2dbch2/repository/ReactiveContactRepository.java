package com.r2dbch2.repository;

import com.r2dbch2.entity.Contact;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveContactRepository extends ReactiveCrudRepository<Contact, Integer> {
}
