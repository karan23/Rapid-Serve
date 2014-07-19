package com.serve.rapid.repository;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}