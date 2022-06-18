package com.jersey.test.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jersey.test.model.ErrorMessage;

//@Provider //register to jax-rs the exception
//throwable: catch all exceptions no matter what it is 
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	//to send a proper response and not the big apache tomcat xml 
	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "link to documentation");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
								.entity(errorMessage)
								.build();
	}

}
