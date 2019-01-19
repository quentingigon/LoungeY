package io.lounge.api.controllers;

import io.lounge.api.interfaces.FileApi;
import io.lounge.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileController implements FileApi {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/uploadFile")
	public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName;
		try {
			fileName = fileStorageService.storeFile(file);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/downloadFile/")
			.path(fileName)
			.toUriString();

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

}
