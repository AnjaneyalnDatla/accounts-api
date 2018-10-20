package com.srkr.accounts.rest;

import static com.srkr.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;

import javax.naming.NameNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.usecases.CreateContact;
import com.srkr.accounts.usecases.FindContact;

@Path("/contacts")
public class ContactsController {

	private final Logger log = LogManager.getLogger(ContactsController.class);

	@Autowired
	private CreateContact createContactUsecase;

	@Autowired
	private FindContact findContact;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createContact(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(
							createContactUsecase.createContact(new ObjectMapper().readValue(jsonBody, Contacts.class))))
					.build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}

	@GET
	@Path("/name")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getContact(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
		log.info("First Name : " + firstName + ", Last Name : " + lastName);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findContact.findContacts(firstName, lastName))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		} catch (NameNotFoundException e) {
			return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).build();
		}
	}
}
