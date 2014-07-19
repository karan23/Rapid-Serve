package com.serve.rapid.repository;

import org.springframework.data.repository.CrudRepository;

import com.serve.rapid.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}