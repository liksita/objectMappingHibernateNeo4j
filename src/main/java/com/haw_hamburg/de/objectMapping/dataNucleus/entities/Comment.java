package com.haw_hamburg.de.objectMapping.dataNucleus.entities;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Comment {
	
//	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	private String id;

	private Date date;
	
//	@ManyToOne
//	private User author;
//
//	@ManyToOne
//	private Post post;
//	
//	@ElementCollection
//	@OrderColumn(name = "commentNr")
//	private List<Comment> comments;

	Comment() {
	}

	public Comment(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
//	public User getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(User author) {
//		this.author = author;
//	}
//	
//	public Post getPost() {
//		return post;
//	}
//
//	public void setPost(Post post) {
//		this.post = post;
//	}

}
