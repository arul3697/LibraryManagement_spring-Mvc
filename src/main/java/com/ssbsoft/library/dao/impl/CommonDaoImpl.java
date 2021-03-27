package com.ssbsoft.library.dao.impl;

import com.ssbsoft.library.dao.CommonDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import static com.ssbsoft.library.util.HibernateUtil.createSession;

/**
 * CommonDao is used to do common dao functions across all model
 * it is Connected to database via Hibernate FrameWork
 *
 * @author Arulselvan S
 */
@Repository
public class CommonDaoImpl implements CommonDao {

    /**
     * saveOrUpdateFunction is used to add , update ,soft delete elements respective to parameter object
     *
     * @param object
     */

    public void saveOrUpdate(Object object) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(object);
        transaction.commit();
        session.close();

    }

    public void delete(Object object) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();

    }
}
