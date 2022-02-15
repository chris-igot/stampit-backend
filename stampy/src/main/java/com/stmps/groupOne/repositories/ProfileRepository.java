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

	@Query(value = "SELECT profiles.id as id, profiles.name as name, profiles.image_id as image_id, profiles.title as title, profiles.bio as bio, profiles.is_private as is_private, profiles.user_id as user_id FROM profiles INNER JOIN follows ON follows.you_id = profiles.id WHERE follows.them_id = :profileId AND follows.follow_verified IS TRUE;", nativeQuery = true)
	List<Profile> findFollowers(@Param("profileId") String profileId);

	@Query(value = "SELECT profiles.id as id, profiles.name as name, profiles.image_id as image_id, profiles.title as title, profiles.bio as bio, profiles.is_private as is_private, profiles.user_id as user_id FROM profiles INNER JOIN follows ON follows.you_id = profiles.id WHERE follows.them_id = :profileId AND follows.follow_verified IS NOT TRUE;", nativeQuery = true)
	List<Profile> findFollowerRequests(@Param("profileId") String profileId);

	@Query(value = "SELECT profiles.id as id, profiles.name as name, profiles.image_id as image_id, profiles.title as title, profiles.bio as bio, profiles.is_private as is_private, profiles.user_id as user_id FROM profiles INNER JOIN follows ON follows.them_id = profiles.id WHERE follows.you_id = :profileId AND follows.follow_verified IS TRUE;", nativeQuery = true)
	List<Profile> findFollowed(@Param("profileId") String profileId);
}
