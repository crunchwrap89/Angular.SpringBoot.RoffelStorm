package com.teamroffel.userpost.roffelstorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teamroffel.userpost.roffelstorm.model.UserPost;

@Repository
public interface UsePostRepository extends JpaRepository<UserPost, Long> {

	public UserPost findByusername(String name);
}
