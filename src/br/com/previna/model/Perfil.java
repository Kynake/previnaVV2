package br.com.previna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


/**
 * Classe de modelo para validar a senha do usuario.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class Perfil extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;

	private String passwordToValidate;

	public Perfil() {
		super();
	}

	@JsonIgnore
	public String getPasswordToValidate(){
		return passwordToValidate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPasswordToValidate(String passwordToValidate) {
		this.passwordToValidate = passwordToValidate;
	}



}
