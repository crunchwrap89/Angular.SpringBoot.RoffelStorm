package com.teamroffel.userpost.roffelstorm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
	    public void whenFindByName_thenReturnUserName() {
	        UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        entityManager.persist(usr1);
	        entityManager.flush();
	        UserPost found = userPostRepository.findByusername("alex");
	        assertEquals(found.getUsername(), usr1.getUsername());
	    }
	    
	    @Test
	    public void whenFindAll_thenReturnsAll() {
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
	    
//	    public void findById() {
//	    	 UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
//		     entityManager.persist(usr1);
//		     entityManager.flush();
//		     Long usrid = usr1.getId();
//		     UserPost found = userPostRepository.findById(usrid);
//		        assertEquals(found.getUsername(), usr1.getUsername());
//		      
//	    }
	    
	    @Test
	    public void updateUserPostUpvotes() {
	    	UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        entityManager.persist(usr1);
	        entityManager.flush();
	        
	        

	    }
	    
	
}
