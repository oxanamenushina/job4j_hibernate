package ru.job4j.todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.management.Query;
import java.util.List;
import java.util.function.Function;

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

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * The method adds a task to the storage.
     * @param item a task to add.
     */
    @Override
    public void add(Item item) {
        this.tx(session -> session.save(item));
    }

    /**
     * The method updates tasks.
     * @param list tasks.
     */
    @Override
    public void update(List<Item> list) {
        for (Item item : list) {
            this.tx(session -> {
                        session.update(item);
                        return null;
                    }
            );
        }
    }

    /**
     * The method deletes a task.
     * @param id a task to delete.
     */
    @Override
    public void delete(int id) {
        Item it = new Item();
        it.setId(id);
        this.tx(session -> {
            session.delete(it);
            return null;
        });
    }

    /**
     * The method returns a task with the specified id.
     * @param id task ID.
     * @return a task with the specified id.
     */
    @Override
    public Item getItem(int id) {
        return this.tx(session -> session.get(Item.class, id));
    }

    /**
     * The method returns a list of tasks.
     * @return a list of tasks.
     */
    @Override
    public List<Item> getList() {
        return this.tx(session -> session.createQuery("from ru.job4j.todo.Item").list());
    }
}