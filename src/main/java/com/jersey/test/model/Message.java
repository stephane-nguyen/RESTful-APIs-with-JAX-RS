package com.jersey.test.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {
	
	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long, Comment> comments = new HashMap<>();
	//way for the framework to be able to create new instance of the class 
	public Message() {} //model needs to have an empty constructor 
	
	
	public Message(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
	}

	@XmlTransient //we don't want the comments data to show up when the message is pulled up in the API
	//we don't want to have the list of comments when you access a message
	public Map<Long, Comment> getComments(){
		return comments;
	}
	
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments; 
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
