package com.teamroffel.userpost.roffelstorm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.repository.UserPostRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestClass {

	    @Autowired
	    private TestEntityManager entityManager;

	    @Autowired
	    private UserPostRepository userPostRepository;

	    // write test cases here

	    @Test
	    public void whenFindPostByName_thenReturnAllUserPostsByName() {
	        UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        entityManager.persist(usr1);
	        entityManager.flush();
	        UserPost usr2 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        entityManager.persist(usr2);
	        entityManager.flush();
	        UserPost usr3 = new UserPost(null, 2, "Egone", "ldkgsalgal", 0);
	        entityManager.persist(usr3);
	        entityManager.flush();
	        List<UserPost> found = userPostRepository.findByusername("alex");
	        assertEquals(found.size(), 2);
	    }
	    
	    @Test
	    public void whenFindAllUserPosts_thenReturnsAllUserPosts() {
	    	UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        entityManager.persist(usr1);
	        entityManager.flush();
	        UserPost usr2 = new UserPost(null, 2, "dan", "ldkgsalgal", 0);
	        entityManager.persist(usr2);
	        entityManager.flush();
	        UserPost usr3 = new UserPost(null, 3, "ivan", "ldkgsalgal", 0);
	        entityManager.persist(usr3);
	        entityManager.flush();
	        
	        List<UserPost> usrpt = userPostRepository.findAll();
	        
	        assertEquals(3, usrpt.size());
	        
	    }
	    
	    @Test
	    public void whenFindPostById_thenReturnRightPost() {
	    	 UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
		     entityManager.persist(usr1);
		     entityManager.flush();
		     Optional<UserPost> found = userPostRepository.findById(usr1.getId());
		        assertEquals(found.get(), usr1);
		      
	    }
	    
	    @Test
	    public void updateUserPostUpvotes() {
	    	UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        entityManager.persist(usr1);
	        entityManager.flush();
	        
//	        UserPost usr2 = userPostRepository.);
//	        assertEquals(usr2.getUsername(), usr1.getUsername());
	        
	        

	    }
	    
	
}
