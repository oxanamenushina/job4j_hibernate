package ru.job4j.todo;

/**
 * Permission.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Permission {

    /**
     * The method checks if an account exists
     * with the given login and password.
     * @param login login.
     * @param password password.
     * @return true - authorization was successful.
     */
    boolean isPermit(String login, String password);
}
