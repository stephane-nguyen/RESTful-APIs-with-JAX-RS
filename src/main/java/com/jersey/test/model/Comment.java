package com.jersey.test.model;

import java.util.Date;

public class Comment {
	
	private long id;
	private String message;
	private Date created;
	private String authour;
	
	public Comment() {}

	public Comment(long id, String message, Date created, String authour) {
		super();
		this.id = id;
		this.message = message;
		this.created = created;
		this.authour = authour;
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

	public String getAuthour() {
		return authour;
	}

	public void setAuthour(String authour) {
		this.authour = authour;
	}
	
	
}
