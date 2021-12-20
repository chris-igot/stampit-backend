package com.stampy.groupOne.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {
	/* REACT ROUTES	
	 * All these routes should correspond to react-router-dom routes
	 * Any route in react should be added here to prevent 404s from the server
	 * This is here for server redirects
	 * */
	public static final String reactPage = "stampyReact.jsp"; 
	
	@GetMapping("/home")
	public String reactHome() {
		return reactPage;
	}
	
	@GetMapping("/home/edit")
	public String reactHomeEdit() {
		return reactPage;
	}

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
