package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stmps.groupOne.models.Stamp;

public interface StampRepository extends CrudRepository<Stamp,String>{
	List<Stamp> findAll();
	@Query(value = "SELECT * FROM stamps WHERE post_id = :postId ORDER BY created_at LIMIT 1000", nativeQuery = true)
	List<Stamp> findStampsByPost(@Param("postId") String postId);
}
