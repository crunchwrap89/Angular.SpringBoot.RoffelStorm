package com.teamroffel.userpost.roffelstorm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.repository.UsePostRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)


@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	UsePostRepository postRepository;

	
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
	
//	@PostMapping("/createFeedPost")
//	public UserPost createFeedPost(@RequestBody UserPost userpost) {
//		
//		return userpost;
//		
//	}
}
