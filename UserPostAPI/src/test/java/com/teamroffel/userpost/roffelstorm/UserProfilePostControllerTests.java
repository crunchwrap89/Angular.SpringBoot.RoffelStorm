package com.teamroffel.userpost.roffelstorm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.teamroffel.userpost.roffelstorm.model.UserProfilePost;
import com.teamroffel.userpost.roffelstorm.repository.UserProfilePostRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserProfilePostControllerTests {
	
	@Autowired
	UserProfilePostRepository upr;
	
	@Autowired
    private TestEntityManager entityManager;
	
//	@Test
//	public void whenFindByAuthorId_thenReturnPostByAuthorId() {
//		UserProfilePost usr1 = new UserProfilePost(null, "sdasdsa", 1, 2,"dan", "daniella");
//        entityManager.persist(usr1);
//        entityManager.flush();
//        UserProfilePost usr2 = new UserProfilePost(null, "mjauuu", 8, 9,"stan", "oskar");
//        entityManager.persist(usr2);
//        entityManager.flush();
//        List<UserProfilePost> found = upr.findByAuthorIdOrderByDateDesc(8);
//        assertEquals( "stan", found.get(0).getAuthorName());
//	}
	
//	@Test
//	public void whenFindPostByAuthorname_thenReturnPostByAuthorname() {	
//		UserProfilePost usr1 = new UserProfilePost(null, "sdasdsa", 1, 2,"dan", "daniella");
//        entityManager.persist(usr1);
//        entityManager.flush();
//        List<UserProfilePost> found = upr.findByAuthorNameOrderByDateDesc("dan");
//        assertEquals(1, found.size());
//	}
	
	@Test
	public void whenFindPostByRecievername_thenReturnPostByRecievername() {	
		UserProfilePost usr1 = new UserProfilePost(null, "sdasdsa", 1, 2,"dan", "daniella");
        entityManager.persist(usr1);
        entityManager.flush();
        UserProfilePost usr2 = new UserProfilePost(null, "sdasdsa", 3, 2,"erik", "daniella");
        entityManager.persist(usr2);
        entityManager.flush();
        List<UserProfilePost> found = upr.findByRecieverNameOrderByDateDesc("daniella");
        assertEquals(2, found.size());
	}
	
	
	@Test
	public void whenFindByRecieverId_thenReturnPostByRecieverId() {
		UserProfilePost usr1 = new UserProfilePost(null, "sdasdsa", 1, 2,"dan", "daniella");
        entityManager.persist(usr1);
        entityManager.flush();
        UserProfilePost usr2 = new UserProfilePost(null, "sdasdsa", 3, 2,"erik", "daniella");
        entityManager.persist(usr2);
        entityManager.flush();
        List<UserProfilePost> found = upr.findByRecieverIdOrderByDateDesc(2);
        assertEquals(found.get(0).getRecieverName(), "daniella");
	}
	
	@Test
	public void whenFindAll_thenReturnAll() {
		UserProfilePost usr1 = new UserProfilePost(null, "sdasdsa", 1, 2,"dan", "daniella");
        entityManager.persist(usr1);
        entityManager.flush();
        UserProfilePost usr2 = new UserProfilePost(null, "sdasdsa", 3, 2,"erik", "daniella");
        entityManager.persist(usr2);
        entityManager.flush();
        UserProfilePost usr3 = new UserProfilePost(null, "sdasdsa", 3, 2,"erik", "daniella");
        entityManager.persist(usr3);
        entityManager.flush();
        List<UserProfilePost> found = upr.findAll();
        assertEquals(3, found.size());
	}

//	Long id, String text, int authorId, int recieverId, String authorName, String recieverName
	
	
	
	
}
