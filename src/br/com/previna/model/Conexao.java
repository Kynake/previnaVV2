package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
@Entity
@Table(name = "CONEXAO")
public class Conexao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONEXAO")
    private long id;


    @ManyToOne
    @JoinColumn(name="ID_FASE")
    @JsonBackReference
    private Fase fase;

    @OneToOne(cascade = CascadeType.ALL)
    private  Nodo nodoInicial;

    @OneToOne(cascade = CascadeType.MERGE)
    private Nodo nodoCerto;


    @OneToOne(cascade = CascadeType.MERGE)
    private Nodo nodoErrado;


    public Conexao() {
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Nodo getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Nodo nodoInicial) {
        this.nodoInicial = nodoInicial;
    }

    public Nodo getNodoCerto() {
        return nodoCerto;
    }

    public void setNodoCerto(Nodo nodoCerto) {
        this.nodoCerto = nodoCerto;
    }

    public Nodo getNodoErrado() {
        return nodoErrado;
    }

    public void setNodoErrado(Nodo nodoErrado) {
        this.nodoErrado = nodoErrado;
    }


}
