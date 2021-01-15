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
import com.teamroffel.userpost.roffelstorm.repository.UsePostRepository;
import com.teamroffel.userpost.roffelstorm.repository.UserProfilePostRepository;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)


@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	UsePostRepository postRepository;
	@Autowired
	UserProfilePostRepository profilePostRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@GetMapping("/all")
	public List<UserPost> getAllUserposts() {
		Query q = em.createQuery("select userpost from UserPost userpost ORDER BY date DESC");
		List<UserPost> userposts = q.getResultList();
		return userposts;
	}	
	
//	Hämtar mottagarnamn beroende på vad man skickar in
	@GetMapping("/userprofilepostbyrecievername/{recievername}")
	public List<UserProfilePost> findByRecievername(@PathVariable String recievername) {
		Query q = em.createQuery("select userprofilepost from UserProfilePost userprofilepost where userprofilepost.recieverName = :recievername ORDER BY userprofilepost.date DESC");
		q.setParameter("recievername", recievername);
		List<UserProfilePost> userprofileposts = q.getResultList();
		return userprofileposts;
		}
	
	@GetMapping("/userprofilepostbyrecieverid/{recieverid}")
	public List<UserProfilePost> findByRecieverid(@PathVariable int recieverid) {
		Query q = em.createQuery("select userprofilepost from UserProfilePost userprofilepost where userprofilepost.recieverId = :recieverid ORDER BY userprofilepost.date DESC");
		q.setParameter("recieverid", recieverid);
		List<UserProfilePost> userprofileposts = q.getResultList();
		return userprofileposts;
		}
	
	@GetMapping("/userfeedpostbyauthorid/{authorid}")
	public List<UserProfilePost> findByAuthorid(@PathVariable int authorid) {
		Query q = em.createQuery("select userprofilepost from UserProfilePost userprofilepost where userprofilepost.authorId = :authorid ORDER BY userprofilepost.date DESC");
		q.setParameter("authorid", authorid);
		List<UserProfilePost> userprofileposts = q.getResultList();
		return userprofileposts;
		}
	
	
//	Hämtar författarnamn beroende på vad man skickar in
	@GetMapping("/userprofilepostbyauthorname/{authorname}")
	public List<UserProfilePost> findByAuthorname(@PathVariable String authorname) {
		Query q = em.createQuery("select userprofilepost from UserProfilePost userprofilepost where userprofilepost.authorName = :authorname ORDER BY userprofilepost.date DESC");
		q.setParameter("authorname", authorname);
		List<UserProfilePost> userprofileposts = q.getResultList();
		return userprofileposts;
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
	
	@PostMapping("/createuserprofilepost")
	public UserProfilePost createuserfeedpost( @RequestBody UserProfilePost userprofilepost) {
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
	
	@DeleteMapping("/deleteuserpostbyid/{id}")
	public void deleteUserPostById(@PathVariable(value = "id") Long userId) throws Exception {
		postRepository.deleteById(userId);				
	}
	
	@PutMapping("/updateuserprofileupvotes/{id}")
	public Optional<UserProfilePost> updateUserProfileUpvotes(@RequestBody UserProfilePost newUserProfilePost, @PathVariable Long id) {
		return profilePostRepository.findById(id)
			    .map(userfeed -> {
			      userfeed.setUpvotes(newUserProfilePost.getUpvotes());
			      return profilePostRepository.save(userfeed);
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
