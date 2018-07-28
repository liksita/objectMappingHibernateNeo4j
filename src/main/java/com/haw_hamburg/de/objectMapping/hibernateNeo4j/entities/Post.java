package com.haw_hamburg.de.objectMapping.hibernateNeo4j.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "B")
public class Post extends Activity {

	@OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
	private Set<Comment> userComments = new HashSet<>();

	// constructors, getters and setters...

	Post() {

	}

	public Post(String title, Date date) {
		super(date);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<Comment> userComments) {
		this.userComments = userComments;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

}
