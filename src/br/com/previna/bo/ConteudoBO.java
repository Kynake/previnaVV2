package br.com.previna.bo;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.previna.dao.ConteudoDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.Conteudo;
import br.com.previna.model.AgeGroup;
import br.com.previna.model.Eixo;

/**
 * 
 * @author Jhonata Saraiva Peres
 * @since 28/09/2017
 * @version 1.0v
 *
 */

public class ConteudoBO {
	private ConteudoDAO conteudoDAO = new ConteudoDAO();
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 200;
	private static final int MAX_LENGTH_DESCRICAO = 600;
	private EixoBO eixoBO = new EixoBO();
	private AgeGroupBO ageGroupBO = new AgeGroupBO();

	public Conteudo findId(long id) {
		if (id > 0)
			return conteudoDAO.findId(id, Conteudo.class);
		return null;
	}

	private boolean validateName(Conteudo conteudo) {
		int length = conteudo.getName().length();
		return length >= MIN_LENGTH && length <= MAX_LENGTH;

		/*
		 * Pattern pattern =
		 * Pattern.compile(("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{" +
		 * MIN_LENGTH + "," + MAX_LENGTH + "}$")); Matcher m =
		 * pattern.matcher(conteudo.getName()); return m.matches();
		 */
	}
	private boolean validateDescricao(Conteudo conteudo) {
		int length = conteudo.getDescricao().length();
		return length <= MAX_LENGTH_DESCRICAO && length > 0;
		/*
		 * Pattern pattern =
		 * Pattern.compile(("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{" +
		 * MIN_LENGTH + "," + MAX_LENGTH_DESCRICAO + "}$")); Matcher m =
		 * pattern.matcher(conteudo.getDescricao()); return m.matches();
		 */

	}

	private boolean verificaEixoExiste(Eixo eixo) throws Exception {
		return eixoBO.findId(eixo.getId()) != null;
	}

	private boolean verificaAgeGroupExiste(AgeGroup ageGroup) throws Exception {
		return ageGroupBO.findId(ageGroup.getId()) != null;
	}

	private boolean validate(Conteudo conteudo) throws Exception {
		if (!validateName(conteudo))
			throw new ValidationException("Título do conteúdo inválido");
		if (!validateDescricao(conteudo))
			throw new ValidationException("Descrição do conteúdo inválida");
		if (!verificaAgeGroupExiste(conteudo.getAgeGroup()) || !verificaEixoExiste(conteudo.getEixo()))
			throw new ValidationException("Eixo ou Faixa Etária não existentes");

		return true;
	}

	public long add(Conteudo conteudo) throws Exception {
		try {
			if (validate(conteudo))
				return conteudoDAO.addConteudo(conteudo);
		} catch (ValidationException e) {
			throw new Exception(e);
		} catch (Exception e) {
			throw new Exception("Erro ao adicionar o Conteúdo: " + e.getMessage());
		}
		return 0;
	}

	public HashMap<String, List<Conteudo>> list() throws Exception {
		try {
			List<Conteudo> conteudoList = conteudoDAO.list();
			HashMap<String, List<Conteudo>> conteudoMap = new HashMap<>();
			conteudoMap.put("Conteudo", conteudoList);
			return conteudoMap;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getCause());

		}
	}

	public boolean update(Conteudo conteudo) throws Exception {
		try {
			if (validate(conteudo))
				return conteudoDAO.updateConteudo(conteudo);
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao editar o Conteúdo: " + e.getMessage());
		}
		return false;
	}

	public boolean remove(Conteudo conteudo) {
		try {
			conteudoDAO.remove(conteudo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
