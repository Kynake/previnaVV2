package br.com.previna.model;

import javax.persistence.*;

@Entity
@Table(name = "HISTORIA")
public class Historia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_HISTORIA")
	private Long id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO")
	private String descricao;

	/*@Column(name = "NUMERO")
	private Integer numero;
	*/
	@Column(name = "LINK_IMG")
	private String linkImg;

	/*
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "HISTORIA")
	private AgeGroup ageGroup;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "HISTORIA")
	private Eixo eixo;
	*/

	@Transient
	private String capa;

	public Historia() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/*public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}*/

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	@Override
	public String toString() {
		return "Historia{" +
				"id=" + id +
				", titulo='" + titulo + '\'' +
				", descricao='" + descricao + '\'' +
				", linkImg='" + linkImg + '\'' +
				", capa='" + capa + '\'' +
				'}';
	}
}
