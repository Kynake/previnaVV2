package br.com.previna.controllers;

import br.com.previna.bo.FaseBO;
import br.com.previna.dao.FaseDAO;
import br.com.previna.dto.FaseFormattedDTO;
import br.com.previna.exception.PersistenciaException;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Conexao;
import br.com.previna.model.Fase;
import br.com.previna.model.User;
import br.com.previna.util.Util;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("fase")
public class FaseController {
FaseBO faseBO = new FaseBO();

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/criarFase")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response index(Fase fase) {
        try {
            faseBO.criaFase(fase);
            return null;
        }catch (Exception e){
    return null;
        }

    }

    @GET
    @Path("/listarFases")
    @Produces("application/json; charset=UTF-8")
    public Response lista(){
        try {
            List<Fase> faseList = faseBO.listaFase();

            return Response.ok().entity(faseList).build();

        }catch (Exception e){
            return null;
        }
    }

    @GET
    @Path("/listarFasesMenu")
    @Produces("application/json; charset=UTF-8")
    public Response listaMenu(){
        try {
            List<FaseFormattedDTO> faseFormattedDTOList = faseBO.listaFaseFormated();
            return Response.ok().entity(faseFormattedDTOList).build();

        }catch (Exception e){
            return null;
        }
    }

    @GET
    @Path("/buscarFase/{id}")
    @Produces("application/json; charset=UTF-8")
    public Response searchByID(@PathParam("id") Long id) throws PersistenciaException, ValidationException {
        try {
            return Response.ok().entity(faseBO.findId(id)).status(Response.Status.ACCEPTED).build();
        } catch (Exception e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }
    }

}
