package com.stmps.groupOne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.repositories.FileEntryRepository;
import com.stmps.groupOne.storage.StorageProperties;
import com.stmps.groupOne.storage.StorageService;
import com.stmps.groupOne.utilities.random.RandGenerator;

@Service
public class JointFileService {
	@Autowired
	StorageProperties storageProperties;
	@Autowired
	FileEntryRepository fileEntryRepo;
	@Autowired
	StorageService storageServ;
	
	public FileEntry add(MultipartFile uploadedFile, String usage) {
		/* 
		 * Id must be generated here to ensure it matches filename
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
				return fileEntryRepo.save(new FileEntry(newFileId, fileName, storageProperties.getLocation(), uploadedFile.getContentType(), usage));			
			}
		}
		return null;
	}
	
	public FileEntry addImage(MultipartFile uploadedFile, String usage) {
		if(uploadedFile.getContentType().startsWith("image")) {
			return this.add(uploadedFile, usage);
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
