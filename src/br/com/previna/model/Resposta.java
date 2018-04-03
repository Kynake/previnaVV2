package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "RESPOSTA")
public class Resposta implements Serializable {
    private static final long serialVersionUID = -789863172532826108L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name= "RESPOSTA_CERTA")
    private boolean certo;

    @ManyToOne
    @JoinColumn(name="ID_PERGUNTA")
    @JsonBackReference
    private Pergunta pergunta;




    public boolean isCerto() {
        return certo;
    }

    public void setCerto(boolean certo) {
        this.certo = certo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
}
