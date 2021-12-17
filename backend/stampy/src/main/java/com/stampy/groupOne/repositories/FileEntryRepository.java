package com.stampy.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stampy.groupOne.models.FileEntry;

public interface FileEntryRepository extends CrudRepository<FileEntry, String> {
	List<FileEntry> findAll();
	List<FileEntry> findByFileNameAndTypeStartingWith(String fileName, String type);
}
