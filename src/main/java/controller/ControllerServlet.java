package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("x") != null &&
                    req.getParameter("y") != null &&
                    req.getParameter("r") != null) {
                getServletContext().getRequestDispatcher("/areaCheckServlet").forward(req, resp);
            } else if (req.getParameter("clear") != null && req.getParameter("clear").equals("true")) {
                getServletContext().getRequestDispatcher("/clearServlet").forward(req, resp);
            } else getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e.getClass());
            getServletContext().getRequestDispatcher("/resultPage.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}