package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * DBStore.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DBStore implements Store {

    /**
     * The instance of DBStore.
     */
    private static final Store STORE = new DBStore();

    /**
     * The instance of SessionFactory.
     */
    private final SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private DBStore() {
    }

    public static Store getInstance() {
        return STORE;
    }

    /**
     * The method adds a task to the storage.
     * @param item a task to add.
     */
    @Override
    public void add(Item item) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * The method updates tasks.
     * @param list tasks.
     */
    @Override
    public void update(List<Item> list) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            for (Item item : list) {
                session.update(item);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * The method deletes a task.
     * @param id a task to delete.
     */
    @Override
    public void delete(int id) {
        Session session = this.sf.openSession();
        try {
            session.beginTransaction();
            Item it = new Item();
            it.setId(id);
            session.delete(it);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * The method returns a task with the specified id.
     * @param id task ID.
     * @return a task with the specified id.
     */
    @Override
    public Item getItem(int id) {
        Session session = this.sf.openSession();
        Item item = null;
        try {
            session.beginTransaction();
            item = session.get(Item.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item;
    }

    /**
     * The method returns a list of tasks.
     * @return a list of tasks.
     */
    @Override
    public List<Item> getList() {
        Session session = this.sf.openSession();
        List<Item> list = null;
        try {
            session.beginTransaction();
            list = session.createQuery("from ru.job4j.todo.Item").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}