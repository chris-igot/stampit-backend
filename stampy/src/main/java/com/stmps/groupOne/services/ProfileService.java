package com.stmps.groupOne.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.repositories.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepo;
	@Autowired
	JointFileService fileServ;
	
	public Profile add(Profile profile) {
		return profileRepo.save(profile);
	}
	
	public void addImage(MultipartFile uploadedFile, Profile profile) {
		FileEntry image = fileServ.addImage(uploadedFile, "profile");
		profile.setImage(image);
		profileRepo.save(profile);
	}
	
	public Profile getById(String id) {
		return profileRepo.findById(id).get();
	}
	
	public List<Profile> findName(String search) {
		if(search.equals("")) {
			return Collections.emptyList();
		} else {			
			return profileRepo.searchprofilenames(search);
		}
	}
}
