package com.teamroffel.userpost.roffelstorm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.teamroffel.userpost.roffelstorm.model.UserProfilePost;

@Repository
public interface UserProfilePostRepository extends JpaRepository<UserProfilePost, Long>{

//	public UserProfilePost createuserprofilepost(UserProfilePost userprofilepost);
	
	public List<UserProfilePost> findByRecieverNameOrderByDateDesc(String recievername);
	
	public List<UserProfilePost> findByRecieverIdOrderByDateDesc(int recieverid);
	
	public List<UserProfilePost> findByAuthorNameOrderByDateDesc(String authorname);
	
	public List<UserProfilePost> findByAuthorIdOrderByDateDesc(int authorid);
	
	public ResponseEntity<UserProfilePost> findUserprofilePostByIdOrderByDateDesc(Long userId);
	
	public void deleteUserProfilePostById(Long userId);
	
//	public Optional<UserProfilePost> updateUserProfileUpvotes(UserProfilePost newUserProfilePost, Long id);
	
	
}
