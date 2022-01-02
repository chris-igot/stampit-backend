package com.stmps.groupOne.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/api/post")
	public ResponseEntity<Post> getAPIPost(@RequestParam("id") String postId) {
		Post post = postServ.getById(postId);
		if(post != null) {
			return ResponseEntity.ok().body(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/post/new")
	public ResponseEntity<Void> postAPIPost(@RequestParam("file") MultipartFile uploadedFile,HttpSession session) {
		Profile profile = profileServ.getById((String) session.getAttribute("profile_id"));
		postServ.addImagePost(uploadedFile,profile);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/api/public")
	public ResponseEntity<List<Post>> getAPIPublic() {
		List<Post> posts = postServ.getAll();
		if(posts.size() > 0) {
			return ResponseEntity.ok().body(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
