package com.stampy.groupOne.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stampy.groupOne.models.Profile;
import com.stampy.groupOne.services.ProfileService;

@RestController
public class ProfileController {
	@Autowired
	ProfileService profileServ;
	@GetMapping("/api/profile")
	public ResponseEntity<Profile> getAPIProfile(HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile profile = profileServ.getById(profileId);
		if(profile != null) {
			return ResponseEntity.ok().body(profile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
