package br.com.previna.bo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.previna.dao.UserDAO;
import br.com.previna.exception.ValidationException;
import br.com.previna.model.User;
import br.com.previna.util.Constantes;
import br.com.previna.util.EncryptUtil;
import br.com.previna.util.MensagemContantes;
import br.com.previna.util.Validator;

public class UserBO {
	private UserDAO userDAO;

	public UserBO() {
		userDAO = new UserDAO();
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	private boolean validateCPF(User user) throws ValidationException {
		if (Validator.validaCpf.fazConta(user.getCpf()))
			return true;
		throw new ValidationException("CPF inválido");

	}

	public boolean createUser(User user) throws Exception {
		try {
			if (user != null) {
				user.setRole(Constantes.ANALYST_ROLE);
				user.setActive(true);
				String encryptedPassword = EncryptUtil.encrypt2(user.getPassword());
				user.setPassword(encryptedPassword);
				validateCPF(user);
				userDAO.save(user);
				return true;
			}
			throw new ValidationException("Usuário inválido");
		} catch(ValidationException e) {
			throw e;
		} catch(Exception e) {
			throw new Exception("Erro ao criar o usuário: " + e.getMessage());
		}
	}

	public long disable(long id) {
		return userDAO.alterStatus(id, false);
	}

	public long enable(long id) {
		return userDAO.alterStatus(id, true);
	}

	public long updateUser(User user) throws ValidationException {
		if (user != null) {
			return userDAO.updateUser(user);
		}
		throw new ValidationException("invalido");

	}

	public User validate(User newUser) {
		return newUser;
	}

	public User userExists(User userLogin) throws NoSuchAlgorithmException, ValidationException {
		userLogin.setPassword(EncryptUtil.encrypt2(userLogin.getPassword()));
		User returnedUser = userDAO.findUserByCPF(userLogin.getCpf());
		if (!userLogin.getPassword().equals(returnedUser.getPassword()))
			throw new ValidationException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
		return returnedUser;
	}

	public HashMap<String, List<User>> listUser() {
		ArrayList<User> users = null;
		HashMap<String, List<User>> listUsers = new HashMap<String, List<User>>();
		users = (ArrayList<User>) userDAO.listAll(User.class);
		listUsers.put("Users", users);
		return listUsers;
	}

	public User getUserByCpf(User userLogin) throws ValidationException {
		if (userLogin != null) {
			return userDAO.findUserByCPF(userLogin.getCpf());
		}
		throw new ValidationException("invalido");

	}
	public User getUserById(long id) throws ValidationException {
		if (id > 0) {
			return userDAO.findUserByID(id);
		}
		throw new ValidationException("invalido");

	}

	public User getUserByEmail(String email) throws ValidationException {
		if(email.length()>0)
			return userDAO.findUserByEmail(email);
		throw new ValidationException("Email Invalido");

	}

    public boolean recuperaSenha(User recuperando, String password) {
	    try {
            String encryptedPassword = EncryptUtil.encrypt2(password);
            recuperando.setPassword(encryptedPassword);
            userDAO.updateUser(recuperando);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return true;

    }
}
