package com.haw_hamburg.de.objectMapping.dataNucleus.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class User {
//	
//	@PrimaryKey
//	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private String id;

	private String firstName;
	private String lastName;

	private Set<Post> userPosts = new HashSet<>();
	
	private List<Comment> userComments;

	// constructors, getters and setters...

	User() {
	}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(Set<Post> userPosts) {
		this.userPosts = userPosts;
	}
	
	public List<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<Comment> userComments) {
		this.userComments = userComments;
	}

}