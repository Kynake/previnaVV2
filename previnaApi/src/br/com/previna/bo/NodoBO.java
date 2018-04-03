package br.com.previna.bo;

import br.com.previna.dao.NodoDAO;
import br.com.previna.model.Nodo;

public class NodoBO {

    NodoDAO nodoDAO = new NodoDAO();


    public Nodo nodoExiste(Nodo n) {
        Boolean achou = false;
        Nodo nodo = null ;

        if (n.getConteudo() != null) {
            if (buscaID(n.getConteudo().getId(),"conteudo") != null) {
                nodo = buscaID(n.getConteudo().getId(),"conteudo");
                achou = true;
            }
        } else if (n.getHistoria() != null) {
            if (buscaID(n.getHistoria().getId(),"historia") != null)
                achou = true;
                nodo =  buscaID(n.getHistoria().getId(),"historia");
        } else {
            if (buscaID(n.getPergunta().getId(),"pergunta") != null)
                achou = true;
                nodo = buscaID(n.getPergunta().getId(),"pergunta");
        }
        if (!achou) {
          long id =  nodoDAO.save(n);
          nodo = nodoDAO.findId(id,Nodo.class);
        }

        return nodo;
    }

    public Nodo buscaID(Long id,String tipo){
        try {
            return (Nodo) nodoDAO.findSingleObject(tipo, Nodo.class, "" + id);
        }catch (Exception e) {
            return null;
        }
    }

}

