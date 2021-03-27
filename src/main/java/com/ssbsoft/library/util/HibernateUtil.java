package com.ssbsoft.library.util;

import com.ssbsoft.library.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * HibernateUtil is used to connect with database
 * it opens the session
 * @author Arulselvan S
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = createSessionFactory();

    /**
     * creating the session factory according to given properties
     * @return SessionFactory
     * @throws HibernateException
     */
    private static SessionFactory createSessionFactory() throws HibernateException {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/library?createDatabaseIfNotExist=TRUE");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.connection.password", "root");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            properties.setProperty("hibernate.show_sql", "true");


            Configuration configure = new Configuration().addProperties(properties)
                    .addAnnotatedClass(Role.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Request.class)
                    .addAnnotatedClass(AdminReply.class)
                    .addAnnotatedClass(LibrarianRequest.class)
                    .addAnnotatedClass(Notification.class);
            sessionFactory =  configure.buildSessionFactory();
            System.out.println("sessionFactory: " + sessionFactory);
            return sessionFactory;

        } catch (Throwable ex) {
            ex.printStackTrace();

            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * createSession is returning a session
     * @return Session
     */
    public static Session createSession() {
        return sessionFactory.openSession();
    }
}
