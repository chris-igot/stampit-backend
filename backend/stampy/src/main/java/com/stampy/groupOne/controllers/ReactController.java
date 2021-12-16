package com.stampy.groupOne.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.stampy.groupOne.services.UserService;

@Controller
public class ReactController {
	@Autowired
	UserService usrServ;
	@GetMapping("/profile")
	public String r() {
		return "stampyReact.jsp";
	}
}
