package ru.job4j.todo;

import java.util.List;

/**
 * Store.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    /**
     * The method adds a task to the storage.
     * @param item a task to add.
     */
    void add(Item item);

    /**
     * The method updates tasks.
     * @param items tasks.
     */
    void update(List<Item> items);

    /**
     * The method deletes a task.
     * @param id a task to delete.
     */
    void delete(int id);

    /**
     * The method returns a task with the specified id.
     * @param id task ID.
     * @return a task with the specified id.
     */
    Item getItem(int id);

    /**
     * The method returns a list of tasks.
     * @return a list of tasks.
     */
    List<Item> getList();
}