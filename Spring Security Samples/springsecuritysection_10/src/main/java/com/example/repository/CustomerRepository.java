package com.example.repository;

import com.example.entity.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	List<Customer> findByEmail(String email); // derived method name query (concept of spring jpa)
}
