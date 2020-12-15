package com.teamroffel.userpost.roffelstorm.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamroffel.userpost.roffelstorm.model.UserPost;
import com.teamroffel.userpost.roffelstorm.repository.UsePostRepository;

@Service
public class UserPostService implements IUserPostService {

    @Autowired
    private UsePostRepository repository;

    @Override
    public List<UserPost> findAll() {

        ArrayList userPosts = (ArrayList<UserPost>) repository.findAll();

        return userPosts;
    }
}
