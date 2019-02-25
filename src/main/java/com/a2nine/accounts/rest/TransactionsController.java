package com.a2nine.accounts.rest;

import static com.a2nine.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
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

import com.a2nine.accounts.domain.model.LineItem;
import com.a2nine.accounts.domain.model.TransactionLog;
import com.a2nine.accounts.domain.model.Transactions;
import com.a2nine.accounts.usecases.FindAndSaveTransactions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/transactions")
public class TransactionsController {

	private final Logger log = LogManager.getLogger(TransactionsController.class);

	@Autowired
	private FindAndSaveTransactions findAndSaveTransactions;

	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactions(@PathParam("username") String user_name,
			@NotNull @QueryParam("orgCode") String orgCode) {
		log.info("Username :" + user_name);
		try {
			List<Transactions> transactions = findAndSaveTransactions.findTransactionsByUsername(user_name, orgCode);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

	@GET
	@Path("/transactionNumber/{transactionNumber}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactions(@PathParam("transactionNumber") Integer transactionNumber,
			@QueryParam("orgCode") String orgCode) throws JsonProcessingException {
		log.info("Transaction Number :" + transactionNumber);
		try {
			Transactions transactions = findAndSaveTransactions.findTransactionsByTransactionNumber(transactionNumber,
					orgCode);
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
	public Response getLineItemsByTransactions(@PathParam("transactionNumber") Integer transactionNumber,
			@QueryParam("orgCode") String orgCode) {
		log.info("transaction_number :" + transactionNumber);
		try {
			Set<LineItem> transactions = findAndSaveTransactions.findAllLineItemsForTransaction(transactionNumber,
					orgCode);
			return Response.status(Response.Status.OK.getStatusCode()).entity(toJsonString(transactions)).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllTransactions(@QueryParam("transactionType") Long transactionType,
			@NotNull @QueryParam("orgCode") String orgCode) {
		log.info("transactionType : " + transactionType);
		try {
			if (null != transactionType) {
				return Response.status(Response.Status.OK.getStatusCode())
						.entity(toJsonString(
								findAndSaveTransactions.findAllTransactionsByTransactionType(transactionType, orgCode)))
						.build();
			}
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveTransactions.findAllTransactions(orgCode))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}

	@GET
	@Path("/transactionNumber/new")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTransactionNumber(@NotNull @QueryParam("orgCode") String orgCode) {
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
	public Response approveTransaction(@PathParam("transactionNumber") Integer transactionNumber,
			@RequestBody String jsonBody) {
		log.info("transactionNumber:" + transactionNumber);
		try {
			findAndSaveTransactions.approveTransaction(new ObjectMapper().readValue(jsonBody, TransactionLog.class));
			// return Response.status(Response.Status.OK.getStatusCode()).entity().build();
			// findAndSaveTransactions.approveTransaction(transactionNumber, status);
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
