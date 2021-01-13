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
	
	@PersistenceContext
	private EntityManager em;
	
	
	@GetMapping("/all")
	public List<UserPost> getAllUserposts() {
		Query q = em.createQuery("select userpost from UserPost userpost ORDER BY date DESC");
		List<UserPost> userposts = q.getResultList();
		return userposts;
	}	
	
//	Hämtar mottagarnamn beroende på vad man skickar in
	@GetMapping("/userfeedpostbyrecievername/{recievername}")
	public List<UserFeedPost> findByRecievername(@PathVariable String recievername) {
		Query q = em.createQuery("select userfeedpost from UserFeedPost userfeedpost where userfeedpost.recieverName = :recievername ORDER BY userfeedpost.date DESC");
		q.setParameter("recievername", recievername);
		List<UserFeedPost> userfeedposts = q.getResultList();
		return userfeedposts;
		}
	
	@GetMapping("/userfeedpostbyrecieverid/{recieverid}")
	public List<UserFeedPost> findByRecieverid(@PathVariable int recieverid) {
		Query q = em.createQuery("select userfeedpost from UserFeedPost userfeedpost where userfeedpost.recieverId = :recieverid ORDER BY userfeedpost.date DESC");
		q.setParameter("recieverid", recieverid);
		List<UserFeedPost> userfeedposts = q.getResultList();
		return userfeedposts;
		}
	
	@GetMapping("/userfeedpostbyauthorid/{authorid}")
	public List<UserFeedPost> findByAuthoid(@PathVariable int authorid) {
		Query q = em.createQuery("select userfeedpost from UserFeedPost userfeedpost where userfeedpost.authorId = :authorid ORDER BY userfeedpost.date DESC");
		q.setParameter("authorid", authorid);
		List<UserFeedPost> userfeedposts = q.getResultList();
		return userfeedposts;
		}
	
	
//	Hämtar författarnamn beroende på vad man skickar in
	@GetMapping("/userfeedpostbyauthorname/{authorname}")
	public List<UserFeedPost> findByAuthorname(@PathVariable String authorname) {
		Query q = em.createQuery("select userfeedpost from UserFeedPost userfeedpost where userfeedpost.authorName = :authorname ORDER BY userfeedpost.date DESC");
		q.setParameter("authorname", authorname);
		List<UserFeedPost> userfeedposts = q.getResultList();
		return userfeedposts;
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
	
	@DeleteMapping("/deleteuserfeedpostbyid/{id}")
	public void deleteUserFeedPostById(@PathVariable(value = "id") Long userId) throws Exception {
		UFR.deleteById(userId);				
	}
	
	@DeleteMapping("/deleteuserpostbyid/{id}")
	public void deleteUserPostById(@PathVariable(value = "id") Long userId) throws Exception {
		postRepository.deleteById(userId);				
	}
	
	@PutMapping("/updateuserfeedupvotes/{id}")
	public Optional<UserFeedPost> updateUserFeedUpvotes(@RequestBody UserFeedPost newUserFeedPost, @PathVariable Long id) {
		return UFR.findById(id)
			    .map(userfeed -> {
			      userfeed.setUpvotes(newUserFeedPost.getUpvotes());
			      return UFR.save(userfeed);
			    });	
	}
	
	@PutMapping("/updateuserpostupvotes/{id}")
	public Optional<UserPost> updateUserPostUpvotes(@RequestBody UserPost newUserPost, @PathVariable Long id) {
		return postRepository.findById(id)
			    .map(userpost -> {
			      userpost.setUpvotes(newUserPost.getUpvotes());
			      return postRepository.save(userpost);
			    });	
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

}
