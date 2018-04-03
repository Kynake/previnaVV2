package br.com.previna.dao;

import java.util.List;

import br.com.previna.db.GenericHibernateDAO;
import br.com.previna.model.Conteudo;

/**
 * 
 * @author Jhonata
 * @since 28/09/2017
 * @version 1.0v
 *
 */

public class ConteudoDAO extends GenericHibernateDAO<Conteudo> {

	public long addConteudo(Conteudo conteudo) throws Exception {
		try {
			return save(conteudo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public boolean removeConteudo(Conteudo conteudo) {
		try {
			remove(conteudo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateConteudo(Conteudo conteudo) throws Exception {
		try {
			merge(conteudo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public List<Conteudo> list() throws Exception {
		try {
			return listAll(Conteudo.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getCause());
		}
	}
}