package com.teamroffel.userpost.roffelstorm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.model.UserProfilePost;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {

	public List <UserPost> findByusername(String name);
	
	public List <UserPost> findAll();
	
	public List <UserPost> findAllByOrderByDateDesc();
	
	public Optional<UserPost> findById(Long postId);

	
	public ResponseEntity<UserPost> findPostById(@PathVariable(value = "id") Long userId);
	
	
	
//	public UserPost findBy
}
