package com.teamroffel.userpost.roffelstorm.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "userposts")
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "date_posted", columnDefinition = "TIMESTAMP")
    @CreationTimestamp  
    private Date date;
    
    @Column
    private int userId;
    
    @Column
    private String username;
    
    @Column(name= "post_content")
    private String text;
    
    @Column
    private int upvotes;

    
    public UserPost() {
    }

//    public UserPost(Long id,  String text, int userId) {
//
//        this.id = id;
//        this.text = text;
//        this.userId = userId;
//        
//    }
    
//	public UserPost(Long id, LocalDateTime localDateTime, int userId, String username, String text, int upvotes) {
//		this.id = id;
//		this.localDateTime = localDateTime;
//		this.userId = userId;
//		this.username = username;
//		this.text = text;
//		this.upvotes = upvotes;
//	}
    
	public UserPost(Long id, int userId, String username, String text, int upvotes) {
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.text = text;
		this.upvotes = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + upvotes;
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPost other = (UserPost) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (upvotes != other.upvotes)
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	





}
