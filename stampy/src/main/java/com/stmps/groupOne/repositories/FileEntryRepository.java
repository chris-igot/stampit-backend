package com.stmps.groupOne.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stmps.groupOne.models.FileEntry;

public interface FileEntryRepository extends CrudRepository<FileEntry, String> {
	List<FileEntry> findAll();
	List<FileEntry> findByFileNameAndTypeStartingWith(String fileName, String type);
	List<FileEntry> findByCategoryEquals(String category);
}
