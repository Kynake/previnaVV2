package br.com.previna.controllers;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import br.com.previna.dto.StandardResponseDTO;
import br.com.previna.exception.ImageUploadException;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Conteudo;
import br.com.previna.bo.ConteudoBO;
import br.com.previna.util.ImageUpload;

/**
 * 
 * @author Jhonata Saraiva Peres
 * @since 28/09/2017
 * @version 1.0v
 *
 */

@Path("conteudo")
public class ConteudoController {
	private ConteudoBO conteudoBO = new ConteudoBO();

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/listarConteudo")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(conteudoBO.list()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/cadastrarConteudo")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response create(Conteudo conteudo) throws PersistenciaException, ValidationException {
		long id = -1;
		try {
			// Salva os dados no BD antes de enviar a imagem, para que seja possível salvá-la com o id no nome.
			// Obtendo sucesso, atualiza a entrada no BD com a URL da imagem.
			// A URL de acesso a imagem vai terminar com conteudoID (onde "ID" é o ID do conteúdo).
			id = conteudoBO.add(conteudo);
			if(id != -1 && conteudo.getCapa().length() > 0) {
				conteudo.setId(id);
				conteudo.setLinkImg(ImageUpload.upload(conteudo.getCapa(), "conteudos/conteudo" + String.valueOf(conteudo.getId())));
				conteudoBO.update(conteudo);
			}
		} catch (ImageUploadException e) {
			Map error = ImageUpload.handleImageUploadException(e.getMessage());
			return Response.ok().entity(new StandardResponseDTO(id != -1, (String) error.get("message"))).status((Response.Status) error.get("http_code")).build();
		} catch (Exception e) {
			return Response.ok().entity(new StandardResponseDTO(id != -1, e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@DELETE
	@Path("/removerConteudo/{id}")
	@Produces("aplication/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			Conteudo conteudo = new Conteudo();
			conteudo.setId(id);
			conteudoBO.remove(conteudo);
		} catch (Exception e) {
			return Response.ok().status(Response.Status.ACCEPTED).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@GET
	@Path("/buscarConteudo/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			return Response.ok().entity(conteudoBO.findId(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/atualizarConteudo")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response update(Conteudo conteudo) throws PersistenciaException, ValidationException {
		try {
			if(conteudo.getCapa().length() > 0)
				conteudo.setLinkImg(ImageUpload.upload(conteudo.getCapa(), "conteudos/conteudo" + String.valueOf(conteudo.getId())));
			conteudoBO.update(conteudo);
		} catch (ImageUploadException e) {
			Map error = ImageUpload.handleImageUploadException(e.getMessage());
			return Response.ok().entity(new StandardResponseDTO(false, (String) error.get("message"))).status((Response.Status) error.get("http_code")).build();
		} catch (Exception e) {
			return Response.ok().entity(new StandardResponseDTO(false,e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

}
