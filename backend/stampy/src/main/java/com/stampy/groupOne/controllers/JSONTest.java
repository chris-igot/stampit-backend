package com.stampy.groupOne.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stampy.groupOne.models.FileEntry;
import com.stampy.groupOne.models.Post;
import com.stampy.groupOne.models.User;

@RestController
public class JSONTest {
	@GetMapping("/test")
	public User apiTest(HttpSession session) {
		User user = new User();
		user.setEmail("a@a.com");
		user.setId((long) 5);
		user.setName("username");
		user.setPassword("asdfasdf");
		List<Post> posts = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Post post = new Post();
			post.setId("post00"+ i);
			FileEntry image = new FileEntry();
			String id = "image00"+i;
			image.setId(id);
			image.setCategory("post");
			image.setFileName(id+".png");
			image.setPath("/uplaods");
			image.setType("image/png");
			post.setImage(image);
			posts.add(post);
		}
		user.setPosts(posts);
		return user;
	}
//	@GetMapping("/test2")
//	public String apiTest2(HttpSession session) {
//		session.setAttribute("test2","tst2 value");
//		return "redirect:/test";
//	}
}
