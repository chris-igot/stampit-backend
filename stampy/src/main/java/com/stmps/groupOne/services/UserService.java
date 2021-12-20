package com.stmps.groupOne.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stmps.groupOne.models.User;
import com.stmps.groupOne.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public User add (User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}
	
	public List<User> getAll() {
		return this.userRepo.findAll();
	}
	
	public User getById(Long id) {
		Optional<User> optUser = this.userRepo.findById(id);
		
		if(optUser.isPresent()) {
			return optUser.get();
		} else {
			return null;
		}
	}
	
	public User getByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			System.out.println("user dne");
			return false;
		} else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				System.out.println("authenticated!");
				return true;
			} else {
				System.out.println("not authenticated!");
				return false;
			}
		}
	}
}
