package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.Post;

public interface PostRepository extends CrudRepository<Post, String> {
	List<Post> findAll();
	List<Post> findAllByOrderByCreatedAtDesc();
}
