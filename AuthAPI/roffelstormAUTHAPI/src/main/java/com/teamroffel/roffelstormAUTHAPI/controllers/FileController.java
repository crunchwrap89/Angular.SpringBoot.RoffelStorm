package com.teamroffel.roffelstormAUTHAPI.controllers;

import com.teamroffel.roffelstormAUTHAPI.Message.ResponseMessage;
import com.teamroffel.roffelstormAUTHAPI.models.User;
import com.teamroffel.roffelstormAUTHAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class FileController {
    @Autowired
    UserRepository userRepository;

    User user;

    @Value("${myApp.imgPath}")
    private String imgpath;

    @PutMapping("/uploadrofilepic")
    public ResponseEntity<ResponseMessage> uploadProfilePic(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        String message = "";
        /*try {*/
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            UUID uuid = UUID.randomUUID();
            Path path = Paths.get(imgpath + uuid + fileName);
            String stringPath = uuid + fileName;
            /*user = userRepository.findById(userId)
                    .map(user -> {
                        user.setProfilePicPath(stringPath);
                        return userRepository.save(user);
                    });*/
            user = userRepository.findById(userId).get();
            user.setProfilePicPath(stringPath);
            userRepository.save(user);
            Files.write(path,bytes);
            message = stringPath;

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        /*} catch (Exception e) {
            message = "Could not upload the Picture: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }*/
    }
}
