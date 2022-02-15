package com.stmps.groupOne.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.models.Follow;
import com.stmps.groupOne.models.FollowCompositeKey;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.repositories.FollowRepository;
import com.stmps.groupOne.repositories.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	ProfileRepository profileRepo;
	@Autowired
	JointFileService fileServ;
	@Autowired
	FollowRepository followRepo;
	
	public Profile add(Profile profile) {
		return profileRepo.save(profile);
	}
	
	public void addImage(MultipartFile uploadedFile, Profile profile) {
		FileEntry image = fileServ.addImage(uploadedFile, "image");
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
	
	public List<Profile> getFollowers(String ownProfileId) {
		return profileRepo.findFollowers(ownProfileId);
	}
	
	public List<Profile> getFollowed(String ownProfileId) {
		return profileRepo.findFollowed(ownProfileId);
	}
	
	public List<Profile> getFollowerRequests(String ownProfileId) {
		return profileRepo.findFollowerRequests(ownProfileId);
	}
	
	public Integer checkFollowStatus(String ownProfileId, String theirProfileId) {
		FollowCompositeKey followKey = new FollowCompositeKey(ownProfileId, theirProfileId);
		Follow follow = followRepo.findById(followKey).get();
		Integer status = 0;
		
		if(follow == null) {
			status = 0;
		} else {
			if(follow.getFollowVerified()) {
				status = 2;
			} else {
				status = 1;
			}
		}

		return status;
	}
	
	public Boolean verifyFollowerRequest(Boolean grantFollow, String ownProfileId, String theirProfileId) {
		FollowCompositeKey followKey = new FollowCompositeKey(ownProfileId, theirProfileId);
		Optional<Follow> followOpt = followRepo.findById(followKey);
		Boolean success = false;
		
		if(followOpt.isPresent()) {
			Follow follow = followOpt.get();
			if(grantFollow) {
				follow.setFollowVerified(grantFollow);
				followRepo.save(follow);
			} else {
				followRepo.delete(follow);
			}
			success = true;
		}
		
		return success;
	}
	
	public void follow(String ownProfileId, String otherProfileId) {
		Profile ownProfile = this.getById(ownProfileId);
		Profile otherProfile = this.getById(otherProfileId);

		if (otherProfile.getIsPrivate()) {
			followRepo.save(new Follow(ownProfile.getId(), otherProfile.getId(), false));
		} else {
			followRepo.save(new Follow(ownProfile.getId(), otherProfile.getId(), true));
		}
	}
	
	public void unfollow(String ownProfileId, String otherProfileId) {
		Profile ownProfile = this.getById(ownProfileId);

		for (Profile profile : ownProfile.getAmFollowing()) {
			if(profile.getId().equals(otherProfileId)) {
				ownProfile.getAmFollowing().remove(profile);
				break;
			}
		}
		
		this.add(ownProfile);
	}
}
