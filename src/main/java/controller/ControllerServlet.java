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
                double x = Double.parseDouble(req.getParameter("x"));
                double y = Double.parseDouble(req.getParameter("y"));
                double r = Double.parseDouble(req.getParameter("r"));
                req.setAttribute("par_x", x);
                req.setAttribute("par_y", y);
                req.setAttribute("par_r", r);
                getServletContext().getRequestDispatcher("/areaCheckServlet").forward(req, resp);
            } else if (req.getParameter("clear") != null && req.getParameter("clear").equals("true")) {
                req.setAttribute("clear", true);
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