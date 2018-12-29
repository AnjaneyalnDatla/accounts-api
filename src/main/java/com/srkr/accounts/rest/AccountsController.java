package com.srkr.accounts.rest;

import static com.srkr.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srkr.accounts.domain.model.Accounts;
import com.srkr.accounts.usecases.FindAndSaveAccounts;

@Path("/account")
public class AccountsController {

	private final Logger log = LogManager.getLogger(AccountsController.class);

	@Autowired
	private FindAndSaveAccounts findAndSaveAccounts;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createAccount(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(
							findAndSaveAccounts.saveAccount(new ObjectMapper().readValue(jsonBody, Accounts.class))))
					.build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAccounts() {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveAccounts.findAllAccounts())).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

	@GET
	@Path("/id/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAccountsById(@PathParam("id") Long id) {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveAccounts.findAccountById(id))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

	@GET
	@Path("/accountTypes")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAccountTypes() {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveAccounts.findAllAccountTypes())).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

}
