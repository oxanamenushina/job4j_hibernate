package ru.job4j.todo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * AddItemServlet.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class AddItemServlet extends HttpServlet {

    /**
     * The instance of ValidateService.
     */
    private final Validate<Item> logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataReader reader = new RequestReader();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() { }.getType();
        Map<String, String> map = gson.fromJson(reader.read(req), type);
        Item it = new Item();
        it.setName(map.get("name"));
        it.setDesc(map.get("desc"));
        it.setUser(this.getUser(req));
        boolean result = this.logic.add(it);
        if (result) {
            List<Item> list = this.logic.getList();
            String last = gson.toJson(list.get(list.size() - 1));
            PrintWriter writer = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            writer.append(last);
            writer.flush();
        }
    }

    /**
     * The method searches for a user with the specified login.
     */
    private User getUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        List<User> users = ValidateUser.getInstance().getList();
        return users.stream().filter(u -> login.equals(u.getLogin())).findAny().get();
    }
}