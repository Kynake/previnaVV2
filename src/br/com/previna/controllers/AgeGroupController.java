package br.com.previna.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.com.previna.bo.AgeGroupBO;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.AgeGroup;

import java.sql.SQLException;

@Path("faixa-etaria")
public class AgeGroupController {
	private AgeGroupBO ageGroupBO = new AgeGroupBO();

	@Context
	private HttpServletRequest request;

	@POST
	@Path("/cadastrarFaixaEtaria")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response create(AgeGroup ageGroup) throws PersistenciaException, ValidationException {
		try {
			ageGroupBO.add(ageGroup);
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@GET
	@Path("/listarFaixaEtaria")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(ageGroupBO.list()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/removerFaixaEtaria/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			AgeGroup ageGroup = new AgeGroup();
			ageGroup.setId(id);
			ageGroupBO.remove(ageGroup);
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@PUT
	@Path("/atualizarFaixaEtaria")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response update(AgeGroup ageGroup) throws PersistenciaException, ValidationException {
		try {
			ageGroupBO.update(ageGroup);
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}
}
