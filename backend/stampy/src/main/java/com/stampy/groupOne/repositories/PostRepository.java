package com.stampy.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stampy.groupOne.models.Post;

public interface PostRepository extends CrudRepository<Post, String> {
	List<Post> findAll();
	List<Post> findAllByOrderByCreatedAtDesc();
}
