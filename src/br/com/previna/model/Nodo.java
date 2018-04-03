package br.com.previna.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "NODO", uniqueConstraints={
        @UniqueConstraint(name="uniqueConteudo", columnNames={"ID_CONTEUDO"})
})
public class Nodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NODO")
    private long id;

    @OneToOne()
    @JoinColumn(name = "ID_HISTORIA")
    private  Historia historia;

    @OneToOne()
    @JoinColumn(name = "ID_CONTEUDO")

    private Conteudo conteudo;

    @OneToOne()
    @JoinColumn(name = "ID_PERGUNTA")
    private Pergunta pergunta;

    public Nodo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Historia getHistoria() {
        return historia;
    }

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
}
