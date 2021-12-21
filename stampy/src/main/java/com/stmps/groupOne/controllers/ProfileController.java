package com.stmps.groupOne.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	@GetMapping("/home")
	public String getHome(Model model, HttpSession session) {
		model.addAttribute("profile", profileServ.getById((String)session.getAttribute("profile_id")));
		return "profile.jsp";
	}
	
	@GetMapping("/profile")
	public String getProfile(@RequestParam("id") String otherProfileId, Model model, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		Profile otherProfile = profileServ.getById(otherProfileId);
		
		model.addAttribute("profile", otherProfile);
		model.addAttribute("followed", otherProfile.checkIfBeingFollowed(ownProfileId));
		return "profile.jsp";
	}
	
	@GetMapping("/profile/{follow}")
	public String profileFollow(@RequestParam("id") String otherProfileId, @PathVariable("follow") String followState, HttpSession session) {
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
		return "redirect:/profile?id="+otherProfileId;
	}
	
	@GetMapping("/profile/follows")
	public String profileList(Model model, HttpSession session) {
		Set<Profile> results = profileServ.getById((String)session.getAttribute("profile_id")).getAmFollowing();
		System.out.println(results.size());
		model.addAttribute("following", results);
		return "follows.jsp";
	}
	
	@GetMapping("/profile/follows/{follow}")
	public String profileListFollow(@RequestParam("id") String otherProfileId, @PathVariable("follow") String followState, HttpSession session) {
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
		return "redirect:/profile/follows";
	}
	
	@GetMapping("/search")
	public String search() {
		return "search.jsp";
	}
	
	@PostMapping("/search")
	public String searchResults(@RequestParam("search") String searchString, Model model) {
		List<Profile> results = profileServ.findName(searchString);
		System.out.println(results.size());
		model.addAttribute("results", results);
		return "search.jsp";
	}
	
	@GetMapping("/home/edit")
	public String getProfileEdit(@ModelAttribute("profileEditForm") Profile profileForm, HttpSession session) {
		String profileId = (String) session.getAttribute("profile_id");
		Profile dbProfile = profileServ.getById(profileId);
		profileForm.setBio(dbProfile.getBio());
		profileForm.setTitle(dbProfile.getTitle());
		return "profileEdit.jsp";
	}
	
	@PostMapping("/home/edit")
	public String postProfileEdit (@ModelAttribute("profileEditForm") Profile profileForm, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "profileEdit.jsp";
		} else {
			String profileId = (String) session.getAttribute("profile_id");
			Profile dbProfile = profileServ.getById(profileId);
			dbProfile.setBio(profileForm.getBio());
			dbProfile.setTitle(profileForm.getTitle());
			profileServ.add(dbProfile);
			return "redirect:/home";
		}
	}
	
	@PostMapping("/home/setimage")
	public String postProfilePic(@RequestParam("file") MultipartFile uploadedFile,HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));
		profileServ.addImage(uploadedFile,profile);
		return "redirect:/home/edit";
	}
	
	/*
	 * Unused API Stuff
	 * */
	
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
