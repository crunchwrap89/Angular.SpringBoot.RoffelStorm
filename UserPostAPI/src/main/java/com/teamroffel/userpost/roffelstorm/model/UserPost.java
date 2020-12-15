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

    public UserPost(Long id,  String text, int userId) {

        this.id = id;
        this.text = text;
        this.userId = userId;
        
    }
    
    

	public UserPost(Long id, LocalDateTime localDateTime, int userId, String username, String text, int upvotes) {
		super();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.text);
        hash = 79 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPost other = (UserPost) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

}
