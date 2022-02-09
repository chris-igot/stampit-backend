package com.stmps.groupOne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stmps.groupOne.models.Role;
import com.stmps.groupOne.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepo;
	
	public void addRole(String role) {
		Role newRole = new Role(role);
		roleRepo.save(newRole);
	}
	
	public List<Role> getAll() {
		return roleRepo.findAll();
	}
	
	public Role getRole(String roll) {
		Optional<Role> optRole = roleRepo.findById(roll);
		
		if(optRole.isPresent()) {
			return optRole.get();
		} else {
			return new Role(roll);
		}
	}
	
	public void removeRole(String rollId) {
		roleRepo.deleteById(rollId);
	}
}
