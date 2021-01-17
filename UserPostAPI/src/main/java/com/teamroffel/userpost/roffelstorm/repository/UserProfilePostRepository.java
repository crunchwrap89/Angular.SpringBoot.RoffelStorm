package com.teamroffel.userpost.roffelstorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.teamroffel.userpost.roffelstorm.model.UserProfilePost;


public interface UserProfilePostRepository extends JpaRepository<UserProfilePost, Long>{

	
}
