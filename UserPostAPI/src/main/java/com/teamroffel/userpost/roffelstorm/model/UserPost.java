package com.teamroffel.userpost.roffelstorm.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userposts")
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;
    
    @Column(name= "user_ID")
    private int userId;
    
    @Column(name= "user_name")
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
    
    

	public UserPost(Long id, LocalDateTime localDateTime, int userId, String username, String text, int upvotes) {
		this.id = id;
		this.localDateTime = localDateTime;
		this.userId = userId;
		this.username = username;
		this.text = text;
		this.upvotes = upvotes;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localDateTime == null) ? 0 : localDateTime.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!localDateTime.equals(other.localDateTime))
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
