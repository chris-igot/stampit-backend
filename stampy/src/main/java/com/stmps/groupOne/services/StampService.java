package com.stmps.groupOne.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.models.Post;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.models.Stamp;
import com.stmps.groupOne.repositories.FileEntryRepository;
import com.stmps.groupOne.repositories.StampRepository;

@Service
public class StampService {
	@Autowired
	StampRepository stampRepo;
	@Autowired
	FileEntryRepository fileRepo;
	
	public Stamp add(Profile profile, FileEntry image, Post post, Integer x, Integer y) {
		Stamp stamp = new Stamp(profile,image,post,x,y);
		return stampRepo.save(stamp);
	}
	
	public List<FileEntry> getAllStamps() {
		return fileRepo.findByCategoryEquals("stamp");
	}
	
	public List<Stamp> getPostStamps(String postId) {
		return stampRepo.findStampsByPost(postId);
	}
	
	public void removePostStamps(List<Stamp> stamps) {
		stampRepo.deleteAll(stamps);
	}
}
