package br.com.previna.controllers;

import br.com.previna.bo.PerguntaBO;
import br.com.previna.dto.StandardResponseDTO;
import br.com.previna.exception.ImageUploadException;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Pergunta;
import br.com.previna.util.ImageUpload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("pergunta")
public class PerguntaController {
	private PerguntaBO perguntaBO = new PerguntaBO();

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/listarPergunta")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response list() throws PersistenciaException, SQLException {
		try {
			return Response.ok().entity(perguntaBO.list()).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/cadastrarPergunta")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response create(Pergunta pergunta) throws PersistenciaException, ValidationException {
		long id = -1;
		try {
			// Salva os dados no BD antes de enviar a imagem, para que seja possível salvá-la com o id no nome.
			// Obtendo sucesso, atualiza a entrada no BD com a URL da imagem.
			// A URL de acesso a imagem vai terminar com perguntaID (onde "ID" é o ID da pergunta).
			id = perguntaBO.add(pergunta);
			if(id != -1 && pergunta.getCapa().length() > 0) {
				pergunta.setId(id);
				pergunta.setUrlCapa(ImageUpload.upload(pergunta.getCapa(), "perguntas/pergunta" + String.valueOf(pergunta.getId())));
				perguntaBO.update(pergunta);
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
	@Path("/removerPergunta/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			Pergunta pergunta = new Pergunta();
			pergunta.setId(id);
			perguntaBO.remove(pergunta);
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}

	@GET
	@Path("/buscarPergunta/{id}")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
		try {
			return Response.ok().entity(perguntaBO.findId(id)).status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.ok().status(Response.Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/atualizarPergunta")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@JWTTokenNeeded
	public Response update(Pergunta pergunta) throws PersistenciaException, ValidationException {
		try {
			if(pergunta.getCapa().length() > 0)
				pergunta.setUrlCapa(ImageUpload.upload(pergunta.getCapa(), "perguntas/pergunta" + String.valueOf(pergunta.getId())));
			perguntaBO.update(pergunta);
		} catch (ImageUploadException e) {
			Map error = ImageUpload.handleImageUploadException(e.getMessage());
			return Response.ok().entity(new StandardResponseDTO(false, (String) error.get("message"))).status((Response.Status) error.get("http_code")).build();
		} catch (Exception e) {
			return Response.ok().entity(new StandardResponseDTO(false,e.getMessage())).status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().status(Response.Status.ACCEPTED).build();
	}
}

