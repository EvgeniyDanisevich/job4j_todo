package ru.job4j.todo.controller;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.db.HbmStore;
import ru.job4j.todo.utils.JSONUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class ItemFindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Item> items = new HbmStore().findAll();
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        String json = JSONUtil.serialize(items);
        writer.println(json);
        writer.close();
    }
}
