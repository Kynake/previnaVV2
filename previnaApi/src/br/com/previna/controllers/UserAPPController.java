package br.com.previna.controllers;

import br.com.previna.bo.UserAPPBO;
import br.com.previna.bo.UserBO;
import br.com.previna.dao.UserDAO;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.User;
import br.com.previna.model.UserAPP;
import br.com.previna.util.EncryptUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
@Path("usersapp")
public class UserAPPController {
    private UserAPPBO userAPPBO = new UserAPPBO();

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @GET
    @Path("/listarUsuario")
    @Produces("application/json; charset=UTF-8")
//    @JWTTokenNeeded
    public Response list() throws PersistenciaException, SQLException {
        try {
            return Response.ok().entity(userAPPBO.listUser()).status(Response.Status.ACCEPTED).build();

        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

    }

    @POST
    @Path("/cadastrarUsuario")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
//    @JWTTokenNeeded
    public Response create(UserAPP user) throws PersistenciaException, ValidationException {
        try {
            userAPPBO.createUser(user);
        } catch (Exception e) {
            return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().status(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("/buscarUsuario/{id}")
    @Produces("application/json; charset=UTF-8")
//    @JWTTokenNeeded
    public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
        try {
            return Response.ok().entity(userAPPBO.getUserById(id)).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/verificaExiste/{email}")
    @Produces("application/json; charset=UTF-8")
//    @JWTTokenNeeded
    public Response searchByID(@PathParam("email") String email) throws PersistenciaException, ValidationException {
        try {
            return Response.ok().entity(userAPPBO.getUserByEmail(email)).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/atualizarUsuario")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
//    @JWTTokenNeeded
    public Response update(UserAPP user) throws PersistenciaException, ValidationException {
        try {
            userAPPBO.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok().entity(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().status(Response.Status.ACCEPTED).build();
    }
}
