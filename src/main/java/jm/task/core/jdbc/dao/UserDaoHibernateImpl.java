package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory;
    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                session.createSQLQuery(
                        "CREATE TABLE IF NOT EXISTS users_schema.users(" +
                                "id BIGINT  PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(255)," +
                                "lastName VARCHAR(255)," +
                                "age TINYINT)"

                ).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()){
            try {
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS users_schema.users").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String firstName, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                User user = new User(firstName, lastName, age);
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()){
            try {
                session.beginTransaction();
                session.createQuery("DELETE FROM users_schema.users WHERE id = :id")
                        .setParameter("id", id)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            try {
                session.beginTransaction();
                allUsers = session.createSQLQuery("SELECT * FROM users_schema.users")
                        .getResultList();
                session.getTransaction().commit();

            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()){
            try {
                session.beginTransaction();
                session.createQuery("DELETE users_schema.users").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }

    }
}
