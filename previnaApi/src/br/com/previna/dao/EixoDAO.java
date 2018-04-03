package br.com.previna.dao;

import br.com.previna.db.GenericHibernateDAO;
import br.com.previna.model.Eixo;

import java.util.List;

public class EixoDAO extends GenericHibernateDAO<Eixo> {

	public boolean addEixo(Eixo eixo) throws Exception {
		try {
			save(eixo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);

		}
	}

	public boolean removeEixo(Eixo eixo) {
		try {
			remove(eixo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateEixo(Eixo eixo) {
		try {
			merge(eixo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Eixo> list() throws Exception {
		try {
			return listAll(Eixo.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getCause());

		}
	}

}
