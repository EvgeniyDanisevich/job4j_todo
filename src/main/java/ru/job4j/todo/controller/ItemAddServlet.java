package ru.job4j.todo.controller;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.Store;
import ru.job4j.todo.store.db.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class ItemAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Store store = new HbmStore();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        Item item = new Item(description, new Timestamp(System.currentTimeMillis()), false);
        store.save(item);
    }
}
