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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.services.ProfileService;

@Controller
public class ProfileController {
	@Autowired
	ProfileService profileServ;

	@GetMapping("/api/home")
	public ResponseEntity<Profile> getAPIHome(HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile profile = profileServ.getById(profileId);

		if(profile != null) {
			return ResponseEntity.ok().body(profile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/api/profile")
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
	
	@PostMapping("/api/home/edit")
	public ResponseEntity<Void> postAPIProfileEdit (@ModelAttribute("editProfileForm") Profile profileForm, HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile dbProfile = profileServ.getById(profileId);

		dbProfile.setBio(profileForm.getBio());
		dbProfile.setTitle(profileForm.getTitle());
		profileServ.add(dbProfile);
		System.out.println(profileForm.getBio());
		System.out.println(profileForm.getTitle());
		System.out.println(dbProfile.getBio());
		System.out.println(dbProfile.getTitle());
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@PostMapping("/api/home/setimage")
	public ResponseEntity<Void> postProfileSetimage (@RequestParam("file") MultipartFile uploadedFile, HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));

		profileServ.addImage(uploadedFile,profile);

		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@GetMapping("/api/profile/follows")
	public ResponseEntity<Set<Profile>> getAPIprofileList(Model model, HttpSession session) {
		Profile ownProfile = profileServ.getById((String)session.getAttribute("profile_id"));
		Set<Profile> results = ownProfile.getAmFollowing();
		
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			Profile profile = (Profile) iterator.next();
			profile.setCurrentlyFollowing(ownProfile.checkIfFollowing(profile));
		}
		
		return ResponseEntity.ok().body(results);
	}

	
	@GetMapping("/api/profile/amfollowing")
	public ResponseEntity<Boolean> getAPIprofileAmfollowing(@RequestParam("id") String otherProfileId, HttpSession session) {
		Boolean amFollowing = profileServ.getById((String)session.getAttribute("profile_id")).checkIfFollowing(otherProfileId);
		
		return ResponseEntity.ok().body(amFollowing);
	}
	
	@GetMapping("/api/profile/{follow}")
	public ResponseEntity<Void> getAPIProfileFollow(@RequestParam("id") String otherProfileId, @PathVariable("follow") String followState, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		Profile ownProfile = profileServ.getById(ownProfileId);

		if(followState.equals("unfollow")) {
			System.out.println("unfollowing");
			ownProfile.unfollow(otherProfileId);
			profileServ.add(ownProfile);
		} else if(followState.equals("follow")) {
			Profile otherProfile = profileServ.getById(otherProfileId);
			ownProfile.follow(otherProfile);

			profileServ.add(ownProfile);
		}

		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@PostMapping("/api/search")
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
