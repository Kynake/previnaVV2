package br.com.previna.bo;

import br.com.previna.dao.EixoDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Eixo;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EixoBO {
	private EixoDAO eixoDAO = new EixoDAO();
	private static final int MIN_LENGTH = 3;
	private static final int MAX_LENGTH = 100;

	public Eixo findId(long id) throws Exception {
		try{
		if (id > 0)
			return eixoDAO.findId(id, Eixo.class);
		} catch (EntityNotFoundException e) {
			return null;
		} catch (Exception e) {
			throw new Exception(e.getCause());
		}
		return null;
	}

	public boolean validate(Eixo eixo) {
		Pattern pattern = Pattern.compile(("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{" + MIN_LENGTH + "," + MAX_LENGTH + "}$"));
		Matcher m = pattern.matcher(eixo.getName());
		return m.matches();
	}

	public boolean add(Eixo eixo) throws Exception {
		try {
			if (validate(eixo)) {
				eixoDAO.addEixo(eixo);
				return true;
			}
			throw new ValidationException("Eixo inválido");
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao adicionar o Eixo: " + e.getMessage());
		}
	}

	public HashMap<String, List<Eixo>> list() {
		try {
			List<Eixo> eixoList = eixoDAO.list();
			HashMap<String, List<Eixo>> eixoMap = new HashMap<>();
			eixoMap.put("Eixo", eixoList);
			return eixoMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(Eixo eixo) throws Exception {
		try {
			if (validate(eixo)) {
				eixoDAO.updateEixo(eixo);
				return true;
			}
			throw new ValidationException("Eixo inválido");
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao editar o Eixo: " + e.getMessage());
		}
	}

	public boolean remove(Eixo eixo) {
		try {
			eixoDAO.remove(eixo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return false;
	}

}
