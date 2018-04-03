package br.com.previna.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.com.previna.bo.UserBO;
import br.com.previna.dao.UserDAO;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.User;
import br.com.previna.util.EncryptUtil;

@Path("users")
public class UserController {
	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();
	private EncryptUtil encryptUtil = new EncryptUtil();

	@Context
	private HttpServletRequest request;
	private HttpSession session;

	@GET
	@Path("/listarUsuario")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(userBO.listUser()).status(Response.Status.ACCEPTED).build();

		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Path("/cadastrarUsuario")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response create(User user) throws PersistenciaException, ValidationException {
		try {
			userBO.createUser(user);
		} catch (Exception e) {
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}

		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/removerUsuario/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {

		try {
			userBO.disable(id);

		} catch (Exception e) {

			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();


	}
	@GET
	@Path("/buscarUsuario/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			return Response.ok().entity(userBO.getUserById(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/atualizarUsuario")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response update(User user) throws PersistenciaException, ValidationException {
		try {
			userBO.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}
}
