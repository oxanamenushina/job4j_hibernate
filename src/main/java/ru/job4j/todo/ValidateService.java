package ru.job4j.todo;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * ValidateService.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {

    /**
     * The instance of ValidateService.
     */
    private static final Validate VALIDATE = new ValidateService();

    /**
     * The instance of DBStore.
     */
    private final Store store = DBStore.getInstance();

    private ValidateService() {
    }

    public static Validate getInstance() {
        return VALIDATE;
    }

    /**
     * The method checks the input for the add task operation.
     * @param item a task to add.
     * @return true - a task added to the storage.
     */
    @Override
    public boolean add(Item item) {
        boolean result = item != null && item.getName() != null && item.getDesc() != null
                && !item.getName().equals("") && !item.getDesc().equals("");
        if (result) {
            item.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
            item.setDone(false);
            this.store.add(item);
        }
        return result;
    }

    /**
     * The method checks the input
     * for the update tasks operation.
     * @param list a list of tasks to update.
     * @return true - tasks updated.
     */
    @Override
    public boolean update(List<Item> list) {
        boolean result = !list.isEmpty();
        for (Item item : list) {
            if (item == null || this.store.getItem(item.getId()) == null) {
                result = false;
                break;
            }
        }
        if (result) {
            this.store.update(list);
        }
        return result;
    }

    /**
     * The method checks the input
     * for the delete task operation.
     * @param id a task to delete.
     * @return true - a task deleted.
     */
    @Override
    public boolean delete(int id) {
        boolean result = this.store.getItem(id) != null;
        if (result) {
            this.store.delete(id);
        }
        return result;
    }

    /**
     * The method returns a task with the specified id.
     * @param id task ID.
     * @return task with the specified id.
     */
    @Override
    public Item getItem(int id) {
        return this.store.getItem(id);
    }

    /**
     * The method returns a list of tasks.
     * @return a list of tasks.
     */
    @Override
    public List<Item> getList() {
        return this.store.getList();
    }
}