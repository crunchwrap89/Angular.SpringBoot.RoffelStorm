package com.teamroffel.userpost.roffelstorm.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamroffel.userpost.roffelstorm.model.UserProfilePost;
import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.repository.UserPostRepository;
import com.teamroffel.userpost.roffelstorm.repository.UserProfilePostRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)


@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	UserPostRepository postRepository;
	@Autowired
	UserProfilePostRepository profilePostRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@GetMapping("/all")
	public List<UserPost> getAllUserposts() {
		List<UserPost> userposts = postRepository.findAllByOrderByDateDesc();
		return userposts;
	}
	
	@GetMapping("/findpostsbyusername/{name}")
	public List<UserPost> findUserPostsById(@PathVariable String name) {
		return postRepository.findByusername(name);
	}
	
	@PutMapping("/updateuserpostupvotes/{id}")
	public Optional<UserPost> updateUserPostUpvotes(@RequestBody UserPost newUserPost, @PathVariable Long id) {
		return postRepository.findById(id)
			    .map(userpost -> {
			      userpost.setUpvotes(newUserPost.getUpvotes());
			      return postRepository.save(userpost);
			    });	
	}
	
	@GetMapping("/userpostbyid/{id}")
	public ResponseEntity<UserPost> findPostById(@PathVariable(value = "id") Long userId) throws Exception {
		UserPost userpost = postRepository.findById(userId)
	               .orElseThrow(() -> new Exception("id " + userId + " not found"));
	        return ResponseEntity.ok().body(userpost);		
	}
	
	@PostMapping("/create")
	public UserPost createPost( @RequestBody UserPost userpost) {
        return postRepository.save(userpost);
	}
	
	@DeleteMapping("/deleteuserpostbyid/{id}")
	public void deleteUserPostById(@PathVariable(value = "id") Long userId) throws Exception {
		postRepository.deleteById(userId);				
	}
	
//	 Updaterar oavsett om du fyler i det eller inte. Om du inte fyller  ett fällt kommer det ändra till null oavsett omd et var något där innan.
	@PutMapping("/updateuserpost/{id}")
	public Optional<UserPost> updateUserFeedPost(@RequestBody UserPost newUserPost, @PathVariable Long id) {
		return postRepository.findById(id)
			    .map(userpost -> {
			      userpost.setText(newUserPost.getText());
			      userpost.setUpvotes(newUserPost.getUpvotes());
			      userpost.setUserId(newUserPost.getUserId());
			      userpost.setUsername(newUserPost.getUsername());
			      return postRepository.save(userpost);
			    });
	}
	
	
//	================================
	
	
	
	
//	Hämtar mottagarnamn beroende på vad man skickar in
	@GetMapping("/userprofilepostbyrecievername/{recievername}")
	public List<UserProfilePost> findByRecievername(@PathVariable String recievername) {
		List<UserProfilePost> userprofileposts = profilePostRepository.findByRecieverNameOrderByDateDesc(recievername);
		return userprofileposts;
		}
	
	@GetMapping("/userprofilepostbyrecieverid/{recieverid}")
	public List<UserProfilePost> findByRecieverid(@PathVariable int recieverid) {
		List<UserProfilePost> userprofileposts = profilePostRepository.findByRecieverIdOrderByDateDesc(recieverid);
		return userprofileposts;
		}
	
	@GetMapping("/userprofilepostbyauthorid/{authorid}")
	public List<UserProfilePost> findByAuthorid(@PathVariable int authorid) {
		List<UserProfilePost> userprofileposts = profilePostRepository.findByAuthorIdOrderByDateDesc(authorid);
		return userprofileposts;
		}
	
	@GetMapping("/userprofilepostbyauthorname/{authorname}")
	public List<UserProfilePost> findByAuthorname(@PathVariable String authorname) {		
		List<UserProfilePost> userprofileposts = profilePostRepository.findByAuthorNameOrderByDateDesc(authorname);
		return userprofileposts;
		}
	
	@PostMapping("/createuserprofilepost")
	public UserProfilePost createuserprofilepost( @RequestBody UserProfilePost userprofilepost) {
		return profilePostRepository.save(userprofilepost);		
	}
	
	@GetMapping("/userprofilepostbyid/{id}")
	public ResponseEntity<UserProfilePost> findUserprofilePostById(@PathVariable(value = "id") Long userId) throws Exception {
		UserProfilePost userprofilepost = profilePostRepository.findById(userId)
	               .orElseThrow(() -> new Exception("id " + userId + " not found"));
	        return ResponseEntity.ok().body(userprofilepost);	
	}
	
	@DeleteMapping("/deleteuserprofilepostbyid/{id}")
	public void deleteUserProfilePostById(@PathVariable(value = "id") Long userId) throws Exception {
		profilePostRepository.deleteById(userId);				
	}
	
	@PutMapping("/updateuserprofileupvotes/{id}")
	public Optional<UserProfilePost> updateUserProfileUpvotes(@RequestBody UserProfilePost newUserProfilePost, @PathVariable Long id) {
		return profilePostRepository.findById(id)
			    .map(userfeed -> {
			      userfeed.setUpvotes(newUserProfilePost.getUpvotes());
			      return profilePostRepository.save(userfeed);
			    });	
	}
	

}
