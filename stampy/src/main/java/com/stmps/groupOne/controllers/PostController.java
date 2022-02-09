package com.stmps.groupOne.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.Post;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.services.PostService;
import com.stmps.groupOne.services.ProfileService;

@Controller
public class PostController {
	@Autowired
	PostService postServ;
	@Autowired
	ProfileService profileServ;
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> getAPIPostUser(@RequestParam("id") String profileId) {
		Profile profile = profileServ.getById(profileId);

		if(profile != null) {
			return ResponseEntity.ok().body(profile.getPosts());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/api/posts/self")
	public ResponseEntity<List<Post>> getAPIPostSelf(HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));

		if(profile != null) {
			return ResponseEntity.ok().body(profile.getPosts());
		} else {
			return ResponseEntity.ok().body(Collections.emptyList());
		}
	}
	
	@PostMapping("/api/posts/new")
	public ResponseEntity<Void> postAPIPost(
			@RequestParam("file") MultipartFile uploadedFile, 
			@RequestParam("description") String description, 
			HttpSession session
		) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));
		postServ.addImagePost(uploadedFile, description, profile);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/public")
	public ResponseEntity<List<Post>> getAPIPublic() {
		List<Post> posts = postServ.getAll();
		if(posts != null) {
			return ResponseEntity.ok().body(posts);
		} else {
			return ResponseEntity.ok().body(Collections.emptyList());
		}
	}
	
	@GetMapping("/api/posts/following")
	public ResponseEntity<List<Post>> getAPIFollowing(HttpSession session){
		String profileId = (String)session.getAttribute("profile_id");
		List<Post> posts = postServ.getAllFollowing(profileId);
		
		if(posts != null) {
			return ResponseEntity.ok().body(posts);
		} else {
			return ResponseEntity.ok().body(Collections.emptyList());
		}
	}

	@GetMapping("/api/posts/{postid}")
	public ResponseEntity<Post> getAPIPost(@PathVariable("postid") String postId) {
		Post post = postServ.getById(postId);
		if(post != null) {
			return ResponseEntity.ok().body(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
