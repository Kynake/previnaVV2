package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="FASE")
public class Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FASE")
    private long id;
    @Column(name = "DIFICULDADE")
    private int dificuldade;
@OneToOne
private AgeGroup ageGroup;
    @Column(name = "DESCRICAO")
    private String descricao;


    @JsonManagedReference
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "fase",targetEntity = Conexao.class)
    @Column
    private List<Conexao> conexoes;

    public Fase() {
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

    public List<Conexao> getConexoes() {
        return conexoes;
    }

    public void setConexoes(List<Conexao> conexoes) {
        this.conexoes = conexoes;
    }
}
