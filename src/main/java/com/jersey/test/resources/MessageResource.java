package com.jersey.test.resources;

import java.net.URI;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jersey.test.model.Message;
import com.jersey.test.resources.beans.MessageFilterBean;
import com.jersey.test.service.MessageService;

// path to mention after the url defined in web.xml
// webapi/messages
@Path("messages")
//all of the annotation here applies for the MessageResource class
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET // request
	@Produces(MediaType.APPLICATION_JSON) // convert to XML format, add XML annotation in Message.java
	// @QueryParam. If there is a query parameter in the URL :
	// .../messages?year=2015, .../messages?start=3&size=5

	// BeansParam replace the 3 @QueryParam with the MessageFilterBean.java
//	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
//			@QueryParam("size") int size) {

	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {

//		if (year > 0) {
//			return messageService.getAllMessagesForYear(year);
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
//		if (start > 0 && size > 0) {
//			return messageService.getAllMessagesPaginated(start, size);
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}") // path after webapi/messages (path inside path)
	@Produces(MediaType.APPLICATION_JSON) // add dependency pom.xml
	// PathParam to correspond {messageId} with "messageId"
	// messageId in string because the URL is a string
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON) // specify the expected request body format
	@Produces(MediaType.APPLICATION_JSON)
//	public Message addMessage(Message message) {
//		return messageService.addMessage(message);
//	}

	// Sending Status code, we create the response to control it better 
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		String newMessageId = String.valueOf(newMessage.getId());
		// get path(../messages/) + add Id in the URI 
		URI uri = uriInfo.getAbsolutePathBuilder().path(newMessageId).build();
		return Response.created(uri)
						.entity(newMessage)
						.build();
//		return Response.status(Status.CREATED)
//				.entity(newMessage) // data to the builder
//				.build(); //all of the methods before build() are data added to it 
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	@Path("/{messageId}/comments") 
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
