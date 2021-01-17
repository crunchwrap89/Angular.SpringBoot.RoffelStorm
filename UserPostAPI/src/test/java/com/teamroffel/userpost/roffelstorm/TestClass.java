package com.teamroffel.userpost.roffelstorm;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.repository.UsePostRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestClass {

	    @Autowired
	    private TestEntityManager entityManager;

	    @Autowired
	    private UsePostRepository userPostRepository;

	    // write test cases here

	    @Test
	    public void whenFindByName_thenReturnUserName() {
	        // given
	        UserPost usr1 = new UserPost(null, 1, "alex", "ldkgsalgal", 0);
	        
	        entityManager.persist(usr1);
	        entityManager.flush();
	        // when
	        UserPost found = userPostRepository.findByusername("alex");

	        // then

	        assertEquals(found.getUsername(), usr1.getUsername());
//	        assertThat(found.getUsername())
//	          .isEqualTo(usr1.getUsername());
	    }
	
}
