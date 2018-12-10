package com.srkr.accounts.rest;

import static com.srkr.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.usecases.FindAndSaveTransactions;

@Path("/transactions")
public class TransactionsController {

	private final Logger log = LogManager.getLogger(TransactionsController.class);

	@Autowired
	private FindAndSaveTransactions findAndSaveTransactions;

	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactions(@PathParam("username") String user_name) {
		log.info("Username :" + user_name);
		try {
			List<Transactions> transactions = findAndSaveTransactions.findTransactionsByUsername(user_name);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}
	
	@GET
	@Path("/transactionNumber")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactions(@QueryParam("transactionNumber") Integer transactionNumber) {
		log.info("Transaction Number :" + transactionNumber);
		try {
			List<Transactions> transactions = findAndSaveTransactions.findTransactionsByTransactionNumber(transactionNumber);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}
	
	@GET
	@Path("/lineItems")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLineItemsByTransactions(@QueryParam("transactionNumber") String transactionNumber) {
		log.info("transaction_number :" + transactionNumber);
		try {
			Set<LineItem> transactions = findAndSaveTransactions.findAllLineItemsForTransaction(transactionNumber);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

//	@GET
//	@Path("/{transaction_number}/lineItems")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getLineItemsByTransactions(@PathParam("transaction_number") String transaction_number) {
//		log.info("transaction_number :" + transaction_number);
//		try {
//			Set<LineItem> transactions = findAndSaveTransactions.findAllLineItemsForTransaction(transaction_number);
//			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
//		}
//
//	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllTransactions() {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveTransactions.findAllTransactions())).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response saveTransactions(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(findAndSaveTransactions
					.saveTransaction(new ObjectMapper().readValue(jsonBody, Transactions.class)))).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}
}
