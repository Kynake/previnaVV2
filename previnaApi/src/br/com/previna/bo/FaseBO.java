package br.com.previna.bo;

import br.com.previna.dao.FaseDAO;
import br.com.previna.dao.HistoriaDAO;
import br.com.previna.dao.NodoDAO;
import br.com.previna.dto.FaseFormattedDTO;
import br.com.previna.model.*;

import java.util.ArrayList;
import java.util.List;

public class FaseBO {


    FaseDAO faseDAO = new FaseDAO();
    NodoDAO nodoDAO = new NodoDAO();
    HistoriaDAO historiaDAO = new HistoriaDAO();
    PerguntaBO perguntaBO = new PerguntaBO();
    ConteudoBO conteudoBO = new ConteudoBO();
    HistoriaBO historiaBO = new HistoriaBO();
    NodoBO nodoBO = new NodoBO();


    public boolean criaFase(Fase fase) {
        try {


            criaNodos(fase);
            faseDAO.save(fase);
        } catch (Exception e) {

            return false;
        }
        return true;

    }

    public boolean criaNodos(Fase fase) {
        try {
            ArrayList<Nodo> nodos = new ArrayList<>();
            for (int i =0 ;i <fase.getConexoes().size(); i++){

                long id =0 ;
                    Nodo n = fase.getConexoes().get(i).getNodoInicial();
                    fase.getConexoes().get(i).setNodoInicial(nodoBO.nodoExiste(n));

                fase.getConexoes().get(i).setNodoCerto(nodoBO.nodoExiste(fase.getConexoes().get(i).getNodoCerto()));
                if(fase.getConexoes().get(i).getNodoErrado()!=null)
                    fase.getConexoes().get(i).setNodoErrado(nodoBO.nodoExiste(fase.getConexoes().get(i).getNodoErrado()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




    public void finalExiste(){
        try{
            if (nodoDAO.findId(1,Nodo.class)==null) {
                Nodo nodo= new Nodo();
                Historia historia = new Historia();
                historia.setDescricao("Final da Fase , parabéns");
                nodo.setHistoria(historia);
                nodoDAO.save(nodo);
            }
        }catch (Exception e){

        }
    }

    public List<Fase> listaFase(){
        try{
            return   faseDAO.listAll(Fase.class);
        }catch (Exception e){
            return null;

        }
    }

    public List<FaseFormattedDTO> listaFaseFormated(){
        try{
            List<FaseFormattedDTO> faseFormattedDTOList = new ArrayList<>();

            for (Fase f:faseDAO.listAll(Fase.class)) {
                faseFormattedDTOList.add(new FaseFormattedDTO(f));
            }
            return faseFormattedDTOList;
        }catch (Exception e){
            return null;

        }
    }

    public Fase findId(Long id) {
        return faseDAO.findId(id,Fase.class);
    }
}
