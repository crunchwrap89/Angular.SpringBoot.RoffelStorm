package com.teamroffel.userpost.roffelstorm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamroffel.userpost.roffelstorm.model.UserFeedPost;
import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.repository.UsePostRepository;
import com.teamroffel.userpost.roffelstorm.repository.UserFeedPostRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)


@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	UsePostRepository postRepository;
	@Autowired
	UserFeedPostRepository UFR;
	
	@GetMapping("/all")
	public List<UserPost> allAccess() {
	
		return (List<UserPost>) postRepository.findAll();
	}
	
	/*	Man kan skicka in parametrarna "text" och "userId" i json för att skapa en post
	 * 
	 * */	
	@PostMapping("/create")
	public UserPost createPost( @RequestBody UserPost userpost) {
        return postRepository.save(userpost);
	}
	
	@GetMapping("/userpostbyid/{id}")
	public ResponseEntity<UserPost> findPostById(@PathVariable(value = "id") Long userId) throws Exception {
		UserPost userpost = postRepository.findById(userId)
	               .orElseThrow(() -> new Exception("id " + userId + " not found"));
	        return ResponseEntity.ok().body(userpost);		
	}
	
	@PostMapping("/createuserfeedpost")
	public UserFeedPost createuserfeedpost( @RequestBody UserFeedPost userfeedpost) {
		return UFR.save(userfeedpost);
	}
	
	@GetMapping("/userfeedpostbyid/{id}")
	public ResponseEntity<UserFeedPost> findUserFeedPostById(@PathVariable(value = "id") Long userId) throws Exception {
		UserFeedPost userfeedpost = UFR.findById(userId)
	               .orElseThrow(() -> new Exception("id " + userId + " not found"));
	        return ResponseEntity.ok().body(userfeedpost);
		
	}
	
//	@GetMapping("/userfeedpostsbyname/{authorId}")
//	public ResponseEntity<UserFeedPost> findUserFeedPostById(@PathVariable(value = "authorId") int authorId) throws Exception {
//		UserFeedPost userfeedpost = UFR.findAllById(authorId)
//	               .orElseThrow(() -> new Exception("id " + userId + " not found"));
//	        return ResponseEntity.ok().body(userfeedpost);
//		
//	}

}
