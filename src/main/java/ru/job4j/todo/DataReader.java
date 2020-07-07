package ru.job4j.todo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * DataReader.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface DataReader {

    String read(HttpServletRequest req) throws IOException;
}