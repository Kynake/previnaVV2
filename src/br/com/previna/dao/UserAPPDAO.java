package br.com.previna.dao;

import br.com.previna.db.GenericHibernateDAO;
import br.com.previna.model.User;
import br.com.previna.model.UserAPP;

public class UserAPPDAO  extends GenericHibernateDAO<UserAPP>{
    public long updateUser(UserAPP user) {
        return merge(user);
    }


    public UserAPP findUserByEmail(String email) {
        return (UserAPP) findSingleObject("email", UserAPP.class, email);
    }

    public long removeUser(UserAPP user) {
        return remove(user);
    }

    public UserAPP findUserByID(long id) {
        return findId(id, UserAPP.class);
    }
}
