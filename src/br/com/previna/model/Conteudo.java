package br.com.previna.model;

import br.com.previna.model.Eixo;
import br.com.previna.model.AgeGroup;
import javax.persistence.*;

/**
 * Classe modelo de conteudo
 * 
 * @author Jhonata Saraiva Peres
 * @since 27/09/2017
 * @version 1.1v
 * 
 *
 */

@Entity
@Table(name="CONTEUDO")
public class Conteudo {
	
	public Conteudo() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTEUDO")
	private Long id;
	
	@Column(name = "NAME")/*!! "unique = true"!! Conteudo seria unico? */
	private String name;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "LINK_IMG")
	private String linkImg;
	
	@ManyToOne
	@JoinColumn(name = "ID_AGEGROUP")
	private AgeGroup ageGroup;
	
	@ManyToOne
	@JoinColumn(name = "ID_EIXO")
	private Eixo eixo;

	@Transient
	private String capa;
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getLinkImg() {
		return linkImg;
	}
	
	public AgeGroup getAgeGroup() {
		return ageGroup;
	}
	
	public Eixo getEixo() {
		return eixo;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
	
	public void setAgrGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	public void setEixo(Eixo eixo) {
		this.eixo = eixo;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	@Override
	public String toString() {
		return "Conteudo [id=" + id + ", name=" + name + ", descricao=" + descricao + ", linkImg=" + linkImg
				+ ", ageGroup=" + ageGroup + ", eixo=" + eixo + "]";
	}
	
}
