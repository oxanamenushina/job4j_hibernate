package ru.job4j.todo;

import java.util.List;

/**
 * Validate.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Validate {

    /**
     * The method checks the input for the add task operation.
     * @param item a task to add.
     * @return true - a task added to the storage.
     */
    boolean add(Item item);

    /**
     * The method checks the input
     * for the update tasks operation.
     * @param list a list of tasks to update.
     * @return true - tasks updated.
     */
    boolean update(List<Item> list);

    /**
     * The method checks the input
     * for the delete task operation.
     * @param id a task to delete.
     * @return true - a task deleted.
     */
    boolean delete(int id);

    /**
     * The method returns a task with the specified id.
     * @param id user ID.
     * @return task with the specified id.
     */
    Item getItem(int id);

    /**
     * The method returns a list of tasks.
     * @return a list of tasks.
     */
    List<Item> getList();
}