package com.serve.rapid.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.FieldAgentLocation;

public interface FieldAgentLocationRepository extends CrudRepository<FieldAgentLocation, Long> {

	List<FieldAgentLocation> findAllById(long parseLong);

}