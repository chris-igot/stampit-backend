package com.stampy.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stampy.groupOne.models.File;

public interface FileRepository extends CrudRepository<File, Long> {
	List<File> findAll();
}
