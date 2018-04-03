package br.com.previna.bo;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.previna.dao.AgeGroupDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.AgeGroup;

import javax.persistence.EntityNotFoundException;

public class AgeGroupBO {
    private AgeGroupDAO ageGroupDAO;

    public AgeGroupBO() {
        ageGroupDAO = new AgeGroupDAO();
    }

    private boolean validate(AgeGroup ageGroup) {
        Pattern pattern = Pattern.compile(("^[0-1]?[0-9]{1,2}(-| a )[0-1]?[0-9]{1,2}$"));
        Matcher m = pattern.matcher(ageGroup.getName());
        return m.matches();
    }

    public AgeGroup findId(long id) throws Exception {
        try {
            if (id > 0)
                return ageGroupDAO.findId(id, AgeGroup.class);
        } catch (EntityNotFoundException e) {
            return null;
        } catch (Exception e) {
            throw new Exception(e.getCause());
        }
        return null;
    }

    public boolean add(AgeGroup ageGroup) throws Exception {
        try {
            if (validate(ageGroup)) {
                ageGroupDAO.save(ageGroup);
                return true;
            }
            throw new ValidationException("Faixa Etária inválida");
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar a Faixa Etária: " + e.getMessage());
        }
    }

    public HashMap<String, List<AgeGroup>> list() throws Exception {
        try {
            List<AgeGroup> ageGroupList = ageGroupDAO.listAll(AgeGroup.class);
            HashMap<String, List<AgeGroup>> ageGroupMAP = new HashMap<>();
            ageGroupMAP.put("FaixaEtaria", ageGroupList);
            return ageGroupMAP;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        }
    }

    public boolean remove(AgeGroup ageGroup) throws Exception {
        try {
            ageGroupDAO.remove(ageGroup);
            return true;
        } catch (Exception e) {
            throw new Exception("Erro ao remover a Faixa Etária: " + e.getMessage());
        }
    }

    public boolean update(AgeGroup ageGroup) throws Exception {
        try {
            if (validate(ageGroup)) {
                ageGroupDAO.merge(ageGroup);
                return true;
            }
            throw new ValidationException("Faixa Etária inválida");
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Erro ao editar a Faixa Etária: " + e.getMessage());
        }
    }
}
