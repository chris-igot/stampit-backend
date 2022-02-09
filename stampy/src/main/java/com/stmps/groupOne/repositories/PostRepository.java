package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stmps.groupOne.models.Post;

public interface PostRepository extends CrudRepository<Post, String> {
	List<Post> findAll();
	List<Post> findAllByOrderByCreatedAtDesc();
	@Query(value = "SELECT posts.id as id, posts.image_id as image_id, posts.profile_id as profile_id, posts.created_at as created_at, posts.updated_at as updated_at FROM follows LEFT JOIN profiles ON profiles.id = follows.them_id LEFT JOIN posts ON posts.profile_id = profiles.id WHERE follows.you_id = :userId ORDER BY posts.created_at DESC;", nativeQuery = true)
	List<Post> findAllFollowed(@Param("userId") String userId);
}
