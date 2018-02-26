package com.haw_hamburg.de.objectMapping.dataNucleus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Post {
	
//	@PrimaryKey
//	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private String id;

	private String title;
	private Date date;

	private User author;

	private List<Comment> comments;

	// constructors, getters and setters...

	Post() {
	}

	public Post(String title, Date date, Comment... comments) {
		this.title = title;
		this.date = date;
		this.comments = comments != null ? new ArrayList<>( Arrays.<Comment>asList( comments ) ) : new ArrayList<Comment>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
