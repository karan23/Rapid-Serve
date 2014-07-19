package com.serve.rapid.repository;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint, Long> {

}