package com.serve.rapid.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByContactNumberAndContactNumber(String contactName, String contactNumber);
}