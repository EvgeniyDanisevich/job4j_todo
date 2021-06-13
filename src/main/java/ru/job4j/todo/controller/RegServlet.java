package ru.job4j.todo.controller;

import ru.job4j.todo.model.User;
import ru.job4j.todo.store.db.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HbmStore store = new HbmStore();
        String regEmail = req.getParameter("regEmail");
        User findUser = store.findByEmail(regEmail);
        if (findUser == null) {
            int id = 0;
            User user = new User();
            String regName = req.getParameter("regName");
            String regPassword = req.getParameter("regPassword");
            user.setPassword(regPassword);
            user.setEmail(regEmail);
            user.setName(regName);
            user.setId(id);
            store.save(user);
            req.getRequestDispatcher("index.html").forward(req, resp);
        }
    }
}
