package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PERGUNTA")
public class Pergunta implements Serializable {

    private static final long serialVersionUID = -789863172532826108L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERGUNTA")
    private long id;

    @Column(name = "DESCRICAO")
    private String descricao;


    @Column(name = "DIFICULDADE")
    private int nivel;

    @OneToOne()
    @JoinColumn(name = "ID_EIXO")
    private Eixo eixo;

    @OneToOne()
    @JoinColumn(name = "ID_AGEGROUP")
    private AgeGroup ageGroup;

    @Column(name = "URLCAPA")
    private String urlCapa;

    @Column(name = "QUANT_PREVINAS")
    private int premio;

    @JsonManagedReference
    @JoinColumn(name = "ID_PERGUNTA")
    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = Resposta.class)
    private Set<Resposta> respostas;

    @Transient
    private String capa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getUrlCapa() {
        return urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Eixo getEixo() {
        return eixo;
    }

    public void setEixo(Eixo eixo) {
        this.eixo = eixo;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getCapa() {
    	return capa;
    }
    
    public void setCapa(String capa) {
    	this.capa = capa;
    }

    public long compareTo(Pergunta outra){
        return id-outra.getId();
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", nivel=" + nivel +
                ", eixo=" + eixo +
                ", ageGroup=" + ageGroup +
                ", urlCapa='" + urlCapa + '\'' +
                ", premio=" + premio +
                ", respostas=" + respostas +
                ", capa='" + capa + '\'' +
                '}';
    }
}
