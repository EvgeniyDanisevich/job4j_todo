package ru.job4j.todo.controller;

import ru.job4j.todo.model.User;
import ru.job4j.todo.store.db.HbmStore;
import ru.job4j.todo.utils.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authEmail = req.getParameter("authEmail");
        String authPassword = req.getParameter("authPassword");
        HbmStore store = new HbmStore();
        User findUser = store.findByEmail(authEmail);
        if (findUser != null && findUser.getPassword().equals(authPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", findUser);
        }
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        String userAsJson = JSONUtil.serialize(user);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        resp.getWriter().write(userAsJson);
    }
}
