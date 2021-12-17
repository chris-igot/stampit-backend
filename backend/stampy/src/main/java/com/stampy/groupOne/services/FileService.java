package com.stampy.groupOne.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stampy.groupOne.models.File;
import com.stampy.groupOne.repositories.FileRepository;
import com.stampy.groupOne.storage.StorageProperties;
import com.stampy.groupOne.storage.StorageService;
import com.stampy.groupOne.utilities.RandGenerator;

@Service
public class FileService {
	@Autowired
	StorageProperties storageProperties;
	@Autowired
	FileRepository fileRepo;
	@Autowired
	StorageService storageServ;
	
	public File add(MultipartFile uploadedFile) {
		String fileName = RandGenerator.urlSafe();
		String[] splitFileName = uploadedFile.getOriginalFilename().split("[.]");
		String extension = "";
		if(splitFileName.length > 0) {
			extension = splitFileName[splitFileName.length-1];			
		}

		File file = new File(fileName,fileName+"."+extension,storageProperties.getLocation(),uploadedFile.getContentType());
		storageServ.store(uploadedFile, fileName+"."+extension);
		return fileRepo.save(file);
	}
	
	public File getById(Long id) {
		return fileRepo.findById(id).get();
	}
}
