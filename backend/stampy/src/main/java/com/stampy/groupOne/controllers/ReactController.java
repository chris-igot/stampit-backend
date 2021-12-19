package com.stampy.groupOne.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.stampy.groupOne.services.UserService;

@Controller
public class ReactController {
	public static final String reactPage = "stampyReact.jsp"; 
	@Autowired
	UserService usrServ;
	@GetMapping("/profile")
	public String reactProfile() {
		return reactPage;
	}
	
	@GetMapping("/public")
	public String reactPublic() {
		return reactPage;
	}
	
	@GetMapping("/post")
	public String reactPost() {
		return reactPage;
	}
	
	@GetMapping("/post/new")
	public String reactPostNew() {
		return reactPage;
	}
}
