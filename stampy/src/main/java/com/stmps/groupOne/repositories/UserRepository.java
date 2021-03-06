package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.User;

public interface UserRepository extends CrudRepository<User, String> {
	List<User> findAll();
	User findByEmail(String email);
}

