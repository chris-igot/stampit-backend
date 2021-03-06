package com.stmps.groupOne.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stmps.groupOne.models.FileEntry;
import com.stmps.groupOne.models.Profile;
import com.stmps.groupOne.services.JointFileService;
import com.stmps.groupOne.storage.StorageFileNotFoundException;

@Controller
public class FileController {
	@Autowired
	JointFileService fileServ;

	@GetMapping("/image/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> servePostImage(@PathVariable String filename, HttpSession session) {
		String ownProfileId = (String)session.getAttribute("profile_id");
		FileEntry fileEntry = fileServ.getFileEntry(filename, "image");
		ResponseEntity<Resource> output;

		if(fileEntry != null) {
			Resource file = null;
			Profile postOwner = fileEntry.getPost().getProfile();

			if (
				postOwner.beingFollowedBy(ownProfileId) ||
				postOwner.getId().equals(ownProfileId) ||
				!postOwner.getIsPrivate()
			) {
				file = fileServ.getImage(filename);

				if(file == null) {
					output = ResponseEntity.notFound().build();
				} else {
					output = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + file.getFilename() + "\"").body(file);
				}
			} else {
				output = ResponseEntity.status(403).build();
			}
		} else {
			output = ResponseEntity.notFound().build();
		}
		
		return output;
	}
	
	@GetMapping("/stamp/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveStamp(@PathVariable String filename) {
		Resource file = fileServ.getImage(filename);
		if(file != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + file.getFilename() + "\"").body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/profilepic/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveProfilePic(@PathVariable String filename) {
		Resource file = fileServ.getImage(filename);
		if(file != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + file.getFilename() + "\"").body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/admin/image/new")
	public ResponseEntity<Void> postImageNew(
			@RequestParam("file") MultipartFile uploadedFile,
			@RequestParam("name") String name,
			@RequestParam("category") String category
		) {
		fileServ.addImage(uploadedFile, category, name);
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
	
	@PostMapping("/api/admin/stamps/multiplenew")
	public ResponseEntity<Void> postStampsMultipleNew(
			@RequestParam("files") MultipartFile[] uploadedFiles
			) {
		for (int i = 0; i < uploadedFiles.length; i++) {
			MultipartFile file = uploadedFiles[i];
			fileServ.addImage(file, "stamp", file.getOriginalFilename().split("[.]")[0]);
		}

		return new ResponseEntity<Void>( HttpStatus.OK );
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
