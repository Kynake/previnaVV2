package br.com.previna.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.previna.db.GenericHibernateDAO;
import br.com.previna.db.HibernateUtil;
import br.com.previna.model.User;

public class UserDAO extends GenericHibernateDAO<User> {
	
	public List<User> listForName(Object User, String q) {
		Session session = HibernateUtil.getFactory();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) session.getNamedQuery("findUserForName").setParameter("userName", q).list();
		return users;
	}

	public User findUserByCPF(String cpf) {
		return (User) findSingleObject("cpf", User.class, cpf);
	}

	public User findUserByEmail(String email) {
		return (User) findSingleObject("email", User.class, email);
	}

	public long removeUser(User user) {
		return remove(user);
	}

	public User findUserByID(long id) {
		return findId(id, User.class);
	}

	public long updateUser(User user) {
		return merge(user);
	}

	public long alterStatus(long id, boolean status) {
		User user = findUserByID(id);
		user.setActive(status);
		return merge(user);
	}
}
