package com.srkr.accounts.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthController {

	@GET
	public Response healthCheck() {
		return Response.status(Response.Status.OK.getStatusCode()).build();
	}

}
