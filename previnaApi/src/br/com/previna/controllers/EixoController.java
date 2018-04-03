package br.com.previna.controllers;

import br.com.previna.bo.EixoBO;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Eixo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("eixo")
public class EixoController {
	private EixoBO eixoBO = new EixoBO();

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/listarEixo")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(eixoBO.list()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/cadastrarEixo")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response create(Eixo eixo) throws PersistenciaException, ValidationException {
		try {
			eixoBO.add(eixo);
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/removerEixo/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			Eixo eixo = new Eixo();
			eixo.setId(id);
			eixoBO.remove(eixo);
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			return Response.ok().entity(eixoBO.findId(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/atualizarEixo")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response update(Eixo eixo) throws PersistenciaException, ValidationException {
		try {
			eixoBO.update(eixo);
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}
}