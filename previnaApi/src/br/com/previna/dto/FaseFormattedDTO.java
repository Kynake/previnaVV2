package br.com.previna.dto;

import br.com.previna.model.AgeGroup;
import br.com.previna.model.Fase;

import javax.persistence.*;

public class FaseFormattedDTO {


    private long id;
    private int dificuldade;
    private AgeGroup ageGroup;
    private String descricao;

    public FaseFormattedDTO(Fase fase) {

        this.ageGroup=fase.getAgeGroup();
        this.descricao=fase.getDescricao();
        this.dificuldade=fase.getDificuldade();
        this.id=fase.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
