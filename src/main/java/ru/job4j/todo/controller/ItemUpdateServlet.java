package ru.job4j.todo.controller;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.Store;
import ru.job4j.todo.store.db.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        Store store = new HbmStore();
        Item item = new HbmStore().findById(Integer.parseInt(req.getParameter("id")));
        item.setDone(!item.isDone());
        store.save(item);
    }
}
