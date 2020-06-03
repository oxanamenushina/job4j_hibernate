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
        Type type = new TypeToken<Map<String, String>>() { }.getType();
        Map<String, String> map = gson.fromJson(sb.toString(), type);
        Item it = new Item();
        it.setName(map.get("name"));
        it.setDesc(map.get("desc"));
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
}