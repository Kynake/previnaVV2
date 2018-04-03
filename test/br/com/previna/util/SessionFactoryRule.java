package br.com.previna.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Properties;

public class SessionFactoryRule implements MethodRule {
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private Session session;

    @Override
    public Statement apply(final Statement statement, FrameworkMethod method,
                           Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                sessionFactory = createSessionFactory();
                createSession();
                beginTransaction();
                try {
                    statement.evaluate();
                } finally {
                    shutdown();
                }
            }
        };
    }


    private void shutdown() {
        try {
            try {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private SessionFactory createSessionFactory() {
        SessionFactory factory;

        Properties prop = ReadProperties.read();
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        config.setProperty("hibernate.connection.url", prop.getProperty("conexao.url"));
        config.setProperty("hibernate.connection.password", prop.getProperty("conexao.password"));
        config.setProperty("hibernate.connection.username", prop.getProperty("conexao.user"));
        factory = config.buildSessionFactory();
        return factory;
    }

    public Session createSession() {
        session = sessionFactory.openSession();
        return session;
    }

    public void commit() {
        transaction.commit();
    }

    public void beginTransaction() {
        transaction = session.beginTransaction();
    }

    public Session getSession() {
        return session;
    }
}
