package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users3 (id BIGINT(19) AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45), lastName VARCHAR(45), age TINYINT(3))";
        Transaction ts = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            ts.commit();
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users3";
        Transaction ts = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            ts.commit();
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction ts = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.save(new User(name, lastName, age));
            ts.commit();
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction ts = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.remove(session.get(User.class, id));
            ts.commit();
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction ts = null;
        List<User> userList = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            userList = session.createQuery("from User").list();
            ts.commit();
            userList.forEach(System.out::println);
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction ts = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            ts = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            ts.commit();
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
        }
    }
}