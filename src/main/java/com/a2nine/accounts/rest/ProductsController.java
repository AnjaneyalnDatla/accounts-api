package com.a2nine.accounts.rest;

import static com.a2nine.accounts.util.ObjectSerializer.toJsonString;

import java.io.IOException;

import javax.validation.constraints.NotNull;
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

import com.a2nine.accounts.domain.model.Products;
import com.a2nine.accounts.usecases.FindAndSaveProducts;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/products")
public class ProductsController {

	private final Logger log = LogManager.getLogger(ProductsController.class);

	@Autowired
	private FindAndSaveProducts findAndSaveProducts;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createProduct(@RequestBody String jsonBody) {
		log.info("json body:" + jsonBody);
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(
							findAndSaveProducts.saveProduct(new ObjectMapper().readValue(jsonBody, Products.class))))
					.build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
		}
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllProducts(@NotNull @QueryParam("orgCode") String orgCode) {
		try {
			return Response.status(Response.Status.OK.getStatusCode())
					.entity(toJsonString(findAndSaveProducts.findAllProducts(orgCode))).build();
		} catch (IOException e) {
			return Response.status(Response.Status.FORBIDDEN.getStatusCode()).build();
		}
	}

}
