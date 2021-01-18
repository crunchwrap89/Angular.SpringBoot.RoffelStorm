package com.teamroffel.userpost.roffelstorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teamroffel.userpost.roffelstorm.model.UserProfilePost;

@Repository
public interface UserProfilePostRepository extends JpaRepository<UserProfilePost, Long>{

	
}
