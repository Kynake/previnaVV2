package br.com.previna.model;

import jdk.nashorn.internal.runtime.ECMAException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "AGEGROUP")
public class AgeGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AGEGROUP")
	private long id;

	@Column(name = "NAME", unique = true)
	private String name;

	/**
	 * Construtor vazio.
	 **/
	public AgeGroup() {
	}

	/**
	 * Metodo que retorna o ID
	 *
	 * @return id (long do ID do AgeGroup)
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Construtor com String name
	 *
	 * @param name (Nome da Faixa Etária)
	 */
	public AgeGroup(String name) {
		this.name = name;
	}

	/**
	 * Metodo que retorna o AgeGroup
	 *
	 * @return name (String que contém a faixa etaria. Ex:8-10)
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AgeGroup [id=" + id + ", name=" + name + "]";
	}

}
