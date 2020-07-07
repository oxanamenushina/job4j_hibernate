package ru.job4j.todo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * UpdateItemServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class UpdateItemServlet extends HttpServlet {

    /**
     * The instance of ValidateService.
     */
    private final Validate<Item> logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataReader reader = new RequestReader();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() { }.getType();
        List<Item> list = gson.fromJson(reader.read(req), type);
        for (Item it : list) {
            this.logic.update(it);
        }
    }
}