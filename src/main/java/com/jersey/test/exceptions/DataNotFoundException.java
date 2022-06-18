package com.jersey.test.exceptions;



public class DataNotFoundException extends RuntimeException {

	/**
	 * Added a RunTimeException as it has a serialID
	 */
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
