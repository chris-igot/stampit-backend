package com.stmps.groupOne.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
	public ResponseEntity<Profile> getAPIProfile(@RequestParam("id") String profileId, HttpSession session) {
		Profile profile = profileServ.getById(profileId);
		if(profile != null) {
			return ResponseEntity.ok().body(profile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/home/edit")
	public String postAPIProfileEdit (@RequestBody Profile profileForm, HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile dbProfile = profileServ.getById(profileId);
		dbProfile.setBio(profileForm.getBio());
		dbProfile.setTitle(profileForm.getTitle());
		profileServ.add(dbProfile);
		return "redirect:/home";
	}
}
