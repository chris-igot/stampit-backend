package com.stampy.groupOne.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stampy.groupOne.models.Post;
import com.stampy.groupOne.services.PostService;

@Controller
public class PostController {
	@Autowired
	PostService postServ;
	
	@GetMapping("/api/post")
	public Post getAPIPost(@RequestParam("post") String postId) {
		return postServ.getById(postId);
	}
	
	@GetMapping("/api/post/new")
	public String getAPINewPost(@RequestParam("file") MultipartFile uploadedFile) {
		return "redirect:/profile";
	}
}
