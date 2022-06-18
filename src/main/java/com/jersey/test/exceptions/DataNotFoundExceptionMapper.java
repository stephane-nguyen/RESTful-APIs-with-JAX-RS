package com.jersey.test.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jersey.test.model.ErrorMessage;

@Provider //register to jax-rs the exception
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	//to send a proper response and not the big apache tomcat xml 
	//can be implemented for any exception
	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "link to documentation");
		return Response.status(Status.NOT_FOUND)
								.entity(errorMessage)
								.build();
	}

}
