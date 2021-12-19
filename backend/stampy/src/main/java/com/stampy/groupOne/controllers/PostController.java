package com.stampy.groupOne.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stampy.groupOne.models.Post;
import com.stampy.groupOne.services.PostService;

@Controller
public class PostController {
	@Autowired
	PostService postServ;
	
	@GetMapping("/api/post")
	public ResponseEntity<Post> getAPIPost(@RequestParam("post") String postId) {
		Post post = postServ.getById(postId);
		if(post != null) {
			return ResponseEntity.ok().body(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/post/new")
	public String postAPIPost(@RequestParam("file") MultipartFile uploadedFile) {
		postServ.addImagePost(uploadedFile);
		return "redirect:/profile";
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
