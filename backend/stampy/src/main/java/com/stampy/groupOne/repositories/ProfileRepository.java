package com.stampy.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stampy.groupOne.models.Profile;

public interface ProfileRepository extends CrudRepository<Profile,String> {
	List<Profile> findAll();
	List<Profile> findByNameStartingWith(String search);
}
