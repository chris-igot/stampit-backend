package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.Role;

public interface RoleRepository extends CrudRepository<Role, String> {
	List<Role> findAll();
}
