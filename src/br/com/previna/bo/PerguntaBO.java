package br.com.previna.bo;

import br.com.previna.dao.PerguntaDAO;
import br.com.previna.dao.RespostaDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.AgeGroup;
import br.com.previna.model.Pergunta;
import br.com.previna.model.Resposta;

import java.util.*;

public class PerguntaBO {
	private static final int MAX_PREVINAS = 1000000;
	private PerguntaDAO perguntaDAO = new PerguntaDAO();
	private EixoBO eixoBO = new EixoBO();
	private AgeGroupBO ageGroupBO = new AgeGroupBO();

	public long add(Pergunta pergunta) throws Exception {
		try {
			if (validate(pergunta))
				return perguntaDAO.save(pergunta);
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao adicionar a Pergunta: " + e.getMessage());
		}
		return 0;
	}

	private boolean validatePrize(int premio) {
		return premio <= MAX_PREVINAS && premio >= 0;
	}

	public boolean hasRightAnswer(Pergunta pergunta) {

		for (Resposta r : pergunta.getRespostas()) {
			if (r.isCerto())
				return true;
		}
		return false;
	}

	public boolean validateSize(Pergunta pergunta) {
		return pergunta.getDescricao().length() < 600 && pergunta.getDescricao().length() > 1;
	}

	private boolean validateLevel(Pergunta pergunta) {
		return pergunta.getNivel() > -1 && pergunta.getNivel() < 100;
	}

	private boolean validateAmount(Pergunta pergunta) {
		return pergunta.getRespostas().size() < 6;
	}

	public boolean validate(Pergunta pergunta) throws Exception {
		//poderia montar uma string e concatenar cada problema!
		if (!validateAmount(pergunta))
			throw new ValidationException("Quantidade de respostas inválida");
		if (!validateLevel(pergunta))
			throw new ValidationException("Nível inválido");
		if (!validateSize(pergunta))
			throw new ValidationException("Descrição inválida");
		if (!hasRightAnswer(pergunta))
			throw new ValidationException("Não há resposta certa");
		if (verificaEixoExiste(pergunta))
			throw new ValidationException("Eixo não encontrado");
		if (verificaAgeGroupExiste(pergunta))
			throw new ValidationException("Faixa Etária não encontrada");
		if (!validatePrize(pergunta.getPremio()))
			throw new ValidationException("O valor máximo de previnas deve ser menor ou igual a " + MAX_PREVINAS);
		return true;
	}

	private boolean verificaEixoExiste(Pergunta pergunta) throws Exception {
		return eixoBO.findId(pergunta.getEixo().getId()) == null;
	}

	private boolean verificaAgeGroupExiste(Pergunta pergunta) throws Exception {
		AgeGroup id = ageGroupBO.findId(pergunta.getAgeGroup().getId());
		return ageGroupBO.findId(pergunta.getAgeGroup().getId()) == null;
	}

	public void remove(Pergunta pergunta) {
		try {
			perguntaDAO.remove(pergunta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean update(Pergunta pergunta) throws Exception {
		try {
			if (validate(pergunta))
				perguntaDAO.merge(pergunta);
			return true;
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception("Erro ao editar a Pergunta: " + e.getMessage());
		}
	}

	public ArrayList<Pergunta> list() {
		try {
			return (ArrayList<Pergunta>) perguntaDAO.listAll(Pergunta.class);
		} catch (Exception e) {
			return null;
		}
	}

	public void setPerguntaDAO(PerguntaDAO perguntaDAO) {
		this.perguntaDAO = perguntaDAO;
	}

	public Pergunta findId(Long id) {
		try {
			return perguntaDAO.findId(id, Pergunta.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
