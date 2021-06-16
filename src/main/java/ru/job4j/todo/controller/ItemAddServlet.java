package ru.job4j.todo.controller;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.Store;
import ru.job4j.todo.store.db.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ItemAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = new HbmStore();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        req.setAttribute("user", req.getSession().getAttribute("user"));
        String description = req.getParameter("description");
        String[] categories = req.getParameterValues("categories[]");
        User user = (User) session.getAttribute("user");
        Item item = new Item(user, description, false);
        store.addNewCategory(item, categories);
    }
}
