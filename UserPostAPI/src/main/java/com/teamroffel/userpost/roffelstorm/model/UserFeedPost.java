package com.teamroffel.userpost.roffelstorm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userfeedposts")
public class UserFeedPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "author_ID")
	private int authorId;

	@Column(name = "post_content")
	private String text;

	@Column(name = "reciever_ID")
	private int recieverId;
	

	public UserFeedPost() {
	}


	public UserFeedPost(Long id, String text, int authorId, int recieverId) {
		this.id = id;
		this.text = text;
		this.authorId = authorId;
		this.recieverId = recieverId;
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

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + recieverId;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		UserFeedPost other = (UserFeedPost) obj;
		if (authorId != other.authorId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (recieverId != other.recieverId)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}




}
