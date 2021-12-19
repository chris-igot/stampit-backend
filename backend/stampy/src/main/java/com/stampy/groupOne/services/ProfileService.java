package com.stampy.groupOne.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stampy.groupOne.models.Profile;
import com.stampy.groupOne.repositories.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepo;
	
	public Profile add(Profile profile) {
		return profileRepo.save(profile);
	}
	
	public Profile getById(String id) {
		return profileRepo.findById(id).get();
	}
	
	public List<Profile> findName(String search) {
		return profileRepo.findByNameStartingWith(search);
	}
}
