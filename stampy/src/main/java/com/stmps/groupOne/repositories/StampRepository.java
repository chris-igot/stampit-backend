package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.Stamp;

public interface StampRepository extends CrudRepository<Stamp,String>{
	List<Stamp> findAll();
}
