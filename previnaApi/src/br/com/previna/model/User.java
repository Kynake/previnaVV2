package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Id;

import javax.validation.constraints.Email;




@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "USERS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable {

	private static final long serialVersionUID = -789863172532826108L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private long id;
	
	@Column(name = "CPF", unique = true)
	private String cpf;
	
	@Email(message="E-mail invalido")
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name= "NAME")
	private String name;
	
	@Column(name= "PASSWORD")
	private String password;
	
	@Column(name= "PHONE")
	private String phone;
	
	@Column(name= "ROLE")
	private String role;

	@Column(name="PROFISSAO")
	private String profissao;
	
	
	@Column(name= "ACTIVE")
	private boolean active;

	@Column(name="UUID")
	private String uuid;

	/**
	 * Construtor vazio.
	 * 
	 **/
	public User() {

    }

	/**
	 * Construtor com o CPF.
	 * 
	 * @param cpf CPF de um usuario.
	 **/
	public User(String cpf) {
		this.cpf = cpf;
	}

	public long getId() {
		return id;
	}

	public User(String cpf, String name) {
		this.cpf = cpf;
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Valida se o usuario esta ativo ou nao.
	 * 
	 * @return boolean
	 **/
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Valida se o usuario e valido atraves do CPF.
	 * 
	 * @return boolean
	 **/
	public boolean isValid() {
		return (this.cpf != null);
	}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", name=" + name + ", password=" + password + ", phone=" + phone + ", role=" + role
				+ ", active=" + active + "]";
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}