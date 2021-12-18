package com.stampy.groupOne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stampy.groupOne.models.FileEntry;
import com.stampy.groupOne.repositories.FileEntryRepository;
import com.stampy.groupOne.storage.StorageProperties;
import com.stampy.groupOne.storage.StorageService;
import com.stampy.groupOne.utilities.RandGenerator;

@Service
public class JointFileService {
	@Autowired
	StorageProperties storageProperties;
	@Autowired
	FileEntryRepository fileEntryRepo;
	@Autowired
	StorageService storageServ;
	
	public FileEntry add(MultipartFile uploadedFile) {
		/* 
		 * The for loop detects id collisions. 3 attempts total 
		 * */
		String newFileId = RandGenerator.urlSafe();
		Optional<FileEntry> existingFile = fileEntryRepo.findById(newFileId);
		for (int i = 0; i < 3; i++) {
			if(existingFile.isPresent()) {
				newFileId = RandGenerator.urlSafe();
				existingFile = fileEntryRepo.findById(newFileId);
			} else {
				String[] splitFileName = uploadedFile.getOriginalFilename().split("[.]");
				String extension = "";
				if(splitFileName.length > 0) {
					extension = splitFileName[splitFileName.length-1].toLowerCase();			
				}
				
				String fileName = newFileId+"."+extension;
				
				storageServ.store(uploadedFile, fileName);
				return fileEntryRepo.save(new FileEntry(newFileId, fileName, storageProperties.getLocation(), uploadedFile.getContentType()));			
			}
		}
		return null;
	}
	
	public FileEntry addImage(MultipartFile uploadedFile) {
		if(uploadedFile.getContentType().startsWith("image")) {
			return this.add(uploadedFile);
		}
		return null;
	}
	
	public FileEntry getEntryById(String id) {
		return fileEntryRepo.findById(id).get();
	}
	
	public Resource getImage(String fileName) {
		List<FileEntry> fileEntries = fileEntryRepo.findByFileNameAndTypeStartingWith(fileName, "image");
		if(fileEntries.size() > 0) {
			Resource file = storageServ.loadAsResource(fileEntries.get(0).getFileName());
			return file;
		} else {
			return null;
		}
	}
}
