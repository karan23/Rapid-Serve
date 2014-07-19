package com.serve.rapid.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.Complaint;
import com.serve.rapid.domain.Customer;
import com.serve.rapid.domain.FieldAgent;

public interface ComplaintRepository extends CrudRepository<Complaint, Long> {

	List<Complaint> findByCustomer(Customer cust);

	List<Complaint> findByAgent(FieldAgent cust);

}