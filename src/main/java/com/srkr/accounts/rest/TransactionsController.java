package com.srkr.accounts.rest;

import static com.srkr.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.TransactionLog;
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
	@Path("/transactionNumber/{transactionNumber}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactions(@PathParam("transactionNumber") Integer transactionNumber)
			throws JsonProcessingException {
		log.info("Transaction Number :" + transactionNumber);
		try {
			Transactions transactions = findAndSaveTransactions.findTransactionsByTransactionNumber(transactionNumber);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.NOT_FOUND.getStatusCode())
					.entity(toJsonString("Requested resource not found")).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

	@GET
	@Path("/lineItems/{transactionNumber}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLineItemsByTransactions(@PathParam("transactionNumber") Integer transactionNumber) {
		log.info("transaction_number :" + transactionNumber);
		try {
			Set<LineItem> transactions = findAndSaveTransactions.findAllLineItemsForTransaction(transactionNumber);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllTransactions(@QueryParam("transactionType") Long transactionType) {
		log.info("transactionType : " + transactionType);
		try {
			if (null != transactionType) {
				return Response.status(Response.Status.OK.getStatusCode()).entity(
						toJsonString(findAndSaveTransactions.findAllTransactionsByTransactionType(transactionType)))
						.build();
			}
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveTransactions.findAllTransactions())).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}

	@GET
	@Path("/transactionNumber/new")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactionNumber() {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(findAndSaveTransactions.transactionNumber()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}

	@PUT
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

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateTransactions(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(findAndSaveTransactions
					.updateTransaction(new ObjectMapper().readValue(jsonBody, Transactions.class)))).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}

	@POST
	@Path("/documents")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addDocumentMetaData(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			findAndSaveTransactions
					.addDocumentMetaDataToTransaction(new ObjectMapper().readValue(jsonBody, Transactions.class));
			return Response.status(Response.Status.OK.getStatusCode()).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}

	@GET
	@Path("/documents/{transactionNumber}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getDocumentMetaData(@PathParam("transactionNumber") Long transactionNumber) {
		log.info("transactionNumber:" + transactionNumber);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveTransactions.getDocumentMetaData(transactionNumber))).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}
	
	@POST
	@Path("/approve")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response approveTransaction(@PathParam("transactionNumber") Integer transactionNumber, @RequestBody String jsonBody) {
		log.info("transactionNumber:" + transactionNumber);
		try {
			 findAndSaveTransactions
					.approveTransaction(new ObjectMapper().readValue(jsonBody, TransactionLog.class));
			//return Response.status(Response.Status.OK.getStatusCode()).entity().build();
			//findAndSaveTransactions.approveTransaction(transactionNumber, status);
			return Response.status(Response.Status.OK.getStatusCode()).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}
	
	@Path("/sendemail")
	   public String sendEmail() {
		try {
			findAndSaveTransactions.sendEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return "Email sent successfully";
	   }   
	
}
