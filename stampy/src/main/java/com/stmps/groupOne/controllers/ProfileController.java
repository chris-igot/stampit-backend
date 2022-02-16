package com.stmps.groupOne.controllers;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.services.ProfileService;
import com.stmps.groupOne.services.RoleService;
import com.stmps.groupOne.services.UserService;

@Controller
public class ProfileController {
	@Autowired
	ProfileService profileServ;
	@Autowired
	UserService usrServ;
	@Autowired
	RoleService roleServ;

	@GetMapping("/api/profiles/home")
	public ResponseEntity<Profile> getAPIHome(HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile profile = profileServ.getById(profileId);

		if(profile != null) {
			return ResponseEntity.ok().body(profile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/api/profiles")
	public ResponseEntity<Profile> getAPIProfile(@RequestParam("id") String otherProfileId, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		Profile otherProfile = profileServ.getById(otherProfileId);
		
		if(otherProfile != null) {
			otherProfile.setCurrentlyFollowing(profileServ.checkFollowStatus(ownProfileId, otherProfileId));
			otherProfile.setFollowRequested(profileServ.checkFollowStatus(otherProfileId, ownProfileId));
			return ResponseEntity.ok().body(otherProfile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/profiles/home/edit")
	public ResponseEntity<Void> postAPIProfileEdit (@ModelAttribute("editProfileForm") Profile profileForm, HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile dbProfile = profileServ.getById(profileId);

		dbProfile.setBio(profileForm.getBio());
		dbProfile.setTitle(profileForm.getTitle());
		dbProfile.setIsPrivate(profileForm.getIsPrivate());
		profileServ.add(dbProfile);
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@PostMapping("/api/profiles/home/setimage")
	public ResponseEntity<Void> postProfileSetimage (@RequestParam("file") MultipartFile uploadedFile, HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));

		profileServ.addImage(uploadedFile,profile);

		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@GetMapping("/api/profiles/follows/followed")
	public ResponseEntity<List<Profile>> getAPIProfilesFollowsAmfollowing(HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		List<Profile> results = profileServ.getFollowed(ownProfileId);
		
		for (Iterator<Profile> iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(2);
			profile.setFollowRequested(profileServ.checkFollowStatus(profile.getId(), ownProfileId));
		}
		
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping("/api/profiles/follows/followers")
	public ResponseEntity<List<Profile>> getAPIProfilesFollowsFollowers(HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		List<Profile> results = profileServ.getFollowers(ownProfileId);
		
		for (Iterator<Profile> iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(profileServ.checkFollowStatus(ownProfileId, profile.getId()));
			profile.setFollowRequested(2);
		}
		
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping("/api/profiles/follows/requested")
	public ResponseEntity<List<Profile>> getAPIProfilesFollowsRequested(HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		List<Profile> results = profileServ.getFollowerRequests(ownProfileId);
		
		for (Iterator<Profile> iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(profileServ.checkFollowStatus(ownProfileId, profile.getId()));
			profile.setFollowRequested(1);
		}
		
		return ResponseEntity.ok().body(results);
	}

	@GetMapping("/api/profiles/follow")
	public ResponseEntity<Void> getAPIProfilesFollow(@RequestParam("id") String otherProfileId, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");

		profileServ.follow(ownProfileId, otherProfileId);

		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@GetMapping("/api/profiles/unfollow")
	public ResponseEntity<Void> getAPIProfilesUnfollow(@RequestParam("id") String otherProfileId, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");

		profileServ.unfollow(ownProfileId, otherProfileId);
		
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@GetMapping("/api/profiles/accept")
	public ResponseEntity<Void> getAPIProfilesAccept(@RequestParam("id") String otherProfileId, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		ResponseEntity<Void> response;

		if(profileServ.verifyFollowerRequest(true, ownProfileId, otherProfileId)) {
			response = new ResponseEntity<Void>( HttpStatus.OK );
		} else {
			response = new ResponseEntity<Void>( HttpStatus.NOT_FOUND );
		}

		return response;
	}
	
	@GetMapping("/api/profiles/reject")
	public ResponseEntity<Void> getAPIProfilesFollow(@RequestParam("id") String otherProfileId, @PathVariable("follow") String followState, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		ResponseEntity<Void> response;

		if(profileServ.verifyFollowerRequest(false, ownProfileId, otherProfileId)) {
			response = new ResponseEntity<Void>( HttpStatus.OK );
		} else {
			response = new ResponseEntity<Void>( HttpStatus.NOT_FOUND );
		}

		return response;
	}
	
	@PostMapping("/api/profiles/search")
	public ResponseEntity<List<Profile>> postAPISearch(@RequestParam("search") String searchString, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		List<Profile> results = profileServ.findName(searchString);
		
		for (Iterator<Profile> iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(profileServ.checkFollowStatus(ownProfileId, profile.getId()));
			profile.setFollowRequested(profileServ.checkFollowStatus(profile.getId(), ownProfileId));
		}

		return ResponseEntity.ok().body(results);
	}
}
