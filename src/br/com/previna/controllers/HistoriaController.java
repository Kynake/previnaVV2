package br.com.previna.controllers;

import br.com.previna.bo.HistoriaBO;
import br.com.previna.dto.StandardResponseDTO;
import br.com.previna.exception.ImageUploadException;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Historia;
import br.com.previna.util.ImageUpload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("historia")
public class HistoriaController {
    private HistoriaBO historiaBO = new HistoriaBO();

    @Context
    private HttpServletRequest request;

    @GET
    @Path("/listarHistoria")
    @Produces("application/json; charset=UTF-8")
    @JWTTokenNeeded
    public Response list() throws PersistenciaException, SQLException {
        try {
            return Response.ok().entity(historiaBO.list()).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/cadastrarHistoria")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @JWTTokenNeeded
    public Response create(Historia historia) throws PersistenciaException, ValidationException {
        long id = -1;
        try {
            // Salva os dados no BD antes de enviar a imagem, para que seja possível salvá-la com o id no nome.
            // Obtendo sucesso, atualiza a entrada no BD com a URL da imagem.
            // A URL de acesso a imagem vai terminar com historiaID (onde "ID" é o ID da história).
            id = historiaBO.add(historia);
            if(id != -1 && historia.getCapa().length() > 0) {
                historia.setId(id);
                historia.setLinkImg(ImageUpload.upload(historia.getCapa(), "historias/historia" + String.valueOf(historia.getId())));
                historiaBO.update(historia);
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
    @Path("/removerHistoria/{id}")
    @Produces("application/json; charset=UTF-8")
    @JWTTokenNeeded
    public Response remove(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
        try {
            Historia historia = new Historia();
            historia.setId(id);
            historiaBO.remove(historia);
        } catch (Exception e) {
        	System.out.println("gosgo");
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().status(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("/buscarHistoria/{id}")
    @Produces("application/json; charset=UTF-8")
    @JWTTokenNeeded
    public Response buscarPorId(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
        try {
            return Response.ok().entity(historiaBO.findId(id)).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/atualizarHistoria")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @JWTTokenNeeded
    public Response update(Historia historia) throws PersistenciaException, ValidationException {
        try {
            if(historia.getCapa().length() > 0)
                historia.setLinkImg(ImageUpload.upload(historia.getCapa(), "historias/historia" + String.valueOf(historia.getId())));
            historiaBO.update(historia);
        } catch (ImageUploadException e) {
            Map error = ImageUpload.handleImageUploadException(e.getMessage());
            return Response.ok().entity(new StandardResponseDTO(false, (String) error.get("message"))).status((Response.Status) error.get("http_code")).build();
        } catch (Exception e) {
            return Response.ok().entity(new StandardResponseDTO(false,e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().status(Response.Status.ACCEPTED).build();
    }
}

