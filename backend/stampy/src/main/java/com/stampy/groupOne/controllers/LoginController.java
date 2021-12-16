package com.stampy.groupOne.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stampy.groupOne.models.User;
import com.stampy.groupOne.services.UserService;
import com.stampy.groupOne.validators.UserValidator;

@Controller
public class LoginController {
	@Autowired
	UserService usrServ;
	@Autowired
	UserValidator usrValidator;
	
	@GetMapping("/")
	public String root(@ModelAttribute("registerForm") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("registerForm") User user, Model model, @RequestParam String email, @RequestParam String password, HttpSession session) {
		if(usrServ.authenticateUser(email, password)) {
			session.setAttribute("email", email);
			session.setAttribute("id", usrServ.getByEmail(email).getId());
			return "redirect:/";
		} else {
			model.addAttribute("loginError", "Invalid email/password");
			return "index.jsp";			
		}
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerForm") User user, BindingResult result, HttpSession session) {
		usrValidator.validate(user, result);

		if(result.hasErrors()) {
			return "index.jsp";
			
		} else {
			User newUser = usrServ.add(user);
			session.setAttribute("email", user.getEmail());
			session.setAttribute("id", newUser.getId());
			return "redirect:/";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
