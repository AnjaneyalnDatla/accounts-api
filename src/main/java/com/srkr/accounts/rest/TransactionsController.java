package com.srkr.accounts.rest;

import static com.srkr.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;
import java.util.List;

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

import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.usecases.FindAndSaveTransactions;

@Path("/transactions")
public class TransactionsController {

	private final Logger log = LogManager.getLogger(TransactionsController.class);

	@Autowired
	private FindAndSaveTransactions findTransactions;

	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactions(@PathParam("username") String user_name) {
		log.info("Username :" + user_name);
		try {
			List<Transactions> transactions = findTransactions.findTransactionsByUsername(user_name);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllTransactions() {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findTransactions.findAllTransactions())).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		} 
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response saveTransactions(@RequestBody String JsonBody) {
		log.info("Json Body :" + JsonBody);
		findTransactions.saveTransactions(JsonBody);
		return Response.status(Response.Status.OK.getStatusCode()).build(); // "Transaction created successfully";
	}
}
