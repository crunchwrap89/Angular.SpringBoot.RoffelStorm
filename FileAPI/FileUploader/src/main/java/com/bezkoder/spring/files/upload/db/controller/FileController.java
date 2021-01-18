package com.bezkoder.spring.files.upload.db.controller;

import com.bezkoder.spring.files.upload.db.message.ResponseFile;
import com.bezkoder.spring.files.upload.db.message.ResponseMessage;
import com.bezkoder.spring.files.upload.db.model.FileDB;
import com.bezkoder.spring.files.upload.db.repository.FileDBRepository;
import com.bezkoder.spring.files.upload.db.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
/*@RequestMapping("/api")*/
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
  @Value("${myApp.imgPath}")
  private String imgpath;
  @Autowired
  private FileStorageService storageService;
  @PersistenceContext
  private EntityManager em;
  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      byte[] bytes = file.getBytes();
      UUID uuid = UUID.randomUUID();
      Path path = Paths.get(imgpath + uuid + fileName);
      String stringPath = uuid + fileName;
      Files.write(path,bytes);
      message = stringPath;
      storageService.store(file, stringPath);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

/*  @GetMapping("/files")
  public Stream<FileDB> getAllFiles() {
    return (Stream<FileDB>) storageService.getAllFiles();
  }*/
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> new ResponseFile(
        dbFile.getName(),
        dbFile.getPath())).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }
  /*@Transactional
	@DeleteMapping("/removeimage/{id}")
	public ResponseEntity<?> deleteImage(@PathVariable String id) {
		String remId = id;
		FileDB imageRem = em.find(FileDB.class, remId);
		  em.remove(imageRem);
		  System.out.println("image Removed");
	  return ResponseEntity.ok(new MessageResponse("Image removed"));
	}*/

  /*@Transactional
  @DeleteMapping("/removeimage/{imageId}")
  public ResponseEntity<?> deleteImage(@PathVariable String imageId) {
    String pathStr = imageId;
    Path path = Paths.get(imgpath + pathStr);
    try {
      Files.delete(path);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("File not in \\BountyImages\\");
    }
    return ResponseEntity.ok(new MessageResponse("Image removed"));
  }*/
}
