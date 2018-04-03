package br.com.previna.controllers;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.com.previna.util.sendMail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;


import br.com.previna.bo.UserBO;
import br.com.previna.dao.UserDAO;
import br.com.previna.model.Token;
import br.com.previna.model.User;
import br.com.previna.util.EncryptUtil;
import br.com.previna.util.TokenGenerator;


@Path("auth")
public class AuthController {
	Logger logger = Logger.getLogger("controller.AuthController");

	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();

	private EncryptUtil encryptUtil = new EncryptUtil();
	@Context
	private HttpServletResponse response;
	@Context
	private HttpServletRequest request;
	private HttpSession session = null;

	@POST
	@Path("/login")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response login(User userLogin) throws IOException {
		User user;
		HashMap<String, Object> responsed = new HashMap<>();
		Token token=new Token();
		try {
			ObjectMapper mapper = new ObjectMapper();
			user = userBO.userExists(userLogin);
			 token.setToken(TokenGenerator.issueToken(mapper.writeValueAsString(user)));
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(UNAUTHORIZED).build();
		}
		return Response.ok().entity(token).build();
	}

	
	@POST
	@Path("/recoverPasswordRequest")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response recoverPasswordRequest(User userLogin) throws IOException {
		
		UUID idOne = UUID.randomUUID();
		
		
		
		return Response.ok().entity("da").build();
	}
	@POST
	@Path("/mandarEmail")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response enviaEmailRecuperaSenha(User userEmail){
		try {
            User recuperando= userBO.getUserByEmail(userEmail.getEmail());
            recuperando.setUuid(UUID.randomUUID().toString());
			sendMail.sendMail(recuperando);
            userBO.updateUser(recuperando);


        }catch (Exception e){
			return Response.ok().status(Response.Status.BAD_REQUEST).build();


		}
		return Response.ok().status(Response.Status.OK).build();
	}

    @POST
    @Path("/recuperarSenha")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response trocaSenhaUsuario(@HeaderParam("uuid") String uuid, User user){


	    try {
            User recuperando= userBO.getUserByEmail(user.getEmail());
            if(recuperando.getUuid().equals(uuid)) {
                recuperando.setUuid("");
				userBO.recuperaSenha(recuperando, user.getPassword());
			}
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
            return Response.ok().status(Response.Status.OK).build();
    }

}
