package ru.job4j.todo;

import java.util.List;

/**
 * BaseValidate.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseValidate<T> implements Validate<T> {

    /**
     * The name of the class.
     */
    private final String className;

    /**
     * The instance of DBStore.
     */
    private final Store store = DBStore.getInstance();

    public BaseValidate(String className) {
        this.className = className;
    }

    @Override
    public boolean add(T elem) {
        this.store.add(elem);
        return true;
    }

    @Override
    public boolean update(T elem) {
        this.store.update(elem);
        return true;
    }

    @Override
    public boolean delete(int id) {
        T elem = this.store.getElem(id, this.className);
        boolean result = elem != null;
        if (result) {
            this.store.delete(elem);
        }
        return result;
    }

    @Override
    public T getElem(int id) {
        return this.store.getElem(id, this.className);
    }

    @Override
    public List<T> getList() {
        return this.store.getList(this.className);
    }
}