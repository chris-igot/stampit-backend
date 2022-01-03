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
	
	@PostMapping("/click")
	public ResponseEntity<Void> getClickyWithIt(
			@RequestParam("x") Float x,@RequestParam("y") Float y,
			@RequestParam("boxDimX") Float boxDimX, @RequestParam("boxDimY") Float boxDimY,
			@RequestParam("postId") String postId,
			HttpSession session
			) {
		System.out.println("\n\nClick!");
		System.out.println(x);
		System.out.println(y);
		System.out.println("Click!boxsize");
		System.out.println(boxDimX);
		System.out.println(boxDimY);
		Float scaledX = (x / boxDimX) * 10000;
		Float scaledY = (y / boxDimY) * 10000;
		Integer relX = scaledX.intValue();
		Integer relY = scaledY.intValue();
		System.out.println("Click!relativepos");
		System.out.println(relX);
		System.out.println(relY);
		System.out.println(postId);
		
		return new ResponseEntity<Void>( HttpStatus.OK );

	}
	
	@GetMapping("/api/post/stamps")
	public ResponseEntity<List<Stamp>> getAPIPostStamps(@RequestParam("id") String postId){
		List<Stamp> stamps = stampServ.getPostStamps(postId);
		if(stamps.size() > 0) {
			return ResponseEntity.ok().body(stamps);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/post/stamp")
	public ResponseEntity<Void> postPostStampHere(
			@RequestParam("x") Float x,@RequestParam("y") Float y,
			@RequestParam("boxDimX") Float boxDimX, @RequestParam("boxDimY") Float boxDimY,
			@RequestParam("postId") String postId,
			HttpSession session
		) {
		return postAPIPostStampHere(x, y, boxDimX, boxDimY, postId, session);
	}

	@PostMapping("/api/post/stamp")
	public ResponseEntity<Void> postAPIPostStampHere(
			@RequestParam("x") Float x,@RequestParam("y") Float y,
			@RequestParam("boxDimX") Float boxDimX, @RequestParam("boxDimY") Float boxDimY,
			@RequestParam("postId") String postId,
			HttpSession session
			) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		FileEntry image = fileServ.getEntryById("win");
		Profile ownProfile = profileServ.getById(ownProfileId);
		Post post = postServ.getById(postId);
		Float scaledX = (x / boxDimX) * 10000;
		Float scaledY = (y / boxDimY) * 10000;
		
		stampServ.add(ownProfile, image, post, scaledX.intValue(), scaledY.intValue());
		
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
}
