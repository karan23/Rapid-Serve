package com.serve.rapid.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.FieldAgent;

public interface FieldAgentRepository extends CrudRepository<FieldAgent, Long> {

	List<FieldAgent> findByIdAndContactNumber(long id, String contactNumber);

}