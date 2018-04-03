package br.com.previna.bo;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.previna.dao.HistoriaDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Historia;

public class HistoriaBO {
	private HistoriaDAO historiaDAO;
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 200;
	private static final int MAX_LENGTH_DESCRICAO = 600;

	public HistoriaBO() {
		historiaDAO = new HistoriaDAO();
	}

	private boolean validate(Historia historia) throws Exception {
		if (!validateTitulo(historia))
			throw new ValidationException("Título da História inválido");

		if (!validateDescricao(historia))
			throw new ValidationException("Descrição da História inválida");
		return true;
	}

	private boolean validateTitulo(Historia historia) {
		int length = historia.getTitulo().length();
		return length >= MIN_LENGTH && length <= MAX_LENGTH;
		/*
		 * Pattern pattern =
		 * Pattern.compile(("^[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{" +
		 * MIN_LENGTH + "," + MAX_LENGTH + "}$")); Matcher m =
		 * pattern.matcher(historia.getTitulo()); return m.matches();
		 */
	}

	private boolean validateDescricao(Historia historia) {
		int length = historia.getDescricao().length();
		return length <= MAX_LENGTH_DESCRICAO;
		/*
		 * Pattern pattern =
		 * Pattern.compile(("^[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{" +
		 * MIN_LENGTH + "," + MAX_LENGTH_DESCRICAO + "}$")); Matcher m =
		 * pattern.matcher(historia.getDescricao()); return m.matches();
		 */
	}

	public long add(Historia historia) throws Exception {
		try {
			if (validate(historia))
				return historiaDAO.save(historia);
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao adicionar a História: " + e.getMessage());
		}
		return 0;
	}

	public HashMap<String, List<Historia>> list() throws Exception {
		try {
			List<Historia> list = historiaDAO.listAll(Historia.class);
			HashMap<String, List<Historia>> map = new HashMap<>();
			map.put("Historia", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getCause());
		}
	}

	public Historia findId(Long id) {
		try {
			return historiaDAO.findId(id, Historia.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(Historia historia) throws Exception {
		try {
			if (validate(historia))
				historiaDAO.merge(historia);
			return true;
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao editar a História: " + e.getMessage());
		}

	}

	public boolean remove(Historia historia) throws Exception {
		try {
			historiaDAO.remove(historia);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Houve algum problema ao remover a história :(");
		}
	}

}
