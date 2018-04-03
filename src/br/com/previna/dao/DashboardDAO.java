package br.com.previna.dao;

import br.com.previna.db.GenericHibernateDAO;
import br.com.previna.model.*;

import java.util.HashMap;

public class DashboardDAO extends GenericHibernateDAO {

	public static HashMap<String, Long> itemsCount() {
		HashMap<String, Long> countMap = new HashMap<>();
		Class<?>[] classes = {AgeGroup.class, Conteudo.class, Eixo.class, Historia.class, Pergunta.class, User.class};
		for(Class<?> c: classes)
			countMap.put(c.getSimpleName(), rowsCount(c));
		return countMap;
	}
}
