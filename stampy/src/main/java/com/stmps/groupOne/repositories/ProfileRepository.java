package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.Profile;

public interface ProfileRepository extends CrudRepository<Profile,String> {
	List<Profile> findAll();
	@Query(value = "SELECT * FROM profiles WHERE name LIKE ?1", nativeQuery = true)
	List<Profile> findName(String search);
}
