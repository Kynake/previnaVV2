package br.com.previna.dao;

import br.com.previna.db.GenericHibernateDAO;
import br.com.previna.model.Resposta;

import java.util.Set;

public class RespostaDAO extends GenericHibernateDAO<Resposta> {

	public boolean saveList(Set<Resposta> respostas) {
		try {
			for (Resposta r : respostas) {
				save(r);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeList(Set<Resposta> respostas) {
		try {
			for (Resposta r : respostas) {
				remove(r);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
