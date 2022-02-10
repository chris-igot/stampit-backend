package com.stmps.groupOne.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.models.User;
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
		Profile otherProfile = profileServ.getById(otherProfileId);
		Profile ownProfile = profileServ.getById((String)session.getAttribute("profile_id"));
		
		if(otherProfile != null) {
			otherProfile.setCurrentlyFollowing(ownProfile.checkIfFollowing(otherProfile));
			return ResponseEntity.ok().body(otherProfile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/profiles/home/edit")
	public ResponseEntity<Void> postAPIProfileEdit (@ModelAttribute("editProfileForm") Profile profileForm, HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Long userId = (Long) session.getAttribute("id");
		Profile dbProfile = profileServ.getById(profileId);
		User user = usrServ.getById(userId);
		
		if(profileForm.getIsPrivate() == true) {
			user.addRole(roleServ.getRole("private"));
			usrServ.add(user);
		} else {
			user.removeRole("private");
			usrServ.add(user);
		}

		dbProfile.setBio(profileForm.getBio());
		dbProfile.setTitle(profileForm.getTitle());
		profileServ.add(dbProfile);
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@PostMapping("/api/profiles/home/setimage")
	public ResponseEntity<Void> postProfileSetimage (@RequestParam("file") MultipartFile uploadedFile, HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));

		profileServ.addImage(uploadedFile,profile);

		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@GetMapping("/api/profiles/ownfollows")
	public ResponseEntity<Set<Profile>> getAPIprofileList(Model model, HttpSession session) {
		Profile ownProfile = profileServ.getById((String)session.getAttribute("profile_id"));
		Set<Profile> results = ownProfile.getAmFollowing();
		
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(ownProfile.checkIfFollowing(profile));
		}
		
		return ResponseEntity.ok().body(results);
	}

	
	@GetMapping("/api/profiles/follows/amfollowing")
	public ResponseEntity<Boolean> getAPIprofileAmfollowing(@RequestParam("id") String otherProfileId, HttpSession session) {
		Boolean amFollowing = profileServ.getById((String)session.getAttribute("profile_id")).checkIfFollowing(otherProfileId);
		
		return ResponseEntity.ok().body(amFollowing);
	}
	
	@GetMapping("/api/profiles/{follow}")
	public ResponseEntity<Void> getAPIProfileFollow(@RequestParam("id") String otherProfileId, @PathVariable("follow") String followState, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");

		if(followState.equals("unfollow")) {
			profileServ.unfollow(ownProfileId, otherProfileId);
		} else if(followState.equals("follow")) {
			profileServ.follow(ownProfileId, otherProfileId);
		}

		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@PostMapping("/api/profiles/search")
	public ResponseEntity<List<Profile>> postAPISearch(@RequestParam("search") String searchString, HttpSession session) {
		List<Profile> results = profileServ.findName(searchString);
		Profile ownProfile = profileServ.getById((String)session.getAttribute("profile_id"));
		
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(ownProfile.checkIfFollowing(profile));
		}

		return ResponseEntity.ok().body(results);
	}
}
