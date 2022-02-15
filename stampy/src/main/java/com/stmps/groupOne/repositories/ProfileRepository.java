package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stmps.groupOne.models.Profile;

public interface ProfileRepository extends CrudRepository<Profile,String> {
	List<Profile> findAll();
	@Query(value = "SELECT * FROM profiles WHERE name LIKE %:search%", nativeQuery = true)
	List<Profile> searchprofilenames(@Param("search") String search);
	
	@Query(value = "SELECT * FROM profiles;")
	List<Profile> findFollowed(@Param("profileId") String profileId);
}
