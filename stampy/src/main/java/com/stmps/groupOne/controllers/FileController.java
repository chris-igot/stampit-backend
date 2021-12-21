package com.stmps.groupOne.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stmps.groupOne.services.JointFileService;
import com.stmps.groupOne.storage.StorageFileNotFoundException;

@RestController
public class FileController {
	@Autowired
	JointFileService fileServ;
//	
//	@GetMapping("/upload")
//	public String listUploadedFiles() {
//		return "stampyReact.jsp";
//	}

	@GetMapping("/img/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		System.out.println("UPLOAD - FILES");
		
		Resource file = fileServ.getImage(filename);
		if(file != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + file.getFilename() + "\"").body(file);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	@PostMapping("/upload")
//	public String handleFileUpload(@RequestParam("file") MultipartFile uploadedFile,
//			RedirectAttributes redirectAttributes) {
//		System.out.println("UPLOAD - POST");
//
//		fileServ.addImage(uploadedFile,"post");
//		
//		redirectAttributes.addFlashAttribute("message",
//				"You successfully uploaded " + uploadedFile.getOriginalFilename() + "!");
//
//		return "redirect:/profile";
//	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}