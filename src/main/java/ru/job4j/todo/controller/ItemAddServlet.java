package ru.job4j.todo.controller;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.Store;
import ru.job4j.todo.store.db.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class ItemAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Store store = new HbmStore();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        req.setAttribute("user", req.getSession().getAttribute("user"));
        String description = req.getParameter("description");
        User user = (User) session.getAttribute("user");
        Item item = new Item(user, description, new Timestamp(System.currentTimeMillis()), false);
        store.save(item);
    }
}
