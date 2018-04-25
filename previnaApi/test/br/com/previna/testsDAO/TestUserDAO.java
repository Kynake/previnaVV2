package br.com.previna.testsDAO;

import br.com.previna.dao.UserDAO;
import br.com.previna.model.User;
import br.com.previna.util.SessionFactoryRule;
import br.com.previna.util.UtilTest;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author bruno.mazzardo
 * Classe para teste do DAO Hibernate
 */
/**
public class TestUserDAO {

    @Rule
    public final SessionFactoryRule sf = new SessionFactoryRule();
// Teste de integraçao, para criação de tabelas

    @Test
    public void testCreationTableUser() {
        User user = UtilTest.createUser();
        UserDAO userDAO = new UserDAO();
        userDAO.save(user);
        List<User> userList = userDAO.listAll(User.class);
        assertNotNull(userList);
        assertTrue(userList.size()>0);
    }
}

*/