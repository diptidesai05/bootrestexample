package com.api.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.helper.FileUploadHelper;

@RestController
public class FileController {
	
	@Autowired
	FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		if(file.isEmpty()) {	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Resquest has no file");
		}
		if(!file.getContentType().equals("image/jpeg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpeg file is allowed");
		}
		
		try {
			boolean f = fileUploadHelper.uploadFile(file);
			if(f) {
				//return ResponseEntity.ok("File uploaded");
				return ResponseEntity.ok(ServletUriComponentsBuilder
						                 .fromCurrentContextPath()
						                 .path("/image/")
						                 .path(file.getOriginalFilename())
						                 .toUriString());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something wrong");
	}

}
