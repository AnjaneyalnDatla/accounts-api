package com.a2nine.accounts.rest;

import static com.a2nine.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;

import javax.naming.NameNotFoundException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.a2nine.accounts.domain.model.Contacts;
import com.a2nine.accounts.usecases.FindAndSaveContacts;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/contacts")
public class ContactsController {

	private final Logger log = LogManager.getLogger(ContactsController.class);

	@Autowired
	private FindAndSaveContacts findAndCreateContacts;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createContact(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(
					findAndCreateContacts.createContact(new ObjectMapper().readValue(jsonBody, Contacts.class))))
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
	public Response fetchContact(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName,
			@NotNull @QueryParam("orgCode") String orgCode) {
		log.info("First Name : " + firstName + ", Last Name : " + lastName);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndCreateContacts.findContacts(firstName, lastName, orgCode))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		} catch (NameNotFoundException e) {
			return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).build();
		}
	}

	@GET
	@Path("/id/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response fetchContactById(@PathParam("id") Long contactId, @NotNull @QueryParam("orgCode") String orgCode) {
		log.info("ID : " + contactId);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndCreateContacts.findContactById(contactId, orgCode))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllContacts(@NotNull @QueryParam("orgCode") String orgCode) {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndCreateContacts.findAllContacts(orgCode))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}
}
