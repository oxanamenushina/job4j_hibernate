package ru.job4j.todo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader json = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = json.readLine()) != null) {
            sb.append(str);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() { }.getType();
        List<Item> list = gson.fromJson(sb.toString(), type);
        this.logic.update(list);
    }
}