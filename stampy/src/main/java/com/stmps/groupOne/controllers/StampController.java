package com.stmps.groupOne.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.models.Post;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.models.Stamp;
import com.stmps.groupOne.services.JointFileService;
import com.stmps.groupOne.services.PostService;
import com.stmps.groupOne.services.ProfileService;
import com.stmps.groupOne.services.StampService;

@Controller
public class StampController {
	@Autowired
	ProfileService profileServ;
	@Autowired
	PostService postServ;
	@Autowired
	StampService stampServ;
	@Autowired
	JointFileService fileServ;

	@GetMapping("/api/stamps/all")
	public ResponseEntity<List<FileEntry>> getAPIStampsAll() {
		return ResponseEntity.ok().body(stampServ.getAllStamps());
	}
	
	@GetMapping("/api/stamps")
	public ResponseEntity<List<Stamp>> getAPIPostStamps(@RequestParam("postid") String postId){
		List<Stamp> stamps = stampServ.getPostStamps(postId);

		return ResponseEntity.ok().body(stamps);
	}

	@PostMapping("/api/stamps/new")
	public ResponseEntity<Void> postAPIPostStampHere(
			@RequestParam("x") Float x,@RequestParam("y") Float y,
			@RequestParam("boxDimX") Float boxDimX, @RequestParam("boxDimY") Float boxDimY,
			@RequestParam("postId") String postId,
			@RequestParam("stampId") String stampId,
			HttpSession session
			) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		FileEntry image = fileServ.getEntryById(stampId);
		Profile ownProfile = profileServ.getById(ownProfileId);
		Post post = postServ.getById(postId);
		Float scaledX = ((x % boxDimX) / boxDimX) * 10000;
		Float scaledY = ((y % boxDimY) / boxDimY) * 10000;
		
		stampServ.add(ownProfile, image, post, scaledX.intValue(), scaledY.intValue());
		
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
}
