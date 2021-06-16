package ru.job4j.todo.controller;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.db.HbmStore;
import ru.job4j.todo.utils.JSONUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Category> categories = new HbmStore().allCategory();
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        String json = JSONUtil.serialize(categories);
        writer.println(json);
        writer.close();
    }
}
