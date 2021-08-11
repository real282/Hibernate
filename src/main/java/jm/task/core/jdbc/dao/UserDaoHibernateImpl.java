package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users (id INT AUTO_INCREMENT, " +
                    "name VARCHAR(30), " +
                    "lastName VARCHAR(30), " +
                    "age TINYINT, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        } catch (Exception ignore) {

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception ignore) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = (User) session.get(User.class, id);
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (IllegalArgumentException ignore) {

        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> users = session.createQuery("from User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        } catch (Exception ignore) {

        }
    }
}
