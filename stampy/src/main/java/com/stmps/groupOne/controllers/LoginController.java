package com.stmps.groupOne.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.models.User;
import com.stmps.groupOne.services.ProfileService;
import com.stmps.groupOne.services.UserService;
import com.stmps.groupOne.validators.UserValidator;

@Controller
public class LoginController {
	@Autowired
	UserService usrServ;
	@Autowired
	UserValidator usrValidator;
	@Autowired
	ProfileService profileServ;
	
	@GetMapping("/")
	public String root(HttpSession session) {
		if(session.getAttribute("id") != null) {
			return "redirect:/home";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/login")
	public String loginPage(@ModelAttribute("registerForm") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("registerForm") User userForm, Model model, @RequestParam String email, @RequestParam String password, HttpSession session) {
		if(usrServ.authenticateUser(email, password)) {
			User user = usrServ.getByEmail(email);
			session.setAttribute("email", email);
			session.setAttribute("id", user.getId());
			session.setAttribute("profile_id", user.getProfile().getId());
			System.out.println("profileid: "+user.getProfile().getId());
			return "redirect:/home";
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
			Profile newProfile = profileServ.add(new Profile(user.getName(),"","",user));
			session.setAttribute("email", user.getEmail());
			session.setAttribute("id", newUser.getId());
			session.setAttribute("profile_id", newProfile.getId());
			return "redirect:/home";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
