package com.bezkoder.spring.files.upload.db.controller;

import com.bezkoder.spring.files.upload.db.message.ResponseFile;
import com.bezkoder.spring.files.upload.db.message.ResponseMessage;
import com.bezkoder.spring.files.upload.db.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("userId") int userId) {
    String message = "";
    try {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      byte[] bytes = file.getBytes();
      UUID uuid = UUID.randomUUID();
      Path path = Paths.get(imgpath + uuid + fileName);
      String stringPath = uuid + fileName;
      Files.write(path,bytes);
      message = stringPath;
      storageService.storePicture(file, stringPath, userId);
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
        dbFile.getPath(),
        dbFile.getUserId())).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{userId}")
  public ResponseEntity<List<ResponseFile>> getListFilesByUserId(@PathVariable(value = "userId") int userId) {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> new ResponseFile(
            dbFile.getName(),
            dbFile.getPath(),
            dbFile.getUserId())).collect(Collectors.toList());
    List<ResponseFile> filesByUserId = new ArrayList<>();
    for(ResponseFile file : files) {
      if(file.getUserId() == userId) {
        filesByUserId.add(file);
      }
    }
    return ResponseEntity.status(HttpStatus.OK).body(filesByUserId);
  }

  @PostMapping("/uploadprofilepic")
  public ResponseEntity<ResponseMessage> uploadProFilePic(@RequestParam("file") MultipartFile file,@RequestParam("userId") int userId) {
    String message = "";
    try {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      byte[] bytes = file.getBytes();
      UUID uuid = UUID.randomUUID();
      Path path = Paths.get(imgpath + uuid + fileName);
      String stringPath = uuid + fileName;
      Files.write(path,bytes);
      message = stringPath;
      storageService.storeProfilePic(file, stringPath, userId);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/profilepics/{userId}")
  public ResponseEntity<List<ResponseFile>> getProfilePicsByUserId(@PathVariable(value = "userId") int userId) {
    List<ResponseFile> profilepics = storageService.getAllProfilePics().map(dbFile -> new ResponseFile(
            dbFile.getName(),
            dbFile.getPath(),
            dbFile.getUserId())).collect(Collectors.toList());
    List<ResponseFile> profilepicsByUserId = new ArrayList<>();
    for(ResponseFile file : profilepics) {
      if(file.getUserId() == userId) {
        profilepicsByUserId.add(file);
      }
    }
    return ResponseEntity.status(HttpStatus.OK).body(profilepicsByUserId);
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
