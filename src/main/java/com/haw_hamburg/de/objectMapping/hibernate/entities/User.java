package com.haw_hamburg.de.objectMapping.hibernate.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String firstName;
	private String lastName;

	@OrderColumn(name = "loginData")
	private LoginData loginData;

	@OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
	private Set<Activity> userPosts = new HashSet<>();
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
	private Set<Activity> userComments = new HashSet<>();

	@ManyToMany(targetEntity = Discussion.class, mappedBy = "users")
	private List<Discussion> discussions;

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

	public Set<Activity> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(Set<Activity> userPosts) {
		this.userPosts = userPosts;
	}
	
	public Set<Activity> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<Activity> userComments) {
		this.userComments = userComments;
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	 public LoginData getLoginData() {
	 return loginData;
	 }
	
	 public void setLoginData(LoginData loginData) {
	 this.loginData = loginData;
	 }

}
