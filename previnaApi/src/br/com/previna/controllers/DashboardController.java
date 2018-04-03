package br.com.previna.controllers;

import br.com.previna.dao.DashboardDAO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/dashboard")
public class DashboardController {

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/quantidadeItens")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response count() {
		try {
			return Response.ok().entity(DashboardDAO.itemsCount()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}
}
